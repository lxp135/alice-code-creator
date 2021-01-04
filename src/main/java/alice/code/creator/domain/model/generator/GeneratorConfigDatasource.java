package alice.code.creator.domain.model.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;

import alice.code.creator.domain.model.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据源配置Domain
 * User: contact@liuxp.me
 * Date: 2021-01-04
 **/
public class GeneratorConfigDatasource extends AbstractEntity{

    /**
     * 数据源名称
     */
    private String datasourceName;
    /**
     * 数据源名称(虚拟字段：用于模糊查询)
     */
    @Transient
    private String datasourceNameLike;
    /**
     * 数据源类型（MySQL,Oracle,SQLServer）
     */
    private String datasourceType;
    /**
     * JDBC驱动
     */
    private String driverClassName;
    /**
     * 数据源地址
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 所有者用户编号
     */
    private Long ownerUserId;
    /**
     * 所有者用户名称
     */
    private String ownerUserName;
    /**
     * 所有者用户名称(虚拟字段：用于模糊查询)
     */
    @Transient
    private String ownerUserNameLike;

    /**
     * 获取数据源名称
     * @return 数据源名称
     */
    public String getDatasourceName() {
        return datasourceName;
    }
    /**
     * 设置数据源名称
     * @param datasourceName 数据源名称
     */
    public void setDatasourceName(String datasourceName) {
        this.datasourceName = datasourceName;
    }
    /**
     * 获取数据源名称(虚拟字段：用于模糊查询)
     * @return 数据源名称
     */
    public String getDatasourceNameLike() {
        return datasourceNameLike;
    }
    /**
     * 设置数据源名称(虚拟字段：用于模糊查询)
     * @param datasourceNameLike 数据源名称
     */
    public void setDatasourceNameLike(String datasourceNameLike) {
        this.datasourceNameLike = datasourceNameLike;
    }

    /**
     * 获取数据源类型（MySQL,Oracle,SQLServer）
     * @return 数据源类型（MySQL,Oracle,SQLServer）
     */
    public String getDatasourceType() {
        return datasourceType;
    }

    /**
     * 设置数据源类型（MySQL,Oracle,SQLServer）
     * @param datasourceType 数据源类型（MySQL,Oracle,SQLServer）
     */
    public void setDatasourceType(String datasourceType) {
        this.datasourceType = datasourceType;
    }
    /**
     * 获取JDBC驱动
     * @return JDBC驱动
     */
    public String getDriverClassName() {
        return driverClassName;
    }

    /**
     * 设置JDBC驱动
     * @param driverClassName JDBC驱动
     */
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
    /**
     * 获取数据源地址
     * @return 数据源地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置数据源地址
     * @param url 数据源地址
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * 获取密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 获取所有者用户编号
     * @return 所有者用户编号
     */
    public Long getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * 设置所有者用户编号
     * @param ownerUserId 所有者用户编号
     */
    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }
    /**
     * 获取所有者用户名称
     * @return 所有者用户名称
     */
    public String getOwnerUserName() {
        return ownerUserName;
    }
    /**
     * 设置所有者用户名称
     * @param ownerUserName 所有者用户名称
     */
    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }
    /**
     * 获取所有者用户名称(虚拟字段：用于模糊查询)
     * @return 所有者用户名称
     */
    public String getOwnerUserNameLike() {
        return ownerUserNameLike;
    }
    /**
     * 设置所有者用户名称(虚拟字段：用于模糊查询)
     * @param ownerUserNameLike 所有者用户名称
     */
    public void setOwnerUserNameLike(String ownerUserNameLike) {
        this.ownerUserNameLike = ownerUserNameLike;
    }

}