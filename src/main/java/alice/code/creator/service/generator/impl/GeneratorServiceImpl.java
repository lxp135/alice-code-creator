package alice.code.creator.service.generator.impl;

import alice.code.creator.common.framework.context.BusinessException;
import alice.code.creator.common.util.FileZipUtils;
import alice.code.creator.common.util.StringFormatUtils;
import alice.code.creator.common.util.VelocityUtils;
import alice.code.creator.domain.enums.DatasourceTypeEnum;
import alice.code.creator.domain.model.generator.*;
import alice.code.creator.service.generator.GeneratorConfigDatasourceService;
import alice.code.creator.service.generator.GeneratorConfigMappingService;
import alice.code.creator.service.generator.GeneratorConfigTemplateService;
import alice.code.creator.service.generator.GeneratorService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * MYSQL元数据生成代码Service实现类
 * @author contact@liuxp.me
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    // 模板Service
    @Resource
    private GeneratorConfigTemplateService generatorConfigTemplateService;

    // 数据类型映射Service
    @Resource
    private GeneratorConfigMappingService generatorConfigMappingService;

    // 数据源Service
    @Resource
    private GeneratorConfigDatasourceService generatorConfigDatasourceService;

    // 代码生成保存路径
    private static final String GENERATOR_PATH = "generator";

    @Value("${spring.datasource.driver-class-name}")
    private String defaultDriverClassName;

    @Value("${spring.datasource.url}")
    private String defaultUrl;

    @Value("${spring.datasource.username}")
    private String defaultUsername;

    @Value("${spring.datasource.password}")
    private String defaultPassword;

    /**
     * 生成代码-压缩包下载
     */
    public ResponseEntity<byte[]> generatorDownLoad(MysqlGenerator mysqlGenerator, HttpHeaders headers){
        try {
            HashMap<String, Object> hashMap = this.getTemplateVariables(mysqlGenerator);

            // 取得系统临时文件路径
            String currentProjectPath =  System.getProperty("java.io.tmpdir");

            // 模板列表
            GeneratorConfigTemplate generatorConfigTemplateQuery = new GeneratorConfigTemplate();
            generatorConfigTemplateQuery.setGroupId(mysqlGenerator.getGroupId());
            List<GeneratorConfigTemplate> generatorConfigTemplateList = generatorConfigTemplateService.selectList(generatorConfigTemplateQuery);
            for(GeneratorConfigTemplate template : generatorConfigTemplateList){
                String importFilePath = currentProjectPath + File.separator + GENERATOR_PATH + File.separator + hashMap.get("tableClassNameEN");
                importFilePath =importFilePath + File.separator + hashMap.get("packagePath").toString().replaceAll("\\.","/");
                importFilePath =importFilePath + File.separator + template.getFilePath().replaceAll("\\.","/");
                importFilePath =importFilePath + File.separator + hashMap.get("tableClassNameEN") + template.getFileName();
                if(template.getTemplateCode().equals("sqlMapperTemplate")){
                    // sqlMapper文件路径与文件名规则
                    importFilePath = currentProjectPath + File.separator + GENERATOR_PATH + File.separator+hashMap.get("tableClassNameEN"); // 路径名称
                    importFilePath = importFilePath + File.separator + hashMap.get("tableClassNameEN") + template.getFileName(); // 文件名
                }
                if(template.getFilePath().equals("html")){
                    // html与js文件路径与文件名规则
                    importFilePath = currentProjectPath + File.separator + GENERATOR_PATH + File.separator+hashMap.get("tableClassNameEN"); // 路径名称
                    importFilePath = importFilePath + File.separator + "html" + File.separator + hashMap.get("htmlFileName") + template.getFileName(); // 文件名
                }
                File file = new File(importFilePath);
                    if (!file.exists()) {
                    File filePath = new File(file.getParent());
                    filePath.mkdirs();
                }
                // 解析并创建文件
                VelocityUtils.generatorCode(template.getTemplateContent(), hashMap, importFilePath);
            }

            // 生成压缩包
            String folderSrcPath = currentProjectPath + File.separator + GENERATOR_PATH + File.separator + hashMap.get("tableClassNameEN")+ File.separator;
            String forderDesPath = currentProjectPath + File.separator + GENERATOR_PATH + File.separator + hashMap.get("tableClassNameEN")+"_"+new Date().getTime()+".zip";
            FileZipUtils.zip(folderSrcPath, forderDesPath, "");
            // 下载文件
            File file = new File(forderDesPath);
            InputStream inputStream = new FileInputStream(file);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", new String((hashMap.get("tableClassNameEN")+"_"+new Date().getTime()+".zip").getBytes("gbk"),"iso-8859-1"));

            return new ResponseEntity<>(IOUtils.toByteArray(inputStream), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 取得数据源信息
     * @param datasourceId 数据源编号
     * @return 数据源信息
     */
    @Override
    public GeneratorConfigDatasource getDataSource(Long datasourceId) {
        GeneratorConfigDatasource datasource = null;
        if(datasourceId == -1){
            datasource = new GeneratorConfigDatasource();
            datasource.setDriverClassName(defaultDriverClassName);
            datasource.setDatasourceType(DatasourceTypeEnum.MySQL.getCode());
            datasource.setUrl(defaultUrl);
            datasource.setUsername(defaultUsername);
            datasource.setPassword(defaultPassword);
        }else{
            datasource = generatorConfigDatasourceService.selectOne(datasourceId);
        }
        return datasource;
    }

    /**
     * 获取模板变量
     * @param mysqlGenerator 字段信息实体类
     * @return HashMap<String,Object> 模板替换变量数据集合
     */
    private HashMap<String, Object> getTemplateVariables(MysqlGenerator mysqlGenerator){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String tableNameEN = mysqlGenerator.getTableName();

        HashMap<String, Object> hashMap = new HashMap<>();

        if(StringUtils.isNotBlank(mysqlGenerator.getTablePrefix())){
            String tablePrefix = mysqlGenerator.getTablePrefix() + "_";
            tableNameEN = tableNameEN.replaceFirst(tablePrefix, "");
            hashMap.put("tablePrefix", StringFormatUtils.getFirstOne(mysqlGenerator.getTableName())+"_");          // 表名前缀（带下划线）
        }else{
            hashMap.put("tablePrefix","");          // 表名前缀（带下划线）
        }

        hashMap.put("htmlPath",StringFormatUtils.htmlPath(tableNameEN,"_")); // 前台页面路径
        hashMap.put("htmlFileName",StringFormatUtils.htmlFileName(mysqlGenerator.getTableName(),"_")); // 前台页面名称
        hashMap.put("packagePath", mysqlGenerator.getPackagePath());            // 包路径
        hashMap.put("tableNameCH", mysqlGenerator.getTableComment());           // 中文表名
        hashMap.put("tableNameEN", tableNameEN);                                // 英文表名
        hashMap.put("tableNameENUpperCase", tableNameEN.toUpperCase());                                // 英文表名（大写）
        hashMap.put("tablePrefixLowerCase", StringFormatUtils.getFirstOne(mysqlGenerator.getTableName()).toLowerCase());     // 表名前缀（小写）
        hashMap.put("tablePrefixUpperCase", StringFormatUtils.getFirstOne(mysqlGenerator.getTableName()).toUpperCase());     // 表名前缀（大写）
        hashMap.put("tableClassNameEN", StringFormatUtils.humpToUpperCaseFirstOne(tableNameEN, "_"));
        hashMap.put("tablePropertyNameEN", StringFormatUtils.humpToLowerCaseFirstOne(tableNameEN, "_"));
        hashMap.put("author", mysqlGenerator.getAuthor());                      // 创建者
        hashMap.put("dateTime", sdf.format(new Date()));                        // 创建日期

        // 取得数据源信息
        GeneratorConfigDatasource generatorConfigDatasource = getDataSource(mysqlGenerator.getDatasource());

        if(null==generatorConfigDatasource){
            throw new RuntimeException(mysqlGenerator.getDatasource()+"数据源不存在");
        }

        // 查询数据类型JDBC映射
        HashMap<String, GeneratorConfigMapping> generatorConfigMappingHashMap = new HashMap<>();
        GeneratorConfigMapping generatorConfigMappingQuery = new GeneratorConfigMapping();
        generatorConfigMappingQuery.setDatasourceType(generatorConfigDatasource.getDatasourceType()); // 数据源类型（MySQL,Oracle,SQLServer）
        List<GeneratorConfigMapping> generatorConfigMappingList = generatorConfigMappingService.selectList(generatorConfigMappingQuery);
        for(GeneratorConfigMapping generatorConfigMapping : generatorConfigMappingList){
            generatorConfigMappingHashMap.put(generatorConfigMapping.getDbType(), generatorConfigMapping);
        }

        ObjectMapper mapper = new ObjectMapper();
        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();
        try {
            columnGeneratorList = mapper.readValue(mysqlGenerator.getColumnJson(), new TypeReference<List<ColumnGenerator>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("JSON字符串转换实体对象失败", e);
        }


        // 主键字段
        Column pkColumn = new Column();
        // 扩展标识字段
        List<Column> extFlagColumns = new ArrayList<>();
        // 逻辑删除标识字段
        Column effectiveFlagColumn = new Column();
        // 普通字段
        List<Column> columns = new ArrayList<>();

        for(ColumnGenerator column : columnGeneratorList){
            GeneratorConfigMapping generatorConfigMapping = generatorConfigMappingHashMap.get(column.getDataType());
            if(null==generatorConfigMapping){
                throw new BusinessException(column.getDataType() + "未配置数据类型映射关系。");
            }
            // 判断是否为过滤字段
            if(StringUtils.isBlank(column.getFilterFlag())) {
                if(StringUtils.isNotBlank(column.getEffectiveFlag())){
                    // 逻辑标识字段
                    effectiveFlagColumn.setColumnNameCH(column.getColumnComment());
                    effectiveFlagColumn.setColumnNameEN(column.getColumnName());
                    effectiveFlagColumn.setColumnPropertyName(StringFormatUtils.humpToLowerCaseFirstOne(column.getColumnName(), "_"));
                    effectiveFlagColumn.setColumnMethodName(StringFormatUtils.humpToUpperCaseFirstOne(column.getColumnName(), "_"));
                    effectiveFlagColumn.setJdbcType(StringUtils.upperCase(generatorConfigMapping.getJdbcType()));
                    effectiveFlagColumn.setJavaType(generatorConfigMapping.getJavaType());
                    effectiveFlagColumn.setMaxLength(column.getMaxLength());
                }else if(StringUtils.isNotBlank(column.getExtFlag())){
                    // 扩展字段
                    Column extColumn = new Column();
                    extColumn.setColumnNameCH(column.getColumnComment());
                    extColumn.setColumnNameEN(column.getColumnName());
                    extColumn.setColumnPropertyName(StringFormatUtils.humpToLowerCaseFirstOne(column.getColumnName(), "_"));
                    extColumn.setColumnMethodName(StringFormatUtils.humpToUpperCaseFirstOne(column.getColumnName(), "_"));
                    extColumn.setJdbcType(StringUtils.upperCase(generatorConfigMapping.getJdbcType()));
                    extColumn.setJavaType(generatorConfigMapping.getJavaType());
                    extColumn.setAutoFill(column.getAutoFill());
                    extColumn.setMaxLength(column.getMaxLength());
                    extFlagColumns.add(extColumn);
                }else if(StringUtils.isBlank(column.getColumnKey()) || !column.getColumnKey().equals("PRI")){
                    // 普通字段
                    Column commonColumn = new Column();
                    commonColumn.setColumnNameCH(column.getColumnComment());
                    commonColumn.setColumnNameEN(column.getColumnName());
                    commonColumn.setColumnPropertyName(StringFormatUtils.humpToLowerCaseFirstOne(column.getColumnName(), "_"));
                    commonColumn.setColumnMethodName(StringFormatUtils.humpToUpperCaseFirstOne(column.getColumnName(), "_"));
                    commonColumn.setJdbcType(StringUtils.upperCase(generatorConfigMapping.getJdbcType()));
                    commonColumn.setJavaType(generatorConfigMapping.getJavaType());
                    commonColumn.setLikeFlag(StringUtils.isNotBlank(column.getLikeFlag()));
                    commonColumn.setIsNullable("YES".equals(column.getIsNullable()));
                    commonColumn.setMaxLength(column.getMaxLength());
                    columns.add(commonColumn);
                }else{
                    // 主键
                    pkColumn.setColumnNameCH(column.getColumnComment());
                    pkColumn.setColumnNameEN(column.getColumnName());
                    pkColumn.setColumnPropertyName(StringFormatUtils.humpToLowerCaseFirstOne(column.getColumnName(), "_"));
                    pkColumn.setColumnMethodName(StringFormatUtils.humpToUpperCaseFirstOne(column.getColumnName(), "_"));
                    pkColumn.setJdbcType(StringUtils.upperCase(generatorConfigMapping.getJdbcType()));
                    pkColumn.setJavaType(generatorConfigMapping.getJavaType());
                    pkColumn.setMaxLength(column.getMaxLength());
                }
            }
        }

        hashMap.put("pkColumn",pkColumn); // 主键字段
        hashMap.put("extFlagColumns",extFlagColumns); // 扩展标识字段
        hashMap.put("effectiveFlagColumn",effectiveFlagColumn); // 逻辑删除标识字段
        hashMap.put("columns",columns); // 普通字段
        return hashMap;
    }
}