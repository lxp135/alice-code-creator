package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.Transient;
import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 模板分组Domain
 * User: contact@liuxp.me
 * Date: 2020-12-31
 **/
@Setter
@Getter
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
     * 当前登录用户编号
     */
    private Long loginUserId;

}