package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 字段信息(用于解析模板)
 * @author contact@liuxp.me
 */
@Setter
@Getter
public class ColumnGenerator extends AbstractEntity {

    // 数据库名
    private String tableSchema;
    // 表名
    private String tableName;
    // 表注释
    private String tableComment;
    // 字段名
    private String columnName;
    // 数据类型
    private String dataType;
    // 主键
    private String columnKey;
    // 主键策略
    private String extra;
    // 是否可以为空 YES NO
    private String isNullable;
    // 字段备注
    private String columnComment;
    // 字段最大长度
    private String maxLength;
    //
    private String uniqueFlag;
    //
    private String likeFlag;
    //
    private String extFlag;
    //
    private String effectiveFlag;
    //
    private String filterFlag;
    //
    private String autoFill;

}
