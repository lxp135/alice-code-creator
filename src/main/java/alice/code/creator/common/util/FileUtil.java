package alice.code.creator.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import alice.code.creator.domain.model.resource.ResourceFile;

import java.util.Date;
import java.util.UUID;

/**
 * 文件处理工具
 * @author contact@liuxp.me
 */
public class FileUtil {

    /**
     * 构建数据
     * @param resourceFile 文件存储信息
     * @param multiFile 文件流
     * @param userName 操作人名称
     * @return ResourceFile
     */
    public static ResourceFile initResourceFile(ResourceFile resourceFile, MultipartFile multiFile, String userName) throws Exception {

        if(null==resourceFile){
            resourceFile = new ResourceFile();
        }

        Date date = new Date();
        String uuid = UUID.randomUUID().toString().replace("-", ""); // 随机文件名32位uuid

        resourceFile.setFileKey(uuid);                                      // 文件KEY
        resourceFile.setFileOriginName(multiFile.getOriginalFilename());    // 原文件名
        resourceFile.setFileCurrentName(uuid);                              // 现文件名
        resourceFile.setFileMimeType(multiFile.getContentType());           // MIME类型
        resourceFile.setFileSuffix(FileUtil.getFileSuffix(multiFile.getOriginalFilename()));// 文件后缀
        resourceFile.setFileSize(multiFile.getSize());                      // 文件大小
        resourceFile.setFileMd5(DigestUtils.md5Hex(multiFile.getBytes()));  // 文件md5
        resourceFile.setIsFinished(1);                                      // 状态 0:未完成 1:已完成
        resourceFile.setCreateUser(userName);
        resourceFile.setCreateTime(date);
        resourceFile.setUpdateUser(userName);
        resourceFile.setUpdateTime(date);

        return resourceFile;

    }

    /**
     * 取得文件扩展名
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String getFileSuffix(String fileName) {
        return fileName != null && fileName.contains(".") && fileName.lastIndexOf(".") != fileName.length() - 1
                ? fileName.substring(fileName.lastIndexOf(".") + 1)
                : "";
    }

    /**
     * 取得文件名
     * @param path 文件全路径名称
     * @return 文件名
     */
    public static String getFileName(String path){
        String fileName;
        fileName = path.trim();
        fileName = fileName.substring(fileName.lastIndexOf("/")+1);
        fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        return fileName;
    }

}