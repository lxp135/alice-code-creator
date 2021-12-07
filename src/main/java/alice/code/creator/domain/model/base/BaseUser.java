package alice.code.creator.domain.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;
import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 用户基本信息表Domain
 * User: contact@liuxp.me
 * Date: 2018-07-17
 **/
@Setter
@Getter
public class BaseUser extends AbstractEntity{

    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户名称(虚拟字段：用于模糊查询)
     */
    @Transient
    private String userNameLike;
    /**
     * 用户密码
     */
    private String userPassword;
    /**
     * 性别 0:未填写 1:男 2:女
     */
    private Integer userSex;
    /**
     * 用户头像
     */
    private String userFace;
    /**
     * 电子邮件
     */
    private String userEmail;
    /**
     * 微信号
     */
    private String userWechat;
    /**
     * 手机号
     */
    private String userPhone;
    /**
     * 出生日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date userBirthday;
    /**
     * 出生日期/起(虚拟字段：用于时间段查询)
     */
    @Transient
    private String userBirthdayBegin;
    /**
     * 出生日期/止(虚拟字段：用于时间段查询)
     */
    @Transient
    private String userBirthdayEnd;
    /**
     * 签名
     */
    private String userSignature;
    /**
     * 用户状态 0:禁用 1:正常
     */
    private Integer isEnable;

    // 角色编号
    @Transient
    private Long roleId;
    // 当前角色名称
    @Transient
    private String roleName;
    // 全部角色列表
    @Transient
    private List<BaseRole> baseRoles;
    // 当前角色菜单列表
    @Transient
    private List<BaseMenu> baseMenus;
    // 短信验证码
    @Transient
    private String smsCaptcha;

}