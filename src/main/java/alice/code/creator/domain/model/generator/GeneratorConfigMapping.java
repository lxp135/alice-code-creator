package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 代码生成器映射关系配置Domain
 * User: contact@liuxp.me
 * Date: 2018-12-26
 **/
@Setter
@Getter
public class GeneratorConfigMapping extends AbstractEntity{

    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * jdbc类型
     */
    private String jdbcType;
    /**
     * java类型
     */
    private String javaType;
    /**
     * 是否私有 0:公共,1:私有
     */
    private Integer isPrivate;
    /**
     * 所有者编号
     */
    private Long ownerId;
    /**
     * 所有者名称
     */
    private String ownerName;

}