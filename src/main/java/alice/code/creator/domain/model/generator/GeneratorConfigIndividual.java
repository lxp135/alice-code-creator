package alice.code.creator.domain.model.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;

import alice.code.creator.domain.model.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户个性化配置Domain
 * User: contact@liuxp.me
 * Date: 2020-12-12
 **/
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
     * 扩展标识字段（可以存多个，按照都好分割）
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

    /**
     * 获取默认签名
     * @return 默认签名
     */
    public String getDefaultSign() {
        return defaultSign;
    }

    /**
     * 设置默认签名
     * @param defaultSign 默认签名
     */
    public void setDefaultSign(String defaultSign) {
        this.defaultSign = defaultSign;
    }
    /**
     * 获取默认包路径
     * @return 默认包路径
     */
    public String getDefaultPackage() {
        return defaultPackage;
    }

    /**
     * 设置默认包路径
     * @param defaultPackage 默认包路径
     */
    public void setDefaultPackage(String defaultPackage) {
        this.defaultPackage = defaultPackage;
    }
    /**
     * 获取默认表前缀
     * @return 默认表前缀
     */
    public String getDefaultTablePrefix() {
        return defaultTablePrefix;
    }

    /**
     * 设置默认表前缀
     * @param defaultTablePrefix 默认表前缀
     */
    public void setDefaultTablePrefix(String defaultTablePrefix) {
        this.defaultTablePrefix = defaultTablePrefix;
    }
    /**
     * 获取默认分组编号
     * @return 默认分组编号
     */
    public Long getDefaultGroupId() {
        return defaultGroupId;
    }

    /**
     * 设置默认分组编号
     * @param defaultGroupId 默认分组编号
     */
    public void setDefaultGroupId(Long defaultGroupId) {
        this.defaultGroupId = defaultGroupId;
    }
    /**
     * 获取默认分组名称
     * @return 默认分组名称
     */
    public String getDefaultGroupName() {
        return defaultGroupName;
    }

    /**
     * 设置默认分组名称
     * @param defaultGroupName 默认分组名称
     */
    public void setDefaultGroupName(String defaultGroupName) {
        this.defaultGroupName = defaultGroupName;
    }
    /**
     * 获取默认数据源编号
     * @return 默认数据源编号
     */
    public Long getDefaultDatasourceId() {
        return defaultDatasourceId;
    }

    /**
     * 设置默认数据源编号
     * @param defaultDatasourceId 默认数据源编号
     */
    public void setDefaultDatasourceId(Long defaultDatasourceId) {
        this.defaultDatasourceId = defaultDatasourceId;
    }
    /**
     * 获取默认数据源名称
     * @return 默认数据源名称
     */
    public String getDefaultDatasourceName() {
        return defaultDatasourceName;
    }

    /**
     * 设置默认数据源名称
     * @param defaultDatasourceName 默认数据源名称
     */
    public void setDefaultDatasourceName(String defaultDatasourceName) {
        this.defaultDatasourceName = defaultDatasourceName;
    }
    /**
     * 获取唯一标识字段
     * @return 唯一标识字段
     */
    public String getDefaultFieldUnique() {
        return defaultFieldUnique;
    }

    /**
     * 设置唯一标识字段
     * @param defaultFieldUnique 唯一标识字段
     */
    public void setDefaultFieldUnique(String defaultFieldUnique) {
        this.defaultFieldUnique = defaultFieldUnique;
    }
    /**
     * 获取扩展标识字段（可以存多个，按照都好分割）
     * @return 扩展标识字段（可以存多个，按照都好分割）
     */
    public String getDefaultFieldExt() {
        return defaultFieldExt;
    }

    /**
     * 设置扩展标识字段（可以存多个，按照都好分割）
     * @param defaultFieldExt 扩展标识字段（可以存多个，按照都好分割）
     */
    public void setDefaultFieldExt(String defaultFieldExt) {
        this.defaultFieldExt = defaultFieldExt;
    }
    /**
     * 获取逻辑删除标识字段名称
     * @return 逻辑删除标识字段名称
     */
    public String getDefaultFieldEffective() {
        return defaultFieldEffective;
    }

    /**
     * 设置逻辑删除标识字段名称
     * @param defaultFieldEffective 逻辑删除标识字段名称
     */
    public void setDefaultFieldEffective(String defaultFieldEffective) {
        this.defaultFieldEffective = defaultFieldEffective;
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
}