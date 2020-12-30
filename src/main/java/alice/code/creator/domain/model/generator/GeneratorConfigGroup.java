package alice.code.creator.domain.model.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;

import alice.code.creator.domain.model.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 模板分组Domain
 * User: contact@liuxp.me
 * Date: 2020-12-12
 **/
public class GeneratorConfigGroup extends AbstractEntity{

    /**
     * 分组名称
     */
    private String groupName;
    /**
     * 分组内容描述
     */
    private String groupDesc;
    /**
     * 模板总数
     */
    private Integer templateAmount;
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