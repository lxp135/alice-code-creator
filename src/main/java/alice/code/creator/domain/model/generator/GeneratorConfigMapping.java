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
     * 数据源类型（MySQL,Oracle,SQLServer）
     */
    private String datasourceType;
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



}