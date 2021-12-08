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
 * SQLServer实现类
 * @author contact@liuxp.me
 */
@Service("DataSourceSQLServerServiceImpl")
public class DataSourceSQLServerServiceImpl implements DataSourceService {

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
            e.printStackTrace();
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

        String sql = "SELECT NAME FROM SYSDATABASES" +
                "        WHERE" +
                "            NAME <> 'master'" +
                "            AND NAME <> 'tempdb'" +
                "            AND NAME <> 'model'" +
                "            AND NAME <> 'msdb'";

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableSchema(String.valueOf(result.get("NAME")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;
    }

    @Override
    public List<ColumnGenerator> selectTableNames(GeneratorConfigDatasource datasource,String tableSchema) {

        if(datasource == null){
            throw new BusinessException("数据源不存在");
        }

        String sql = "use "+ tableSchema +"; " +
                "SELECT DISTINCT d.name TABLE_NAME, f.value TABLE_COMMENT " +
                "FROM syscolumns a " +
                "LEFT JOIN systypes b ON a.xusertype= b.xusertype " +
                "INNER JOIN sysobjects d ON a.id= d.id AND d.xtype= 'U' AND d.name<> 'dtproperties' " +
                "LEFT JOIN syscomments e ON a.cdefault= e.id " +
                "LEFT JOIN sys.extended_properties g ON a.id= G.major_id AND a.colid= g.minor_id " +
                "LEFT JOIN sys.extended_properties f ON d.id= f.major_id AND f.minor_id= 0 ";

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
                "USE "+tableSchema+"; " +
                        "SELECT  " +
                        "'"+tableSchema+"' AS TABLE_SCHEMA,"+
                        "D.NAME AS TABLE_NAME, " +
                        "A.NAME AS COLUMN_NAME, " +
                        "B.NAME AS DATA_TYPE, " +
                        "CASE WHEN EXISTS(SELECT 1 FROM SYSOBJECTS WHERE XTYPE='PK' AND PARENT_OBJ=A.ID AND NAME IN ( " +
                        "SELECT NAME FROM SYSINDEXES WHERE INDID IN( " +
                        "SELECT INDID FROM SYSINDEXKEYS WHERE ID = A.ID AND COLID=A.COLID))) THEN 'PRI' ELSE '' END AS COLUMN_KEY, " +
                        // EXTRA
                        "CASE WHEN A.ISNULLABLE=1 THEN 'YES'ELSE 'NO' END AS IS_NULLABLE, " +
                        "COLUMNPROPERTY(A.ID,A.NAME,'PRECISION') AS DATA_LENGTH, " +
                        "ISNULL(G.[VALUE],'')  AS COLUMN_COMMENT " +
                        "FROM SYSCOLUMNS A  " +
                        "LEFT JOIN SYSTYPES B ON A.XUSERTYPE=B.XUSERTYPE  " +
                        "INNER JOIN SYSOBJECTS D ON A.ID=D.ID  AND D.XTYPE='U' AND  D.NAME<>'DTPROPERTIES'  " +
                        "LEFT JOIN SYSCOMMENTS E ON A.CDEFAULT=E.ID  " +
                        "LEFT JOIN sys.extended_properties G ON A.ID=G.major_id AND A.COLID=G.minor_id  " +
                        "WHERE D.NAME = '"+tableName+"'" +
                        "ORDER BY A.ID,A.COLORDER "  ;

        List<Map<String,Object>> resultList = execute(datasource.getDriverClassName(), datasource.getUrl(), datasource.getUsername(), datasource.getPassword(), sql);

        List<ColumnGenerator> columnGeneratorList = new ArrayList<>();

        for(Map<String,Object> result : resultList){
            ColumnGenerator columnGenerator = new ColumnGenerator();
            columnGenerator.setTableName(String.valueOf(result.get("TABLE_NAME")));
            columnGenerator.setTableSchema(tableSchema);
            columnGenerator.setColumnName(String.valueOf(result.get("COLUMN_NAME")));
            columnGenerator.setDataType(String.valueOf(result.get("DATA_TYPE")));
            columnGenerator.setColumnKey(String.valueOf(result.get("COLUMN_KEY")));
            columnGenerator.setExtra(String.valueOf(result.get("EXTRA")));
            columnGenerator.setIsNullable(String.valueOf(result.get("IS_NULLABLE")));
            columnGenerator.setMaxLength(String.valueOf(result.get("DATA_LENGTH")));
            columnGenerator.setColumnComment(String.valueOf(result.get("COLUMN_COMMENT")));
            columnGeneratorList.add(columnGenerator);
        }

        return columnGeneratorList;
    }

    private List<Map<String,Object>> execute(String className,String url,String username,String password,String sql){

        List<Map<String,Object>> result = new ArrayList<>();

        // 第一步：加载驱动
        try {// 加载 SQLServer 的驱动类 将驱动注册到 DriverManager 当中
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
        String ClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://10.0.23.60:1433;DatabaseName=master";
        String username = "sa";
        String password = "SYviewhigh@123456";

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
            sql = "use hn_gov_assets; " +
                    "SELECT DISTINCT d.name, f.value " +
                    "FROM syscolumns a " +
                    "LEFT JOIN systypes b ON a.xusertype= b.xusertype " +
                    "INNER JOIN sysobjects d ON a.id= d.id AND d.xtype= 'U' AND d.name<> 'dtproperties' " +
                    "LEFT JOIN syscomments e ON a.cdefault= e.id " +
                    "LEFT JOIN sys.extended_properties g ON a.id= G.major_id AND a.colid= g.minor_id " +
                    "LEFT JOIN sys.extended_properties f ON d.id= f.major_id AND f.minor_id= 0 ";
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