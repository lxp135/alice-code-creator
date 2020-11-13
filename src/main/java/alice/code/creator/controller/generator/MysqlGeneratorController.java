package alice.code.creator.controller.generator;

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

	/**
	 * 数据库表信息查询
	 */
	@RequestMapping(value = "/selectDatabase", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ColumnGenerator> selectDatabase() {
		return  mysqlGeneratorService.selectDatabase();
	}

	/**
	 * 数据库表信息查询
	 */
	@RequestMapping(value = "/selectTableNames", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public List<ColumnGenerator> selectTableNames(ColumnGenerator columnGenerator) {
		return  mysqlGeneratorService.selectTableNames(columnGenerator);
	}

	/**
	 * 字段信息查询
	 */
	@RequestMapping(value = "/selectColumnNames", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> selectColumnNames(ColumnGenerator columnGenerator) {
		List<ColumnGenerator> columnGeneratorList =  mysqlGeneratorService.selectColumnNames(columnGenerator);
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