package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户个性化配置Domain
 * User: contact@liuxp.me
 * Date: 2020-12-12
 **/
@Setter
@Getter
public class GeneratorConfigIndividual extends AbstractEntity{

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
     * 默认分组编号
     */
    private Long defaultGroupId;
    /**
     * 默认分组名称
     */
    private String defaultGroupName;
    /**
     * 默认数据源编号
     */
    private Long defaultDatasourceId;
    /**
     * 默认数据源名称
     */
    private String defaultDatasourceName;
    /**
     * 唯一标识字段
     */
    private String defaultFieldUnique;
    /**
     * 扩展标识字段（可以存多个，按逗号分割）
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

}