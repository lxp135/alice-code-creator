package alice.code.creator.service.generator;

import alice.code.creator.domain.model.generator.ColumnGenerator;
import alice.code.creator.domain.model.generator.GeneratorConfigDatasource;

import java.util.List;

public interface DataSourceService {
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