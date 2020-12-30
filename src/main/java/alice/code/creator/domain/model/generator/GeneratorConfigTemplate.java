package alice.code.creator.domain.model.generator;

import com.fasterxml.jackson.annotation.JsonFormat;
import alice.code.creator.domain.Transient;

import alice.code.creator.domain.model.AbstractEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 代码生成器模板Domain
 * User: contact@liuxp.me
 * Date: 2020-12-12
 **/
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

    /**
     * 获取模板英文名
     * @return 模板英文名
     */
    public String getTemplateCode() {
        return templateCode;
    }

    /**
     * 设置模板英文名
     * @param templateCode 模板英文名
     */
    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
    /**
     * 获取模板中文名
     * @return 模板中文名
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * 设置模板中文名
     * @param templateName 模板中文名
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    /**
     * 获取模板内容
     * @return 模板内容
     */
    public String getTemplateContent() {
        return templateContent;
    }

    /**
     * 设置模板内容
     * @param templateContent 模板内容
     */
    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }
    /**
     * 获取生成地址相对路径
     * @return 生成地址相对路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置生成地址相对路径
     * @param filePath 生成地址相对路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    /**
     * 获取文件后缀与扩展名
     * @return 文件后缀与扩展名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件后缀与扩展名
     * @param fileName 文件后缀与扩展名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**
     * 获取分组编号
     * @return 分组编号
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * 设置分组编号
     * @param groupId 分组编号
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    /**
     * 获取分组名称
     * @return 分组名称
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置分组名称
     * @param groupName 分组名称
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}