package alice.code.creator.service.resource;

import org.springframework.web.multipart.MultipartFile;
import alice.code.creator.domain.model.resource.ResourceFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * 描述：文件上传下载接口
 * 创建时间：2018/7/30 11:35
 * @author contact@liuxp.me
 */
public interface FileStorageService {

    /**
     * 用户上传图片
     * @param resourceFile 文件存储信息
     * @param multiFile 文件流
     * @param userName 操作人名称
     * @return 文件存储信息
     */
    ResourceFile uploadImage(ResourceFile resourceFile, MultipartFile multiFile, String userName);

    /**
     * 下载图片
     * @param fileKey 文件KEY
     * @param response HTTP返回
     */
    void downloadImage(String fileKey, HttpServletResponse response);

    /**
     * 上传文件
     * @param resourceFile 文件存储信息
     * @param multiFile 文件流
     * @return 文件存储信息
     */
    ResourceFile uploadFile(ResourceFile resourceFile, MultipartFile multiFile, String userName);

    /**
     * 上传文件
     * @param resourceFile 文件存储信息
     * @param inputStream 文件流
     * @param fileName 文件名
     * @param userName 操作人名称
     * @return 文件存储信息
     */
    ResourceFile uploadFile(ResourceFile resourceFile, InputStream inputStream, String fileName, String userName);

    /**
     * 下载文件
     * @param fileKey 文件KEY
     * @param response 返回对象
     * @param request 请求对象
     */
    void downloadFile(String fileKey, HttpServletResponse response, HttpServletRequest request);

    /**
     * 删除文件
     * @param fileKey 文件KEY
     */
    void deleteFile(String fileKey);
}