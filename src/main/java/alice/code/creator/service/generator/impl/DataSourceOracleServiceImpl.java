package alice.code.creator.service.generator.impl;

import alice.code.creator.common.framework.context.BusinessException;
import alice.code.creator.domain.model.generator.ColumnGenerator;
import alice.code.creator.domain.model.generator.GeneratorConfigDatasource;
import alice.code.creator.service.generator.DataSourceService;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DataSourceOracleServiceImpl")
public class DataSourceOracleServiceImpl implements DataSourceService {

    @Override
    public List<ColumnGenerator> selectDatabase(GeneratorConfigDatasource datasource) {

        if(datasource == null){
            throw new BusinessException("数据源不存在");
        }

        String sql = "SELECT USERNAME FROM ALL_USERS" +
                "        WHERE USERNAME <> 'SYSTEM'" +
                "        AND USERNAME <> 'SYS'" +
                "        ORDER BY USERNAME";

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableSchema(String.valueOf(result.get("USERNAME")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;

    }

    @Override
    public List<ColumnGenerator> selectTableNames(GeneratorConfigDatasource datasource,String tableSchema) {

        if(datasource == null){
            throw new BusinessException("数据源不存在");
        }

        String sql =
                "SELECT TABLE_NAME,COMMENTS " +
                "FROM ALL_TAB_COMMENTS " +
                "WHERE OWNER = '"+tableSchema+"'" ;

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableName(String.valueOf(result.get("TABLE_NAME")));
            columnGenerator.setTableComment(String.valueOf(result.get("COMMENTS")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;
    }

    @Override
    public List<ColumnGenerator> selectColumnNames(GeneratorConfigDatasource datasource,String tableSchema,String tableName) {

        if(datasource == null){
            throw new BusinessException("数据源不存在");
        }

        String sql =
                "SELECT" +
                "   ATC.OWNER OWNER," +
                "   ATC.TABLE_NAME TABLE_NAME," +
                "   ATC.COLUMN_NAME COLUMN_NAME," +
                "   ATC.DATA_TYPE DATA_TYPE," +
//                "   COLUMN_KEY," +
//                "   EXTRA," +
                "   ATC.NULLABLE NULLABLE," +
                "   ATC.DATA_LENGTH DATA_LENGTH," +
                "   ACC.COMMENTS COMMENTS " +
                "FROM ALL_TAB_COLUMNS ATC, ALL_COL_COMMENTS ACC  " +
                "WHERE ATC.OWNER = ACC.OWNER " +
                "AND ATC.TABLE_NAME = ACC.TABLE_NAME " +
                "AND ATC.COLUMN_NAME = ACC.COLUMN_NAME " +
                "AND ATC.TABLE_NAME = '"+tableName+"'" +
                "AND ATC.OWNER = '"+tableSchema+"'";

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableName(String.valueOf(result.get("TABLE_NAME")));
            columnGenerator.setTableSchema(String.valueOf(result.get("OWNER")));
            columnGenerator.setColumnName(String.valueOf(result.get("COLUMN_NAME")));
            columnGenerator.setDataType(String.valueOf(result.get("DATA_TYPE")));
//            columnGenerator.setColumnKey(String.valueOf(result.get("COLUMN_KEY")));
//            columnGenerator.setExtra(String.valueOf(result.get("EXTRA")));
            columnGenerator.setIsNullable("Y".equals(String.valueOf(result.get("NULLABLE")))?"YES":"NO");
            columnGenerator.setMaxLength(String.valueOf(result.get("DATA_LENGTH")));
            columnGenerator.setColumnComment(String.valueOf(result.get("COMMENTS")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;
    }

    private List<Map<String,Object>> execute(String className, String url, String username, String password, String sql){

        List<Map<String,Object>> result = new ArrayList<>();

        // 第一步：加载驱动
        try {// 加载 MySql 的驱动类 将驱动注册到 DriverManager 当中
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败！请检查驱动名称");
            e.printStackTrace();
        }

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // 第二步：获取连接
            con = DriverManager.getConnection(url, username, password);
            // 第四步：获取 statement 类
            statement = con.createStatement();
            // 第五步：获取到执行后的结果集 resultSet
            resultSet = statement.executeQuery(sql);

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int count = resultSetMetaData.getColumnCount();

            while(resultSet.next()){

                Map<String,Object> line = new HashMap<>();

                for(int i=0;i<count;i++){
                    line.put(resultSetMetaData.getColumnName(i+1),resultSet.getString(i+1));
                }
                result.add(line);
            }

        } catch (SQLException e) {
            System.out.println("数据库连接失败！请检查数据库连接信息");
            e.printStackTrace();
        }finally {
            // 先关闭结果集
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 然后关闭 Statement 对象
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 最后关闭连接
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}