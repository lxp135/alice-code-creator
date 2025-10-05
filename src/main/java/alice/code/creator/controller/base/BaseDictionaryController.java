package alice.code.creator.controller.base;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.base.BaseDictionary;
import alice.code.creator.service.base.BaseDictionaryService;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 数据字典表Controller
 * @author contact@liuxp.me
 */
@Controller
@RequestMapping("/web/basic/base/dictionary")
@Secured("BASIC_DICTIONARY_NORMAL")
public class BaseDictionaryController extends BaseController {

    // 数据字典表Service
	@Resource
	private BaseDictionaryService baseDictionaryService;

    /**
     * 分页查询
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
	@ResponseBody
	@Secured("PUBLIC")
    public Page<BaseDictionary> selectPage(BaseDictionary baseDictionary,
										   @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
										   @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
										   @RequestParam(value = "sidx", required = false, defaultValue = "updateTime") String sortName,
										   @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

		Page<BaseDictionary> page = buildPage(baseDictionary, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
		return baseDictionaryService.selectPage(page);
    }

    /**
     * 列表查询
     */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
	@ResponseBody
	@Secured("PUBLIC")
    public List<BaseDictionary> selectList(BaseDictionary baseDictionary) {

		return baseDictionaryService.selectList(baseDictionary);

    }

    /**
     * 单条查询
     */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
	@ResponseBody
	@Secured("PUBLIC")
    public BaseDictionary selectOne(Long id) {

		return baseDictionaryService.selectOne(id);

    }

	/**
	 * 保存数据
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public BaseDictionary insert(BaseDictionary baseDictionary) {

		// 执行插入
		BaseDictionary result = baseDictionaryService.insert(baseDictionary,getAccount().getUserName());
		// 返回已设置主键的数据实体
		return result;
	}

	/**
	 * 修改数据
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Boolean update(BaseDictionary baseDictionary) {
		// 执行更新
		int rows = baseDictionaryService.update(baseDictionary,getAccount().getUserName());
		return rows > 0;
	}

	/**
	 * 逻辑删除数据
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Boolean delete(Long id) {
		// 执行逻辑删除
		int rows = baseDictionaryService.delete(id,getAccount().getUserName());
		return rows > 0;
	}

}