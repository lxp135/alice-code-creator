package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;

/**
 * 代码生成器模板配置Domain
 * User: contact@liuxp.me
 * Date: 2018-12-26
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