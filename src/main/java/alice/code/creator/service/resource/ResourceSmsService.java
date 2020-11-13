package alice.code.creator.service.resource;


import alice.code.creator.domain.model.Result;

/**
 * 短信发送Service接口
 * User: contact@liuxp.me
 * DateTime: 2018-12-12
 **/
public interface ResourceSmsService{

    /**
     * 发送验证码 - 注册
     * @param mobileNumber 手机号
     */
    Result<Boolean> sendRegisterCaptcha(String mobileNumber);

    /**
     * 发送验证码 - 找回密码
     * @param mobileNumber 手机号
     */
    Result<Boolean> sendForgotPasswordCaptcha(String mobileNumber);

    /**
     * 检查验证码是否正确
     * @param mobileNumber 手机号
     * @param captcha 验证码
     * @return 是否正确
     */
    Result<Boolean> checkCaptcha(String mobileNumber, String captcha);

}