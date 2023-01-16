package alice.code.creator.domain.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：数据来源类型
 * 创建时间：2020/12/31
 * @author contact@liuxp.me
 */
public enum DatasourceTypeEnum {

    MySQL("MySQL", "MySQL","com.mysql.cj.jdbc.Driver"),
    Oracle("Oracle", "Oracle","oracle.jdbc.driver.OracleDriver"),
    SQLServer("SQLServer", "SQLServer","com.microsoft.sqlserver.jdbc.SQLServerDriver"),
    ClickHouse("ClickHouse", "ClickHouse","com.clickhouse.jdbc.ClickHouseDriver");

    // 成员变量
    private final String code;
    private final String name;
    private final String drive;

    // 构造方法
    DatasourceTypeEnum(String code,String name,String drive) {
        this.code = code;
        this.name = name;
        this.drive = drive;
    }

    /**
     * 获取编码
     * @return 编码
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 获取名称
     * @return 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取默认驱动
     * @return 名称
     */
    public String getDrive() {
        return this.drive;
    }

    /**
     * 根据编码获取名称
     * @param code 编码
     * @return 名称
     */
    public static String getName(String code) {
        String name = "";
        for (DatasourceTypeEnum element : DatasourceTypeEnum.values()) {
            if (code.equals(element.getCode())) {
                name = element.getName();
                break;
            }
        }
        return name;
    }

    /**
     * 获取List列表
     */
    public static List<Map<String, Object>> getList() {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DatasourceTypeEnum element : DatasourceTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", element.getCode());
            map.put("name", element.getName());
            map.put("drive", element.getDrive());
            list.add(map);
        }
        return list;
    }
}
