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
 * MySQL实现类
 * @author contact@liuxp.me
 */
@Service("DataSourceMySQLServiceImpl")
public class DataSourceMySQLServiceImpl implements DataSourceService {

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

        String sql = "SELECT TABLE_SCHEMA FROM information_schema.COLUMNS" +
                "        WHERE" +
                "            TABLE_SCHEMA <> 'information_schema'" +
                "            AND TABLE_SCHEMA <> 'performance_schema'" +
                "            AND TABLE_SCHEMA <> 'mysql'" +
                "            AND TABLE_SCHEMA <> 'sys'" +
                "        GROUP BY TABLE_SCHEMA";

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableSchema(String.valueOf(result.get("TABLE_SCHEMA")));
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
                "SELECT TABLE_NAME,TABLE_COMMENT " +
                "FROM information_schema.TABLES " +
                "WHERE" +
                "   TABLE_SCHEMA = '"+tableSchema+"'" +
                "GROUP BY TABLE_NAME,TABLE_COMMENT";

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableName(String.valueOf(result.get("TABLE_NAME")));
            columnGenerator.setTableComment(String.valueOf(result.get("TABLE_COMMENT")));
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
                "   TABLE_SCHEMA," +
                "   TABLE_NAME," +
                "   COLUMN_NAME," +
                "   DATA_TYPE," +
                "   COLUMN_KEY," +
                "   EXTRA," +
                "   IS_NULLABLE," +
                "   CHARACTER_MAXIMUM_LENGTH," +
                "   NUMERIC_PRECISION," +
                "   COLUMN_COMMENT " +
                "FROM information_schema.COLUMNS " +
                "WHERE" +
                "    TABLE_NAME = '"+tableName+"'" +
                "AND TABLE_SCHEMA = '"+tableSchema+"'";

        if(null==tableName){
            sql =
                    "SELECT" +
                            "   TABLE_SCHEMA," +
                            "   TABLE_NAME," +
                            "   COLUMN_NAME," +
                            "   DATA_TYPE," +
                            "   COLUMN_KEY," +
                            "   EXTRA," +
                            "   IS_NULLABLE," +
                            "   CHARACTER_MAXIMUM_LENGTH," +
                            "   NUMERIC_PRECISION," +
                            "   COLUMN_COMMENT " +
                            "FROM information_schema.COLUMNS " +
                            "WHERE" +
                            " TABLE_SCHEMA = '"+tableSchema+"'";
        }

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableName(String.valueOf(result.get("TABLE_NAME")));
            columnGenerator.setTableSchema(String.valueOf(result.get("TABLE_SCHEMA")));
            columnGenerator.setColumnName(String.valueOf(result.get("COLUMN_NAME")));
            columnGenerator.setDataType(String.valueOf(result.get("DATA_TYPE")));
            columnGenerator.setColumnKey(String.valueOf(result.get("COLUMN_KEY")));
            columnGenerator.setExtra(String.valueOf(result.get("EXTRA")));
            columnGenerator.setIsNullable(String.valueOf(result.get("IS_NULLABLE")));
            if(null!=result.get("CHARACTER_MAXIMUM_LENGTH")){
                columnGenerator.setMaxLength(String.valueOf(result.get("CHARACTER_MAXIMUM_LENGTH")));
            }
            if(null!=result.get("NUMERIC_PRECISION")){
                columnGenerator.setMaxLength(String.valueOf(result.get("NUMERIC_PRECISION")));
            }
            columnGenerator.setColumnComment(String.valueOf(result.get("COLUMN_COMMENT")));
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

    public static void main(String[] args) {
        String ClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/alice_code_creator?characterEncoding=UTF-8&useSSL=true&serverTimezone=Asia/Shanghai";
        String username = "root";
        String password = "root";

        // 第一步：加载驱动
        try {// 加载 MySql 的驱动类 将驱动注册到 DriverManager 当中
            Class.forName(ClassName);
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动失败！请检查驱动名称");
            e.printStackTrace();
        }

        Connection con = null;
        Statement statement = null;
        String sql = null;
        ResultSet resultSet = null;
        try {
            // 第二步：获取连接
            con = DriverManager.getConnection(url, username, password);
            // 第三步：创建 sql
            sql = "SELECT * FROM base_user";
            // 第四步：获取 statement 类
            statement = con.createStatement();
            // 第五步：获取到执行后的结果集 resultSet
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                // 通过结果集的操作方法进行数据的获取   这里可以进行实际的业务操作，例如存到一个对应的实体类，返回给前端
                // 这里是获取的
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
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
    }
}