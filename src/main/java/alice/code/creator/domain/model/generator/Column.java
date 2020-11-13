package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;

/**
 * 字段信息实体类
 * @author contact@liuxp.me
 */
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

    public String getColumnNameCH() {
        return columnNameCH;
    }

    public void setColumnNameCH(String columnNameCH) {
        this.columnNameCH = columnNameCH;
    }

    public String getColumnNameEN() {
        return columnNameEN;
    }

    public void setColumnNameEN(String columnNameEN) {
        this.columnNameEN = columnNameEN;
    }

    public String getColumnPropertyName() {
        return columnPropertyName;
    }

    public void setColumnPropertyName(String columnPropertyName) {
        this.columnPropertyName = columnPropertyName;
    }

    public String getColumnMethodName() {
        return columnMethodName;
    }

    public void setColumnMethodName(String columnMethodName) {
        this.columnMethodName = columnMethodName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getAutoFill() {
        return autoFill;
    }

    public void setAutoFill(String autoFill) {
        this.autoFill = autoFill;
    }

    public Boolean getLikeFlag() {
        return likeFlag;
    }

    public void setLikeFlag(Boolean likeFlag) {
        this.likeFlag = likeFlag;
    }

    public Boolean getIsNullable() {
        return this.isNullable;
    }

    public void setIsNullable(Boolean isNullable) {
        this.isNullable = isNullable;
    }
}
