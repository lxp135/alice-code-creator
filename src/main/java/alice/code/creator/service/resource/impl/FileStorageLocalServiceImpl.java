package alice.code.creator.service.resource.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import alice.code.creator.common.util.FileContentType;
import alice.code.creator.common.util.FileUtil;
import alice.code.creator.domain.enums.FileSourceTypeEnum;
import alice.code.creator.domain.model.resource.ResourceFile;
import alice.code.creator.service.resource.FileStorageService;
import alice.code.creator.service.resource.ResourceFileService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 描述：云存储本地实现
 * 注意：当启用本地存储时，web端不能同时多点部署在不同服务器（即不支持分布式）
 * 创建时间：2018/7/30 16:09
 * @author contact@liuxp.me
 */
@Service("fileStorageLocalService")
public class FileStorageLocalServiceImpl implements FileStorageService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 本地文件存储路径
     */
    @Value("${local.path}")
    private String localPath;

    /**
     * 文件存储信息RemoteService
     */
    @Resource
    private ResourceFileService resourceFileService;

    /**
     * 用户上传图片
     * @param resourceFile 文件存储信息
     * @param multiFile 文件流
     * @return 文件存储信息
     */
    @Override
    @Transactional
    public ResourceFile uploadImage(ResourceFile resourceFile, MultipartFile multiFile, String userName) {

        try{
            // 初始化文件存储信息
            resourceFile = FileUtil.initResourceFile(resourceFile,multiFile,userName);
        }catch (Exception e){
            throw new RuntimeException("初始化"+resourceFile.getFileOriginName()+"文件存储信息失败",e);
        }

        ResourceFile resourceFileQuery = new ResourceFile();
        resourceFileQuery.setFileMd5(resourceFile.getFileMd5());
        ResourceFile resourceFileOld = resourceFileService.selectOne(resourceFileQuery);

        if(null!=resourceFileOld){
            // 如果文件MD5值相同，直接返回原文件信息
            return resourceFileOld;
        }

        resourceFile.setFileSourceTypeCode(FileSourceTypeEnum.USER_UPLOAD.getIndex());
        resourceFile.setFileSourceTypeName(FileSourceTypeEnum.USER_UPLOAD.getName());

        // 根据年、月、日存放在不同的文件夹下
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINESE);
        // 相对路径
        String bucketCollect = "";
        if(null!=resourceFile.getFileLocation()&&!"".equals(resourceFile.getFileLocation())){
            bucketCollect += resourceFile.getFileLocation();
            bucketCollect += File.separator;
        }
        String relative_path = new StringBuffer()
                .append(bucketCollect)
                .append(calendar.get(Calendar.YEAR)).append(File.separator)
                .append(calendar.get(Calendar.MONTH)+1).append(File.separator).toString();
        String savePath = localPath + File.separator + relative_path;

        resourceFile.setFileLocation(savePath);

        resourceFile = resourceFileService.insert(resourceFile,userName);
        // 上传文件
        this.saveFile(resourceFile,multiFile);

        return resourceFile;
    }

    /**
     * 下载图片
     * @param fileKey 文件编码
     * @param response HTTP返回
     */
    @Override
    public void downloadImage(String fileKey, HttpServletResponse response) {
        if (null==fileKey||"".equalsIgnoreCase(fileKey)) {
            throw new RuntimeException("文件key不能为空");
        }
        // 获取文件存储信息
        ResourceFile resourceFileQuery = new ResourceFile();
        resourceFileQuery.setFileKey(fileKey);
        ResourceFile resourceFile = resourceFileService.selectOne(resourceFileQuery);
        if(null==resourceFile){
            throw new RuntimeException("该文件不存在");
        }

        // 读取流
        try {

            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(resourceFile.getFileOriginName(), "UTF-8"));
            if(null!=resourceFile.getFileSize()){
                response.setHeader("Content-Length", String.valueOf(resourceFile.getFileSize()));
            }

            // 声明ContentType格式
            String contentType = FileContentType.getContentType("." + resourceFile.getFileSuffix());
            if (null!=resourceFile.getFileMimeType()&&!"".equalsIgnoreCase(resourceFile.getFileMimeType())) {
                contentType = resourceFile.getFileMimeType();
            }
            response.setContentType(contentType);

            OutputStream os = response.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream(resourceFile.getFileLocation()  + resourceFile.getFileKey() + "." + resourceFile.getFileSuffix());

            byte[] temp = new byte[1024];

            int size;
            while((size = fileInputStream.read(temp)) != -1) {
                os.write(temp, 0, size);
            }
            fileInputStream.close();
            os.flush();

        }catch (IOException e){
            logger.error("文件读取失败！",e);
        }

    }

    @Override
    public ResourceFile uploadFile(ResourceFile resourceFile, MultipartFile multiFile, String userName) {

        try {
            return uploadFile(resourceFile,multiFile.getInputStream(),multiFile.getOriginalFilename(),userName);
        }catch (IOException e){
            logger.error("文件上传失败，",e);
            throw new RuntimeException("文件上传失败"+e.getMessage());
        }
    }

    @Override
    public ResourceFile uploadFile(ResourceFile resourceFile, InputStream inputStream, String fileName, String userName) {
        // 初始化文件存储信息
        if(null==resourceFile){
            resourceFile = new ResourceFile();
        }

        Date date = new Date();
        String uuid = UUID.randomUUID().toString().replace("-", ""); // 随机文件名32位uuid

        resourceFile.setFileKey(uuid);                                      // 文件KEY
        resourceFile.setFileOriginName(fileName);    // 原文件名
        resourceFile.setFileCurrentName(uuid);                              // 现文件名
        resourceFile.setFileSuffix(FileUtil.getFileSuffix(fileName));// 文件后缀
        resourceFile.setIsFinished(1);                                      // 状态 0:未完成 1:已完成
        resourceFile.setCreateUser(userName);
        resourceFile.setCreateTime(date);
        resourceFile.setUpdateUser(userName);
        resourceFile.setUpdateTime(date);

        // 根据年、月、日存放在不同的文件夹下
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.CHINESE);
        // 相对路径
        String bucketCollect = "";
        if(null!=resourceFile.getFileLocation()&&!"".equals(resourceFile.getFileLocation())){
            bucketCollect += resourceFile.getFileLocation();
            bucketCollect += File.separator;
        }
        String relative_path = new StringBuffer()
                .append(bucketCollect)
                .append(calendar.get(Calendar.YEAR)).append(File.separator)
                .append(calendar.get(Calendar.MONTH)+1).append(File.separator).toString();
        String savePath = localPath + File.separator + relative_path;

        resourceFile.setFileLocation(savePath);

        resourceFile = resourceFileService.insert(resourceFile,userName);

        // 上传文件
        try {
            this.saveFile(resourceFile,inputStream);
        }catch (IOException e){
            throw new RuntimeException("文件上传失败" + e.getMessage());
        }

        return resourceFile;
    }

    @Override
    public void downloadFile(String fileKey, HttpServletResponse response, HttpServletRequest request) {
        if (null==fileKey||"".equalsIgnoreCase(fileKey)) {
            throw new RuntimeException("文件key不能为空");
        }
        // 获取文件存储信息
        ResourceFile resourceFileQuery = new ResourceFile();
        resourceFileQuery.setFileKey(fileKey);
        ResourceFile resourceFile = resourceFileService.selectOne(resourceFileQuery);
        if(null==resourceFile){
            throw new RuntimeException("该文件不存在");
        }

        // 读取流
        try {
            OutputStream outputStream = response.getOutputStream();

            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setHeader("Content-Disposition", "attachment; filename="+ resourceFile.getFileOriginName());
            if(null!=resourceFile.getFileSize()){
                response.setHeader("Content-Length", String.valueOf(resourceFile.getFileSize()));
            }

            // 声明ContentType格式
            String contentType = FileContentType.getContentType("." + resourceFile.getFileSuffix());
            if (null!=resourceFile.getFileMimeType()&&!"".equalsIgnoreCase(resourceFile.getFileMimeType())) {
                contentType = resourceFile.getFileMimeType();
            }
            response.setContentType(contentType);

            try {

                if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)//火狐乱码问题
                {
                    resourceFile.setFileOriginName(URLDecoder.decode(resourceFile.getFileOriginName(),"UTF-8"));
                    resourceFile.setFileOriginName(new String(resourceFile.getFileOriginName().getBytes("GB2312"),"ISO-8859-1"));
                    response.setHeader("Content-Disposition", "attachment; filename=\""+ resourceFile.getFileOriginName()+ "\"");
                }

            }catch (Exception e){
                e.printStackTrace();
            }

            FileInputStream fileInputStream = new FileInputStream(resourceFile.getFileLocation()  + resourceFile.getFileKey() + "." + resourceFile.getFileSuffix());

            byte[] temp = new byte[1024];

            int size;
            while((size = fileInputStream.read(temp)) != -1) {
                outputStream.write(temp, 0, size);
            }
            fileInputStream.close();
            outputStream.flush();

        }catch (IOException e){
            logger.error("文件读取失败！",e);
        }
    }

    /**
     * 删除文件
     * @param fileKey 文件KEY
     */
    @Override
    public void deleteFile(String fileKey) {
        // 获取文件存储信息
        ResourceFile resourceFileQuery = new ResourceFile();
        resourceFileQuery.setFileKey(fileKey);
        ResourceFile resourceFile = resourceFileService.selectOne(resourceFileQuery);
        if(null==resourceFile){
            logger.error("文件"+fileKey+"不存在！");
        }else{
            String path = resourceFile.getFileLocation() + resourceFile.getFileKey() + "." + resourceFile.getFileSuffix();

            File file = new File(path);
            if (file.exists()) {
                // 文件存在时，进行删除操作
                if(!file.getAbsoluteFile().delete()){
                    logger.error("文件"+path+"物理删除失败！");
                }
            }

            // 物理删除
            resourceFileService.deletePhysically(resourceFile.getId());
        }
    }

    /**
     * 保存文件
     * @param resourceFile 文件信息
     * @param multiFile 文件数据
     */
    private void saveFile(ResourceFile resourceFile, MultipartFile multiFile){
        // 检查目录,如果没有则建立一个新的目录
        File uploadDir = new File(resourceFile.getFileLocation());
        if (!uploadDir.isDirectory()) {
            uploadDir.mkdirs();
        }
        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            System.out.println("上传目录没有写权限。");
            return;
        }

        String filePath = resourceFile.getFileLocation() + resourceFile.getFileKey() + "." + resourceFile.getFileSuffix();
        File desFile = new File(filePath);
        if(!desFile.getParentFile().exists()){
            desFile.mkdirs();
        }
        try {
            multiFile.transferTo(desFile);
        } catch (IllegalStateException | IOException e) {
            logger.error("文件"+resourceFile.getFileOriginName()+"上传失败！",e);
            e.printStackTrace();
        }
    }

    private void saveFile(ResourceFile resourceFile, InputStream inputStream) throws IOException {

        // 检查目录,如果没有则建立一个新的目录
        File uploadDir = new File(resourceFile.getFileLocation());
        if (!uploadDir.isDirectory()) {
            uploadDir.mkdirs();
        }
        // 检查目录写权限
        if (!uploadDir.canWrite()) {
            System.out.println("上传目录没有写权限。");
            return;
        }

        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream file = new FileOutputStream(resourceFile.getFileLocation() + resourceFile.getFileKey() + "." + resourceFile.getFileSuffix());
        while ((index = inputStream.read(bytes)) != -1) {
            file.write(bytes, 0, index);
            file.flush();
        }
        file.close();
        inputStream.close();

    }

}