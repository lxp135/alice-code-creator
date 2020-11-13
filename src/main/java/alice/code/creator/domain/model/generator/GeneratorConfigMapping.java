package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;

/**
 * 代码生成器映射关系配置Domain
 * User: contact@liuxp.me
 * Date: 2018-12-26
 **/
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

    /**
     * 获取数据库类型
     * @return 数据库类型
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * 设置数据库类型
     * @param dbType 数据库类型
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }
    /**
     * 获取jdbc类型
     * @return jdbc类型
     */
    public String getJdbcType() {
        return jdbcType;
    }

    /**
     * 设置jdbc类型
     * @param jdbcType jdbc类型
     */
    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
    /**
     * 获取java类型
     * @return java类型
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * 设置java类型
     * @param javaType java类型
     */
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
    /**
     * 获取是否私有 0:公共,1:私有
     * @return 是否私有 0:公共,1:私有
     */
    public Integer getIsPrivate() {
        return isPrivate;
    }

    /**
     * 设置是否私有 0:公共,1:私有
     * @param isPrivate 是否私有 0:公共,1:私有
     */
    public void setIsPrivate(Integer isPrivate) {
        this.isPrivate = isPrivate;
    }
    /**
     * 获取所有者编号
     * @return 所有者编号
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * 设置所有者编号
     * @param ownerId 所有者编号
     */
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    /**
     * 获取所有者名称
     * @return 所有者名称
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置所有者名称
     * @param ownerName 所有者名称
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}