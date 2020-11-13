package alice.code.creator.domain.model.monitor;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;
import alice.code.creator.domain.model.AbstractEntity;

import java.util.Date;

/**
 * 短信发送日志表Domain
 * User: contact@liuxp.me
 * Date: 2018-12-12
 **/
public class ResourceSmsLog extends AbstractEntity{

    /**
     * 手机号
     */
    private String smsSendPhone;
    /**
     * 短信模板编码
     */
    private String smsTemplateCode;
    /**
     * 短信模板名称
     */
    private String smsTemplateName;
    /**
     * 短信网关类型：netease、aliyun
     */
    private String smsGatewayType;
    /**
     * 短信内容
     */
    private String smsSendContent;
    /**
     * 发送状态 0失败 1成功
     */
    private Integer smsSendStatus;
    /**
     * 短信发送时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date smsSendTime;
    /**
     * 短信发送时间/起(虚拟字段：用于时间段查询)
     */
    @Transient
    private String smsSendTimeBegin;
    /**
     * 短信发送时间/止(虚拟字段：用于时间段查询)
     */
    @Transient
    private String smsSendTimeEnd;
    /**
     * 接口返回值
     */
    private String smsSendResult;
    /**
     * 发送异常信息
     */
    private String smsSendExceptionText;

    /**
     * 获取手机号
     * @return 手机号
     */
    public String getSmsSendPhone() {
        return smsSendPhone;
    }

    /**
     * 设置手机号
     * @param smsSendPhone 手机号
     */
    public void setSmsSendPhone(String smsSendPhone) {
        this.smsSendPhone = smsSendPhone;
    }
    /**
     * 获取短信模板编码
     * @return 短信模板编码
     */
    public String getSmsTemplateCode() {
        return smsTemplateCode;
    }

    /**
     * 设置短信模板编码
     * @param smsTemplateCode 短信模板编码
     */
    public void setSmsTemplateCode(String smsTemplateCode) {
        this.smsTemplateCode = smsTemplateCode;
    }
    /**
     * 获取短信模板名称
     * @return 短信模板名称
     */
    public String getSmsTemplateName() {
        return smsTemplateName;
    }

    /**
     * 设置短信模板名称
     * @param smsTemplateName 短信模板名称
     */
    public void setSmsTemplateName(String smsTemplateName) {
        this.smsTemplateName = smsTemplateName;
    }
    /**
     * 获取短信网关类型：netease、aliyun
     * @return 短信网关类型：netease、aliyun
     */
    public String getSmsGatewayType() {
        return smsGatewayType;
    }

    /**
     * 设置短信网关类型：netease、aliyun
     * @param smsGatewayType 短信网关类型：netease、aliyun
     */
    public void setSmsGatewayType(String smsGatewayType) {
        this.smsGatewayType = smsGatewayType;
    }
    /**
     * 获取短信内容
     * @return 短信内容
     */
    public String getSmsSendContent() {
        return smsSendContent;
    }

    /**
     * 设置短信内容
     * @param smsSendContent 短信内容
     */
    public void setSmsSendContent(String smsSendContent) {
        this.smsSendContent = smsSendContent;
    }
    /**
     * 获取发送状态 0失败 1成功
     * @return 发送状态 0失败 1成功
     */
    public Integer getSmsSendStatus() {
        return smsSendStatus;
    }

    /**
     * 设置发送状态 0失败 1成功
     * @param smsSendStatus 发送状态 0失败 1成功
     */
    public void setSmsSendStatus(Integer smsSendStatus) {
        this.smsSendStatus = smsSendStatus;
    }
    /**
     * 获取短信发送时间
     * @return 短信发送时间
     */
    public Date getSmsSendTime() {
        return smsSendTime;
    }

    /**
     * 设置短信发送时间
     * @param smsSendTime 短信发送时间
     */
    public void setSmsSendTime(Date smsSendTime) {
        this.smsSendTime = smsSendTime;
    }

    /**
     * 获取短信发送时间/起(虚拟字段：用于时间段查询)
     * @return 短信发送时间
     */
    public String getSmsSendTimeBegin() {
        return smsSendTimeBegin;
    }

    /**
     * 设置短信发送时间
     * @param smsSendTimeBegin 短信发送时间
     */
    public void setSmsSendTimeBegin(String smsSendTimeBegin) {
        this.smsSendTimeBegin = smsSendTimeBegin;
    }

    /**
     * 获取短信发送时间/止(虚拟字段：用于时间段查询)
     * @return 短信发送时间
     */
    public String getSmsSendTimeEnd() {
        return smsSendTimeEnd;
    }

    /**
     * 设置短信发送时间
     * @param smsSendTimeEnd 短信发送时间
     */
    public void setSmsSendTimeEnd(String smsSendTimeEnd) {
        this.smsSendTimeEnd = smsSendTimeEnd;
    }

    /**
     * 获取接口返回值
     * @return 接口返回值
     */
    public String getSmsSendResult() {
        return smsSendResult;
    }

    /**
     * 设置接口返回值
     * @param smsSendResult 接口返回值
     */
    public void setSmsSendResult(String smsSendResult) {
        this.smsSendResult = smsSendResult;
    }
    /**
     * 获取发送异常信息
     * @return 发送异常信息
     */
    public String getSmsSendExceptionText() {
        return smsSendExceptionText;
    }

    /**
     * 设置发送异常信息
     * @param smsSendExceptionText 发送异常信息
     */
    public void setSmsSendExceptionText(String smsSendExceptionText) {
        this.smsSendExceptionText = smsSendExceptionText;
    }
}