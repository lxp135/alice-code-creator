package alice.code.creator.domain.model.resource;

import alice.code.creator.domain.model.AbstractEntity;

/**
 * 文件存储信息表Domain
 * User: contact@liuxp.me
 * Date: 2018-07-30
 **/
public class ResourceFile extends AbstractEntity {

    /**
     * 文件KEY
     */
    private String fileKey;
    /**
     * 来源类型编码
     */
    private String fileSourceTypeCode;
    /**
     * 来源类型名称
     */
    private String fileSourceTypeName;
    /**
     * 原文件名
     */
    private String fileOriginName;
    /**
     * 现文件名
     */
    private String fileCurrentName;
    /**
     * MIME类型
     */
    private String fileMimeType;
    /**
     * 文件后缀
     */
    private String fileSuffix;
    /**
     * 文件大小
     */
    private Long fileSize;
    /**
     * 文件位置（bucket）
     */
    private String fileLocation;
    /**
     * 文件md5
     */
    private String fileMd5;
    /**
     * 状态 0:未完成 1:已完成
     */
    private Integer isFinished;

    /**
     * 获取文件KEY
     * @return 文件KEY
     */
    public String getFileKey() {
        return fileKey;
    }

    /**
     * 设置文件KEY
     * @param fileKey 文件KEY
     */
    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }
    /**
     * 获取来源类型编码
     * @return 来源类型编码
     */
    public String getFileSourceTypeCode() {
        return fileSourceTypeCode;
    }

    /**
     * 设置来源类型编码
     * @param fileSourceTypeCode 来源类型编码
     */
    public void setFileSourceTypeCode(String fileSourceTypeCode) {
        this.fileSourceTypeCode = fileSourceTypeCode;
    }
    /**
     * 获取来源类型名称
     * @return 来源类型名称
     */
    public String getFileSourceTypeName() {
        return fileSourceTypeName;
    }

    /**
     * 设置来源类型名称
     * @param fileSourceTypeName 来源类型名称
     */
    public void setFileSourceTypeName(String fileSourceTypeName) {
        this.fileSourceTypeName = fileSourceTypeName;
    }
    /**
     * 获取原文件名
     * @return 原文件名
     */
    public String getFileOriginName() {
        return fileOriginName;
    }

    /**
     * 设置原文件名
     * @param fileOriginName 原文件名
     */
    public void setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
    }
    /**
     * 获取现文件名
     * @return 现文件名
     */
    public String getFileCurrentName() {
        return fileCurrentName;
    }

    /**
     * 设置现文件名
     * @param fileCurrentName 现文件名
     */
    public void setFileCurrentName(String fileCurrentName) {
        this.fileCurrentName = fileCurrentName;
    }
    /**
     * 获取MIME类型
     * @return MIME类型
     */
    public String getFileMimeType() {
        return fileMimeType;
    }

    /**
     * 设置MIME类型
     * @param fileMimeType MIME类型
     */
    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }
    /**
     * 获取文件后缀
     * @return 文件后缀
     */
    public String getFileSuffix() {
        return fileSuffix;
    }

    /**
     * 设置文件后缀
     * @param fileSuffix 文件后缀
     */
    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }
    /**
     * 获取文件大小
     * @return 文件大小
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     * @param fileSize 文件大小
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }
    /**
     * 获取文件位置（bucket）
     * @return 文件位置（bucket）
     */
    public String getFileLocation() {
        return fileLocation;
    }

    /**
     * 设置文件位置（bucket）
     * @param fileLocation 文件位置（bucket）
     */
    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
    /**
     * 获取文件md5
     * @return 文件md5
     */
    public String getFileMd5() {
        return fileMd5;
    }

    /**
     * 设置文件md5
     * @param fileMd5 文件md5
     */
    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }
    /**
     * 获取状态 0:未完成 1:已完成
     * @return 状态 0:未完成 1:已完成
     */
    public Integer getIsFinished() {
        return isFinished;
    }

    /**
     * 设置状态 0:未完成 1:已完成
     * @param isFinished 状态 0:未完成 1:已完成
     */
    public void setIsFinished(Integer isFinished) {
        this.isFinished = isFinished;
    }
}