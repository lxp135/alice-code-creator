package alice.code.creator.domain.model.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;

import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据源配置Domain
 * User: contact@liuxp.me
 * Date: 2021-01-04
 **/
@Setter
@Getter
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

}