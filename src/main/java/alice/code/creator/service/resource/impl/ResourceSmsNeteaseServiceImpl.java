package alice.code.creator.service.resource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import alice.code.creator.common.util.EncryptionUtil;
import alice.code.creator.domain.constant.ResultCodeConstant;
import alice.code.creator.domain.enums.ResourceSmsGatewayEnum;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.monitor.ResourceSmsLog;
import alice.code.creator.service.monitor.ResourceSmsLogService;
import alice.code.creator.service.resource.ResourceSmsService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 网易短信实现类
 * User: contact@liuxp.me
 * DateTime: 2018-12-12
 **/
@Service("ResourceSmsNeteaseServiceImpl")
public class ResourceSmsNeteaseServiceImpl implements ResourceSmsService {

    // 日志
    private Logger logger = LoggerFactory.getLogger(getClass());

    // 网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APP_KEY="";
    // 网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET="";

    // 发送验证码URL
    private static final String SERVER_URL_SEND_CAPTCHA="https://api.netease.im/sms/sendcode.action";
    // 短信验证URL
    private static final String SERVER_URL_CHECK_CAPTCHA="https://api.netease.im/sms/verifycode.action";
    // 发送通知类和运营类短信URL
    private static final String SERVER_URL_TEMPLATE="https://api.netease.im/sms/sendtemplate.action";
    // 随机数
    private static final String NONCE="123456";
    // 验证码长度，范围4～10，默认为4
    private static final String CODELEN="6";
    // 注册验证码模版编号
    private static final String REGISTER_CAPTCHA_TEMPLATE_ID = "";
    // 密码找回验证码模板编号
    private static final String FORGOT_PASSWORD_CAPTCHA_TEMPLATE_ID = "";
    // 短信发送日志接口

    // 短信日志Service
    @Resource
    private ResourceSmsLogService resourceSmsLogService;

    @Override
    public Result<Boolean> sendRegisterCaptcha(String mobileNumber) {

        Result<Boolean> result = new Result<>();

        ResourceSmsLog resourceSmsLog = new ResourceSmsLog();
        resourceSmsLog.setSmsGatewayType(ResourceSmsGatewayEnum.NETEASE.getCode());
        resourceSmsLog.setSmsTemplateCode(REGISTER_CAPTCHA_TEMPLATE_ID);
        resourceSmsLog.setSmsTemplateName("注册");
        resourceSmsLog.setSmsSendPhone(mobileNumber);
        resourceSmsLog.setSmsSendContent("");
        resourceSmsLog.setSmsSendTime(new Date());
        resourceSmsLog.setSmsSendStatus(1);

        try {

            Map<String, Object> resultMap = sendCaptcha(mobileNumber,REGISTER_CAPTCHA_TEMPLATE_ID);
            resourceSmsLog.setSmsSendResult(resultMap.toString());
            if((int)resultMap.get("code") != 200){
                resourceSmsLog.setSmsSendStatus(0);
                resultMap.put("success",false);
                resultMap.put("code", resultMap.get("code"));
                resultMap.put("message", resultMap.get("msg"));
                result = new Result<>(ResultCodeConstant.ILLEGAL_ARGUMENT_EXCEPTION,resultMap.get("msg").toString());
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

        Result<Boolean> result = new Result<>();

        ResourceSmsLog resourceSmsLog = new ResourceSmsLog();
        resourceSmsLog.setSmsGatewayType(ResourceSmsGatewayEnum.NETEASE.getCode());
        resourceSmsLog.setSmsTemplateCode(FORGOT_PASSWORD_CAPTCHA_TEMPLATE_ID);
        resourceSmsLog.setSmsTemplateName("密码找回");
        resourceSmsLog.setSmsSendPhone(mobileNumber);
        resourceSmsLog.setSmsSendContent("");
        resourceSmsLog.setSmsSendTime(new Date());
        resourceSmsLog.setSmsSendStatus(1);

        try {

            Map<String, Object> resultMap = sendCaptcha(mobileNumber,FORGOT_PASSWORD_CAPTCHA_TEMPLATE_ID);
            resourceSmsLog.setSmsSendResult(resultMap.toString());
            if((int)resultMap.get("code") != 200){
                resourceSmsLog.setSmsSendStatus(0);
                resultMap.put("success",false);
                resultMap.put("code", resultMap.get("code"));
                resultMap.put("message", resultMap.get("msg"));
                result = new Result<>(ResultCodeConstant.ILLEGAL_ARGUMENT_EXCEPTION,resultMap.get("msg").toString());
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
    public Result<Boolean> checkCaptcha(String mobileNumber, String captcha) {

        Result<Boolean> result = new Result<>();
        try {
            HttpPost httpPost = getHttpPost(SERVER_URL_CHECK_CAPTCHA);

            // 设置请求的的参数，requestBody参数
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("mobile", mobileNumber));
            nvps.add(new BasicNameValuePair("code", captcha));
            nvps.add(new BasicNameValuePair("codeLen", CODELEN));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

            Map<String, Object> resultMap = send(httpPost);

            if((Integer)resultMap.get("code") != 200){
                result = new Result<>(ResultCodeConstant.ILLEGAL_ARGUMENT_EXCEPTION,resultMap.get("msg").toString());
            }

        }catch (Exception e){
            result = new Result<>(ResultCodeConstant.EXCEPTION,e.toString());
        }

        return result;
    }

    /**
     * 根据短信模版编号与手机号发送验证码
     * @param mobileNumber 手机号
     * @param templateId 短信模板编号
     * @return 发送结果
     * { "code": 200, "msg": "88", "obj": "1908" }
     */
    private static Map<String, Object> sendCaptcha(String mobileNumber, String templateId)throws Exception {
        HttpPost httpPost = getHttpPost(SERVER_URL_SEND_CAPTCHA);

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nameValuePairs.add(new BasicNameValuePair("templateid", templateId));
        nameValuePairs.add(new BasicNameValuePair("mobile", mobileNumber));
        nameValuePairs.add(new BasicNameValuePair("codeLen", CODELEN));

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));

        return send(httpPost);
    }

    /**
     * 根据URL生成一个HTTP请求
     * @param url 请求的地址
     * @return HttpPost
     */
    private static HttpPost getHttpPost(String url){
        HttpPost httpPost = new HttpPost(url);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = EncryptionUtil.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpPost;
    }

    /**
     * 发送请求
     * @param httpPost HTTP请求
     * @return 返回结果
     * @throws Exception 请求异常
     */
    private static Map<String, Object> send(HttpPost httpPost) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        String resultString = EntityUtils.toString(response.getEntity(), "utf-8");

        return JSON.parseObject(resultString, new TypeReference<Map<String, Object>>(){});
    }

}