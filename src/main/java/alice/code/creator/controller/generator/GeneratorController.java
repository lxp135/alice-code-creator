package alice.code.creator.controller.generator;

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
	private DataSourceService dataSourceService;

	@Resource
	private GeneratorConfigDatasourceService generatorConfigDatasourceService;

	@Value("${spring.datasource.driver-class-name}")
	private String defaultDriverClassName;

	@Value("${spring.datasource.url}")
	private String defaultUrl;

	@Value("${spring.datasource.username}")
	private String defaultUsername;

	@Value("${spring.datasource.password}")
	private String defaultPassword;

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

		return dataSourceService.selectDatabase(datasource);
	}

	/**
	 * 数据库表信息查询
	 */
	@RequestMapping(value = "/selectTableNames", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ColumnGenerator> selectTableNames(Long datasourceId,String tableSchema) {

		// 取得数据源信息
		GeneratorConfigDatasource datasource = getDataSource(datasourceId);

		return dataSourceService.selectTableNames(datasource,tableSchema);
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

		List<ColumnGenerator> columnGeneratorList =  dataSourceService.selectColumnNames(datasource,tableSchema,tableName);
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
		GeneratorConfigDatasource datasource = null;
		if(datasourceId == -1){
			datasource = new GeneratorConfigDatasource();
			datasource.setDriverClassName(defaultDriverClassName);
			datasource.setUrl(defaultUrl);
			datasource.setUsername(defaultUsername);
			datasource.setPassword(defaultPassword);
		}else{
			datasource = generatorConfigDatasourceService.selectOne(datasourceId);
		}
		return datasource;
	}


}