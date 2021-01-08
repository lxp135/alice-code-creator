package alice.code.creator.controller.generator;

import alice.code.creator.domain.model.generator.GeneratorConfigDatasource;
import alice.code.creator.service.generator.GeneratorConfigDatasourceService;
import alice.code.creator.service.generator.JdbcGeneratorService;
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
import alice.code.creator.service.generator.MysqlGeneratorService;

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
public class MysqlGeneratorController extends BaseController {

    // MySQL代码生成Service
	@Resource
	private MysqlGeneratorService mysqlGeneratorService;

	@Resource
	private JdbcGeneratorService jdbcGeneratorService;

	@Resource
	private GeneratorConfigDatasourceService generatorConfigDatasourceService;

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
		return jdbcGeneratorService.selectDatabase(datasourceId);
	}

	/**
	 * 数据库表信息查询
	 */
	@RequestMapping(value = "/selectTableNames", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ColumnGenerator> selectTableNames(Long datasourceId,String tableSchema) {

		return jdbcGeneratorService.selectTableNames(datasourceId,tableSchema);
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

		List<ColumnGenerator> columnGeneratorList =  jdbcGeneratorService.selectColumnNames(datasourceId,tableSchema,tableName);
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
		return mysqlGeneratorService.generatorDownLoad(mysqlGenerator, headers);
	}


}