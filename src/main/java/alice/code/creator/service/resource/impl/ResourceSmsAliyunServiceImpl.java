package alice.code.creator.service.resource.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import alice.code.creator.domain.constant.ResultCodeConstant;
import alice.code.creator.domain.enums.ResourceSmsGatewayEnum;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.monitor.ResourceSmsLog;
import alice.code.creator.service.monitor.ResourceSmsLogService;
import alice.code.creator.service.resource.ResourceSmsService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 阿里云短信实现类
 * User: contact@liuxp.me
 * DateTime: 2019-11-14
 **/
@Service("ResourceSmsAliyunServiceImpl")
public class ResourceSmsAliyunServiceImpl implements ResourceSmsService {

    // 短信发送日志接口
    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 阿里云账号Appkey
    private static final String APP_KEY="";
    // 阿里云密钥appSecret
    private static final String APP_SECRET="";
    // 验证码模版编号
    // 您的验证码为：${code}，该验证码10分钟内有效，请勿泄露于他人。
    private static final String CAPTCHA_TEMPLATE_CODE = "";
    // 签名
    private static final String SIGN_NAME = "";

    // 短信日志Service
    @Resource
    private ResourceSmsLogService resourceSmsLogService;

    @Override
    public Result<Boolean> sendRegisterCaptcha(String mobileNumber) {

        // 生产验证码
        String code = getCode();

        Result<Boolean> result = new Result<>();

        ResourceSmsLog resourceSmsLog = new ResourceSmsLog();
        resourceSmsLog.setSmsGatewayType(ResourceSmsGatewayEnum.ALIYUN.getCode());
        resourceSmsLog.setSmsTemplateCode(CAPTCHA_TEMPLATE_CODE);
        resourceSmsLog.setSmsTemplateName("默认注册码");
        resourceSmsLog.setSmsSendPhone(mobileNumber);
        resourceSmsLog.setSmsSendContent(code);
        resourceSmsLog.setSmsSendTime(new Date());
        resourceSmsLog.setSmsSendStatus(1);

        try {

            CommonResponse commonResponse = sendCaptchaSms(mobileNumber,code);

            resourceSmsLog.setSmsSendResult(commonResponse.getData());

            if(commonResponse.getHttpStatus() != 200){
                resourceSmsLog.setSmsSendStatus(0);

                result = new Result<>(ResultCodeConstant.ILLEGAL_ARGUMENT_EXCEPTION,commonResponse.getData());
            }

        }catch (Exception e){
            resourceSmsLog.setSmsSendExceptionText(e.toString());
            resourceSmsLog.setSmsSendStatus(0);
            result = new Result<>(ResultCodeConstant.EXCEPTION,e.toString());
        }finally{
            try{
                resourceSmsLogService.insert(resourceSmsLog,"SYSTEM");
            }catch (Exception e){
                logger.error("短信发送日志记录失败，不影响业务进行。",e);
            }
        }

        return result;

    }

    @Override
    public Result<Boolean> sendForgotPasswordCaptcha(String mobileNumber) {
        return sendRegisterCaptcha(mobileNumber);
    }

    /**
     * 检查验证码是否正确
     * @param mobileNumber 手机号
     * @param captcha 验证码
     * @return 是否正确
     */
    @Override
    public Result<Boolean> checkCaptcha(String mobileNumber, String captcha) {

        ResourceSmsLog resourceSmsLogQuery = new ResourceSmsLog();
        resourceSmsLogQuery.setSmsSendContent(captcha);
        resourceSmsLogQuery.setSmsSendPhone(mobileNumber);
        // 仅查询1小时之内的数据
        resourceSmsLogQuery.setCreateTimeBegin(new Date(new Date().getTime() - 1000 * 60 * 60));

        List<ResourceSmsLog> resourceSmsLogList = resourceSmsLogService.selectList(resourceSmsLogQuery);

        return new Result<>(null!=resourceSmsLogList&&resourceSmsLogList.size()>0);

    }


    /**
     * 发送验证码
     * @param phone 手机号
     * @param code 验证码
     * @return 调用结果
     * @throws Exception 异常信息
     */
    private static CommonResponse sendCaptchaSms(String phone, String code) throws Exception {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", APP_KEY, APP_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonResponse response = null;
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", SIGN_NAME);
        request.putQueryParameter("TemplateCode", CAPTCHA_TEMPLATE_CODE);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        response = client.getCommonResponse(request);

        return response;
    }

    /**
     * 生成6位随机数验证码
     * @return 6位随机数验证码
     */
    private static String getCode(){
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            code.append((int)(Math.random() * 9));
        }
        return code.toString();
    }

}