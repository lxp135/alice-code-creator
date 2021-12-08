package alice.code.creator.service.generator;

import alice.code.creator.domain.model.generator.ColumnGenerator;
import alice.code.creator.domain.model.generator.GeneratorConfigDatasource;

import java.util.List;

/**
 * 数据库元数据查询
 * @author contact@liuxp.me
 */
public interface DataSourceService {

    /**
     * 测试数据源是否可用
     * @param driverClassName JDBC驱动
     * @param url 数据源地址
     * @param username 用户名
     * @param password 密码
     * @return 测试结果
     */
    Boolean test(String driverClassName, String url, String username, String password);

    /**
     * 查询数据库列表
     * @param datasource 数据源信息
     * @return 数据库列表
     */
    List<ColumnGenerator> selectDatabase(GeneratorConfigDatasource datasource);

    /**
     * 根据数据库名查询所有表
     * @param datasource 数据源信息
     * @param tableSchema 库名称
     * @return 表列表
     */
    List<ColumnGenerator> selectTableNames(GeneratorConfigDatasource datasource,String tableSchema);

    /**
     * 根据数据库名查询所有字段
     * @param datasource 数据源信息
     * @param tableSchema 库名称
     * @param tableName 表名称
     * @return 字段列表
     */
    List<ColumnGenerator> selectColumnNames(GeneratorConfigDatasource datasource,String tableSchema,String tableName);

}