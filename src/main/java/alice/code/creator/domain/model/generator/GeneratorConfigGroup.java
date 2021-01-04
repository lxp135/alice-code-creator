package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.Transient;
import alice.code.creator.domain.model.AbstractEntity;

/**
 * 模板分组Domain
 * User: contact@liuxp.me
 * Date: 2020-12-31
 **/
public class GeneratorConfigGroup extends AbstractEntity{

    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 分组名称(虚拟字段：用于模糊查询)
     */
    @Transient
    private String groupNameLike;
    /**
     * 分组内容描述
     */
    private String groupDesc;
    /**
     * 模板总数
     */
    private Integer templateAmount;
    /**
     * 默认签名
     */
    private String defaultSign;
    /**
     * 默认包路径
     */
    private String defaultPackage;
    /**
     * 默认表前缀
     */
    private String defaultTablePrefix;
    /**
     * 唯一标识字段
     */
    private String defaultFieldUnique;
    /**
     * 扩展标识字段（可以存多个，按照逗号分割）
     */
    private String defaultFieldExt;
    /**
     * 逻辑删除标识字段名称
     */
    private String defaultFieldEffective;
    /**
     * 所有者用户编号
     */
    private Long ownerUserId;
    /**
     * 所有者用户名称
     */
    private String ownerUserName;
    /**
     * 是否公开 0：私有 1：公开
     */
    private Integer isPublic;

    /**
     * 获取分组名称
     * @return 分组名称
     */
    public String getGroupName() {
        return groupName;
    }
    /**
     * 设置分组名称
     * @param groupName 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /**
     * 获取分组名称(虚拟字段：用于模糊查询)
     * @return 分组名称
     */
    public String getGroupNameLike() {
        return groupNameLike;
    }
    /**
     * 设置分组名称(虚拟字段：用于模糊查询)
     * @param groupNameLike 分组名称
     */
    public void setGroupNameLike(String groupNameLike) {
        this.groupNameLike = groupNameLike;
    }

    /**
     * 获取分组内容描述
     * @return 分组内容描述
     */
    public String getGroupDesc() {
        return groupDesc;
    }

    /**
     * 设置分组内容描述
     * @param groupDesc 分组内容描述
     */
    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
    /**
     * 获取模板总数
     * @return 模板总数
     */
    public Integer getTemplateAmount() {
        return templateAmount;
    }

    /**
     * 设置模板总数
     * @param templateAmount 模板总数
     */
    public void setTemplateAmount(Integer templateAmount) {
        this.templateAmount = templateAmount;
    }
    /**
     * 获取默认签名
     * @return 默认签名
     */
    public String getDefaultSign() {
        return defaultSign;
    }

    /**
     * 设置默认签名
     * @param defaultSign 默认签名
     */
    public void setDefaultSign(String defaultSign) {
        this.defaultSign = defaultSign;
    }
    /**
     * 获取默认包路径
     * @return 默认包路径
     */
    public String getDefaultPackage() {
        return defaultPackage;
    }

    /**
     * 设置默认包路径
     * @param defaultPackage 默认包路径
     */
    public void setDefaultPackage(String defaultPackage) {
        this.defaultPackage = defaultPackage;
    }
    /**
     * 获取默认表前缀
     * @return 默认表前缀
     */
    public String getDefaultTablePrefix() {
        return defaultTablePrefix;
    }

    /**
     * 设置默认表前缀
     * @param defaultTablePrefix 默认表前缀
     */
    public void setDefaultTablePrefix(String defaultTablePrefix) {
        this.defaultTablePrefix = defaultTablePrefix;
    }
    /**
     * 获取唯一标识字段
     * @return 唯一标识字段
     */
    public String getDefaultFieldUnique() {
        return defaultFieldUnique;
    }

    /**
     * 设置唯一标识字段
     * @param defaultFieldUnique 唯一标识字段
     */
    public void setDefaultFieldUnique(String defaultFieldUnique) {
        this.defaultFieldUnique = defaultFieldUnique;
    }
    /**
     * 获取扩展标识字段（可以存多个，按照逗号分割）
     * @return 扩展标识字段（可以存多个，按照逗号分割）
     */
    public String getDefaultFieldExt() {
        return defaultFieldExt;
    }

    /**
     * 设置扩展标识字段（可以存多个，按照逗号分割）
     * @param defaultFieldExt 扩展标识字段（可以存多个，按照逗号分割）
     */
    public void setDefaultFieldExt(String defaultFieldExt) {
        this.defaultFieldExt = defaultFieldExt;
    }
    /**
     * 获取逻辑删除标识字段名称
     * @return 逻辑删除标识字段名称
     */
    public String getDefaultFieldEffective() {
        return defaultFieldEffective;
    }

    /**
     * 设置逻辑删除标识字段名称
     * @param defaultFieldEffective 逻辑删除标识字段名称
     */
    public void setDefaultFieldEffective(String defaultFieldEffective) {
        this.defaultFieldEffective = defaultFieldEffective;
    }
    /**
     * 获取所有者用户编号
     * @return 所有者用户编号
     */
    public Long getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * 设置所有者用户编号
     * @param ownerUserId 所有者用户编号
     */
    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }
    /**
     * 获取所有者用户名称
     * @return 所有者用户名称
     */
    public String getOwnerUserName() {
        return ownerUserName;
    }

    /**
     * 设置所有者用户名称
     * @param ownerUserName 所有者用户名称
     */
    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }
    /**
     * 获取是否公开 0：私有 1：公开
     * @return 是否公开 0：私有 1：公开
     */
    public Integer getIsPublic() {
        return isPublic;
    }

    /**
     * 设置是否公开 0：私有 1：公开
     * @param isPublic 是否公开 0：私有 1：公开
     */
    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }
}