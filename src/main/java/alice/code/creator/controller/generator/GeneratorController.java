package alice.code.creator.controller.generator;

import alice.code.creator.common.framework.config.BeanConfig;
import alice.code.creator.domain.enums.DatasourceTypeEnum;
import alice.code.creator.domain.model.generator.GeneratorConfigDatasource;
import alice.code.creator.service.generator.GeneratorConfigDatasourceService;
import alice.code.creator.service.generator.DataSourceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.generator.ColumnGenerator;
import alice.code.creator.domain.model.generator.MysqlGenerator;
import alice.code.creator.service.generator.GeneratorService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MySQL代码生成Controller
 * @author contact@liuxp.me
 */
@Controller
@RequestMapping("/generator")
@Secured("DEVELOP_GENERATOR")
public class GeneratorController extends BaseController {

    // MySQL代码生成Service
	@Resource
	private GeneratorService generatorService;

	@Resource
	private GeneratorConfigDatasourceService generatorConfigDatasourceService;

	@Resource
	private BeanConfig beanConfig;

	@Value("${spring.datasource.driver-class-name}")
	private String defaultDriverClassName;

	@Value("${spring.datasource.url}")
	private String defaultUrl;

	@Value("${spring.datasource.username}")
	private String defaultUsername;

	@Value("${spring.datasource.password}")
	private String defaultPassword;

	/**
	 * 测试数据源是否可用
	 * @param datasourceType 类型
	 * @param driverClassName JDBC驱动
	 * @param url 数据源地址
	 * @param username 用户名
	 * @param password 密码
	 * @return 测试结果
	 */
	@RequestMapping(value = "/testDatasource", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Boolean testDatasource(String datasourceType,String driverClassName,String url,String username,String password){

		return getDataSourceService(datasourceType).test(driverClassName,url,username,password);
	}

	/**
	 * 取得数据源
	 * @return 默认数据源 + 用户配置的数据源集合
	 */
	@RequestMapping(value = "/selectDatasource", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<GeneratorConfigDatasource> selectDatasource(){

		GeneratorConfigDatasource query = new GeneratorConfigDatasource();
		query.setOwnerUserId(getAccount().getId());

		List<GeneratorConfigDatasource> result = generatorConfigDatasourceService.selectList(query);

		GeneratorConfigDatasource defaultDatasource = new GeneratorConfigDatasource();
		defaultDatasource.setId(-1L);
		defaultDatasource.setDatasourceName("默认数据库");

		result.add(0,defaultDatasource);

		return result;
	}

	/**
	 * 数据库表信息查询
	 */
	@RequestMapping(value = "/selectDatabase", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ColumnGenerator> selectDatabase(Long datasourceId) {

		// 取得数据源信息
		GeneratorConfigDatasource datasource = getDataSource(datasourceId);

		return getDataSourceService(datasource.getDatasourceType()).selectDatabase(datasource);
	}

	/**
	 * 数据库表信息查询
	 */
	@RequestMapping(value = "/selectTableNames", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ColumnGenerator> selectTableNames(Long datasourceId,String tableSchema) {

		// 取得数据源信息
		GeneratorConfigDatasource datasource = getDataSource(datasourceId);

		return getDataSourceService(datasource.getDatasourceType()).selectTableNames(datasource,tableSchema);
	}

	/**
	 * 字段信息查询
	 * @param datasourceId 数据源编号
	 * @param tableSchema 库名称
	 * @param tableName 表名称
	 * @return 字段信息
	 */
	@RequestMapping(value = "/selectColumnNames", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> selectColumnNames(Long datasourceId,String tableSchema,String tableName) {

		// 取得数据源信息
		GeneratorConfigDatasource datasource = getDataSource(datasourceId);

		List<ColumnGenerator> columnGeneratorList = getDataSourceService(datasource.getDatasourceType()).selectColumnNames(datasource,tableSchema,tableName);
		Map<String, Object> result = new HashMap<>();
		result.put("root", columnGeneratorList);
		return result;
	}

	/**
	 * 生成代码-压缩包下载
	 */
	@RequestMapping(value = "/downLoad")
	@ResponseBody
	public ResponseEntity<byte[]> downLoad(MysqlGenerator mysqlGenerator){
		HttpHeaders headers = new HttpHeaders();
		return generatorService.generatorDownLoad(mysqlGenerator, headers);
	}

	/**
	 * 取得数据源信息
	 * @param datasourceId 数据源编号
	 * @return 数据源信息
	 */
	private GeneratorConfigDatasource getDataSource(Long datasourceId){
		return generatorService.getDataSource(datasourceId);
	}

	/**
	 * 取得数据源实现类
	 * @param datasourceType 数据源类型
	 * @return 数据源实现类
	 */
	private DataSourceService getDataSourceService(String datasourceType){
		return beanConfig.getSourceService("DataSource"+datasourceType+"ServiceImpl");
	}


}