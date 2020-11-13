package alice.code.creator.domain.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：文件来源编码
 * 创建时间：2018/8/1 15:11
 * @author contact@liuxp.me
 */
public enum FileSourceTypeEnum {

    USER_UPLOAD("用户上传", "user_upload"),
    SYSTEM_COLLECT("系统采集", "system_collect"),
    SYSTEM_CREATE("系统创建", "system_create");

    // 成员变量
    private final String name;
    private final String index;

    // 构造方法
    FileSourceTypeEnum(String name, String index) {
        this.name = name;
        this.index = index;
    }

    /**
     * 获取名称
     *
     * @return 名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 获取索引
     *
     * @return 索引
     */
    public String getIndex() {
        return this.index;
    }

    /**
     * 根据索引获取名称
     *
     * @param index 索引
     * @return 名称
     */
    public static String getName(String index) {
        String name = "";
        for (FileSourceTypeEnum element : FileSourceTypeEnum.values()) {
            if (index.equals(element.getIndex())) {
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
        for (FileSourceTypeEnum element : FileSourceTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>();
            map.put("index", element.getIndex());
            map.put("name", element.getName());
            list.add(map);
        }
        return list;
    }
}
