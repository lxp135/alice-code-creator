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
public class MysqlGenerator extends AbstractEntity {

    /**
     * 数据源编号
     */
    private Long datasource;
    /**
     * 数据库名
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;
    /**
     * 表前缀
     */
    private String tablePrefix;
    /**
     * 打包路径
     */
    private String packagePath;
    /**
     * 创建者
     */
    private String author;
    /**
     * 字段JSON
     */
    private String columnJson;
    /**
     * 分组编号
     */
    private Long groupId;

}