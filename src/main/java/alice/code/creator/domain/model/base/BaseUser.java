package alice.code.creator.domain.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;
import alice.code.creator.domain.model.AbstractEntity;

import java.util.Date;
import java.util.List;

/**
 * 用户基本信息表Domain
 * User: contact@liuxp.me
 * Date: 2018-07-17
 **/
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

    /**
     * 获取用户账号
     * @return 用户账号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户账号
     * @param userAccount 用户账号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    /**
     * 获取用户名称
     * @return 用户名称
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置用户名称
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取用户名称(虚拟字段：用于模糊查询)
     * @return 用户名称
     */
    public String getUserNameLike() {
        return userNameLike;
    }
    /**
     * 设置用户名称(虚拟字段：用于模糊查询)
     * @param userNameLike 用户名称
     */
    public void setUserNameLike(String userNameLike) {
        this.userNameLike = userNameLike;
    }

    /**
     * 获取用户密码
     * @return 用户密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置用户密码
     * @param userPassword 用户密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * 获取性别 0:未填写 1:男 2:女
     * @return 性别 0:未填写 1:男 2:女
     */
    public Integer getUserSex() {
        return userSex;
    }

    /**
     * 设置性别 0:未填写 1:男 2:女
     * @param userSex 性别 0:未填写 1:男 2:女
     */
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }
    /**
     * 获取用户头像
     * @return 用户头像
     */
    public String getUserFace() {
        return userFace;
    }

    /**
     * 设置用户头像
     * @param userFace 用户头像
     */
    public void setUserFace(String userFace) {
        this.userFace = userFace;
    }
    /**
     * 获取电子邮件
     * @return 电子邮件
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置电子邮件
     * @param userEmail 电子邮件
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    /**
     * 获取微信号
     * @return 微信号
     */
    public String getUserWechat() {
        return userWechat;
    }

    /**
     * 设置微信号
     * @param userWechat 微信号
     */
    public void setUserWechat(String userWechat) {
        this.userWechat = userWechat;
    }
    /**
     * 获取手机号
     * @return 手机号
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置手机号
     * @param userPhone 手机号
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    /**
     * 获取出生日期
     * @return 出生日期
     */
    public Date getUserBirthday() {
        return userBirthday;
    }

    /**
     * 设置出生日期
     * @param userBirthday 出生日期
     */
    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    /**
     * 获取出生日期/起(虚拟字段：用于时间段查询)
     * @return 出生日期
     */
    public String getUserBirthdayBegin() {
        return userBirthdayBegin;
    }

    /**
     * 设置出生日期
     * @param userBirthdayBegin 出生日期
     */
    public void setUserBirthdayBegin(String userBirthdayBegin) {
        this.userBirthdayBegin = userBirthdayBegin;
    }

    /**
     * 获取出生日期/止(虚拟字段：用于时间段查询)
     * @return 出生日期
     */
    public String getUserBirthdayEnd() {
        return userBirthdayEnd;
    }

    /**
     * 设置出生日期
     * @param userBirthdayEnd 出生日期
     */
    public void setUserBirthdayEnd(String userBirthdayEnd) {
        this.userBirthdayEnd = userBirthdayEnd;
    }

    /**
     * 获取签名
     * @return 签名
     */
    public String getUserSignature() {
        return userSignature;
    }

    /**
     * 设置签名
     * @param userSignature 签名
     */
    public void setUserSignature(String userSignature) {
        this.userSignature = userSignature;
    }

    /**
     * 获取用户状态 0:禁用 1:正常
     * @return 用户状态 0:禁用 1:正常
     */
    public Integer getIsEnable() {
        return isEnable;
    }

    /**
     * 设置用户状态 0:禁用 1:正常
     * @param isEnable 用户状态 0:禁用 1:正常
     */
    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<BaseRole> getBaseRoles() {
        return baseRoles;
    }

    public void setBaseRoles(List<BaseRole> baseRoles) {
        this.baseRoles = baseRoles;
    }

    public List<BaseMenu> getBaseMenus() {
        return baseMenus;
    }

    public void setBaseMenus(List<BaseMenu> baseMenus) {
        this.baseMenus = baseMenus;
    }

    public String getSmsCaptcha() {
        return smsCaptcha;
    }

    public void setSmsCaptcha(String smsCaptcha) {
        this.smsCaptcha = smsCaptcha;
    }
}