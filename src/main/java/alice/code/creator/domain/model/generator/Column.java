package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 字段信息实体类
 * @author contact@liuxp.me
 */
@Setter
@Getter
public class Column extends AbstractEntity {

    /**
     * 字段中文名称
     */
    private String columnNameCH;
    /**
     * 字段英文名称
     */
    private String columnNameEN;
    /**
     * 字段属性名称
     */
    private String columnPropertyName;
    /**
     * 字段方法名称
     */
    private String columnMethodName;
    /**
     * Jdbc数据类型
     */
    private String jdbcType;
    /**
     * Java数据类型
     */
    private String javaType;
    /**
     * 自动填充
     */
    private String autoFill;
    /**
     * 模糊标识
     */
    private Boolean likeFlag;
    /**
     * 是否可以为空
     */
    private Boolean isNullable;
    /**
     * 字段长度
     */
    private String maxLength;

}
