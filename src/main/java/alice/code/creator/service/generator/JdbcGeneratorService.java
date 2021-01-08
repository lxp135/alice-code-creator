package alice.code.creator.service.generator;

import alice.code.creator.domain.model.generator.ColumnGenerator;

import java.util.List;

public interface JdbcGeneratorService {
    /**
     * 查询数据库列表
     * @param datasourceId 数据源编号
     * @return 数据库列表
     */
    List<ColumnGenerator> selectDatabase(Long datasourceId);

    /**
     * 根据数据库名查询所有表
     * @param datasourceId 数据源编号
     * @param tableSchema 库名称
     * @return 表列表
     */
    List<ColumnGenerator> selectTableNames(Long datasourceId,String tableSchema);

    /**
     * 根据数据库名查询所有字段
     * @param datasourceId 数据源编号
     * @param tableSchema 库名称
     * @param tableName 表名称
     * @return 字段列表
     */
    List<ColumnGenerator> selectColumnNames(Long datasourceId,String tableSchema,String tableName);

}