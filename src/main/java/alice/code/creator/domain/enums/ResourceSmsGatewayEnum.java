package alice.code.creator.domain.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：短信云网关
 * 创建时间：2018/12/12
 * @author contact@liuxp.me
 */
public enum ResourceSmsGatewayEnum {

    NETEASE("netease","网易云"),
    ALIYUN("aliyun","阿里云");

    // 编码
    private String code;

    // 名称
    private String name;


    ResourceSmsGatewayEnum(String code, String name){
        this.code = code;
        this.name = name;
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
     * 获取List列表
     */
    public static List<ResourceSmsGatewayEnum> getList() {
        List<ResourceSmsGatewayEnum> list = new ArrayList<>();
        for (ResourceSmsGatewayEnum element : ResourceSmsGatewayEnum.values()) {
            list.add(element);
        }

        return list;
    }

    /**
     * 根据名称取得枚举
     * @param name 名称
     * @return 枚举
     */
    public static ResourceSmsGatewayEnum getInstanceByName(String name){
        for (ResourceSmsGatewayEnum element : ResourceSmsGatewayEnum.values()) {
            if(element.getName().equals(name)){
                return element;
            }
        }
        return null;
    }

    /**
     * 根据编码取得枚举
     * @param code 名称
     * @return 枚举
     */
    public static ResourceSmsGatewayEnum getInstanceByCode(String code){
        for (ResourceSmsGatewayEnum element : ResourceSmsGatewayEnum.values()) {
            if(element.getCode().equals(code)){
                return element;
            }
        }
        return null;
    }

}