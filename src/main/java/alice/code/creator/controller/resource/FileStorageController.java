package alice.code.creator.controller.resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import alice.code.creator.common.framework.account.AccountContext;
import alice.code.creator.common.framework.account.AccountUtils;
import alice.code.creator.domain.enums.FileSourceTypeEnum;
import alice.code.creator.domain.model.resource.ResourceFile;
import alice.code.creator.service.resource.FileStorageService;
import alice.code.creator.service.resource.ResourceFileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 描述：文件/图片上传下载
 * 创建时间：2018/7/30 16:22
 * @author contact@liuxp.me
 */
@Controller
@RequestMapping("/web/resource/file")
@Secured("PUBLIC")
public class FileStorageController {

    /**
     * 图片桶
     */
    private static final String bucketImage = "image";

    /**
     * 文件桶
     */
    private static final String bucketFile = "file";

    /**
     * 文件存储服务
     */
    @Resource(name = "fileStorageLocalService")
    private FileStorageService fileStorageService;

    /**
     * 文件存储信息RemoteService
     */
    @Resource
    private ResourceFileService resourceFileService;

    /**
     * 上传图片
     * @param multipartFile 文件数据
     * @return 文件信息
     */
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ResourceFile uploadImage(@RequestParam("file") MultipartFile multipartFile) {

        ResourceFile resourceFile = new ResourceFile();

        resourceFile.setFileSourceTypeCode(FileSourceTypeEnum.USER_UPLOAD.getIndex());
        resourceFile.setFileSourceTypeName(FileSourceTypeEnum.USER_UPLOAD.getName());
        resourceFile.setFileLocation(bucketImage);

        return fileStorageService.uploadImage(resourceFile,multipartFile,AccountContext.getAccount().getUserName());
    }

    /**
     * 上传文件
     * @param multipartFile 文件数据
     * @return 文件信息
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public ResourceFile uploadFile(@RequestParam("file") MultipartFile multipartFile) {

        ResourceFile resourceFile = new ResourceFile();
        resourceFile.setFileSourceTypeCode(FileSourceTypeEnum.USER_UPLOAD.getIndex());
        resourceFile.setFileSourceTypeName(FileSourceTypeEnum.USER_UPLOAD.getName());
        resourceFile.setFileLocation(bucketFile);

        return fileStorageService.uploadFile(resourceFile, multipartFile, AccountUtils.getCurrentUser().getUserName());
    }

    /**
     * 下载图片
     * @param fileKey 文件编号
     * @param response 请求返回
     */
    @RequestMapping(value = "/downloadImage/{fileKey}", method = RequestMethod.GET)
    @ResponseBody
    public void downloadImage(@PathVariable("fileKey") String fileKey, HttpServletResponse response) {
        fileStorageService.downloadImage(fileKey,response);
    }

    /**
     * 下载文件
     * @param fileKey 文件编号
     * @param response 请求返回
     */
    @RequestMapping(value = "/download/{fileKey}", method = RequestMethod.GET)
    @ResponseBody
    public void download(@PathVariable("fileKey") String fileKey, HttpServletResponse response, HttpServletRequest request) {
        fileStorageService.downloadFile(fileKey,response,request);
    }

    /**
     * 取得文件信息
     * @param fileKey 文件编号
     * @return 文件信息
     */
    @RequestMapping(value = "/getResourceFileInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResourceFile getResourceFileInfo(String fileKey){
        ResourceFile resourceFileQuery = new ResourceFile();
        resourceFileQuery.setFileKey(fileKey);
        List<ResourceFile> resourceFileList = resourceFileService.selectList(resourceFileQuery);

        if(resourceFileList.size()>0){
            return resourceFileList.get(0);
        }else{
            return null;
        }
    }

}