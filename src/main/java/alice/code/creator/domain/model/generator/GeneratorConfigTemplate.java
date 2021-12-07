package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 代码生成器模板Domain
 * User: contact@liuxp.me
 * Date: 2020-12-12
 **/
@Setter
@Getter
public class GeneratorConfigTemplate extends AbstractEntity{

    /**
     * 模板英文名
     */
    private String templateCode;
    /**
     * 模板中文名
     */
    private String templateName;
    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 生成地址相对路径
     */
    private String filePath;
    /**
     * 文件后缀与扩展名
     */
    private String fileName;
    /**
     * 分组编号
     */
    private Long groupId;
    /**
     * 分组名称
     */
    private String groupName;

}