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

/**
 * 数据库元数据查询
 * ClickHouse实现类
 * @author contact@liuxp.me
 */
@Service("DataSourceClickHouseServiceImpl")
public class DataSourceClickHouseServiceImpl implements DataSourceService {

    @Override
    public Boolean test(String driverClassName, String url, String username, String password) {

        try {
            // 加载 MySql 的驱动类 将驱动注册到 DriverManager 当中
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new BusinessException("加载JDBC驱动失败，请检查驱动名称");
        }

        Connection con = null;
        try {
            // 获取连接
            con = DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new BusinessException("数据库连接失败！"+e.getMessage());
        }finally {
            // 最后关闭连接
            try {
                if(null!=con){
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public List<ColumnGenerator> selectDatabase(GeneratorConfigDatasource datasource) {

        if(datasource == null){
            throw new BusinessException("数据源不存在");
        }

        String sql = "SELECT * FROM `system`.databases d " +
                "WHERE engine = 'Ordinary'";

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableSchema(String.valueOf(result.get("name")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;

    }

    @Override
    public List<ColumnGenerator> selectTableNames(GeneratorConfigDatasource datasource,String tableSchema) {

        if(datasource == null){
            throw new BusinessException("数据源不存在");
        }

        String sql = "SELECT * FROM `system`.tables t " +
                "WHERE database = '"+tableSchema+"'";

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableName(String.valueOf(result.get("name")));
            columnGenerator.setTableComment(String.valueOf(result.get("name")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;
    }

    @Override
    public List<ColumnGenerator> selectColumnNames(GeneratorConfigDatasource datasource,String tableSchema,String tableName) {

        if(datasource == null){
            throw new BusinessException("数据源不存在");
        }

        String sql = "SELECT * FROM `system`.columns " +
                "WHERE database = '"+tableSchema+"' " +
                "AND `table` = '"+tableName+"'"  ;

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){

            String type = String.valueOf(result.get("type"));


            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableName(String.valueOf(result.get("table")));
            columnGenerator.setTableSchema(tableSchema);
            columnGenerator.setColumnName(String.valueOf(result.get("name")));
            columnGenerator.setDataType(type);
            if(type.contains("Decimal")){
                columnGenerator.setDataType("Decimal");
            }
            columnGenerator.setColumnKey(String.valueOf(result.get("is_in_primary_key")));
//            columnGenerator.setExtra(String.valueOf(result.get("")));
            if(type.contains("Nullable")){
                columnGenerator.setIsNullable("YES");
            }else{
                columnGenerator.setIsNullable("NO");
            }
//            columnGenerator.setMaxLength(String.valueOf(result.get("")));
            columnGenerator.setColumnComment(String.valueOf(result.get("comment")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;
    }

    private List<Map<String,Object>> execute(String className,String url,String username,String password,String sql){

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