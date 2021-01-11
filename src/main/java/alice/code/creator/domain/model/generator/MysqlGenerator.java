package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;

/**
 * 字段信息实体类
 * @author contact@liuxp.me
 */
public class MysqlGenerator extends AbstractEntity {

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


    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getColumnJson() {
        return columnJson;
    }

    public void setColumnJson(String columnJson) {
        this.columnJson = columnJson;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
