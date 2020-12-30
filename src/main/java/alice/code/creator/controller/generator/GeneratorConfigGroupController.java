package alice.code.creator.controller.generator;

import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.generator.GeneratorConfigGroup;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.service.generator.GeneratorConfigGroupService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 模板分组Controller
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 */
@Controller
@RequestMapping("/web/generator/config/group")
public class GeneratorConfigGroupController extends BaseController{

    /**
     * 模板分组Service接口
     */
    @Resource
    private GeneratorConfigGroupService generatorConfigGroupService;

    /**
     * 根据条件分页查询
     * @param generatorConfigGroup 模板分组数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<GeneratorConfigGroup> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<GeneratorConfigGroup> selectPage(GeneratorConfigGroup generatorConfigGroup,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "sidx", required = false, defaultValue = "ts") String sortName,
            @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<GeneratorConfigGroup> page = buildPage(generatorConfigGroup, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return generatorConfigGroupService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param generatorConfigGroup 模板分组数据实体
     * @return 模板分组列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<GeneratorConfigGroup> selectList(GeneratorConfigGroup generatorConfigGroup) {

        return generatorConfigGroupService.selectList(generatorConfigGroup);
    }

    /**
     * 根据主键查询
     * @param id 自增主键
     * @return 模板分组
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigGroup selectOne(Long id) {

        return generatorConfigGroupService.selectOne(id);
    }

    /**
     * 保存数据
     * @param generatorConfigGroup 模板分组数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigGroup insert(GeneratorConfigGroup generatorConfigGroup) {

        // 执行插入，返回已设置主键的数据实体
        return generatorConfigGroupService.insert(generatorConfigGroup,getAccount().getUserName());
    }

    /**
     * 修改数据
     * @param generatorConfigGroup 模板分组数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(GeneratorConfigGroup generatorConfigGroup) {
        // 执行更新
        int rows = generatorConfigGroupService.update(generatorConfigGroup,getAccount().getUserName());
				return rows > 0;
    }

    /**
     * 逻辑删除数据
     * @param id 自增主键
     * @return 执行结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(Long id) {
        // 执行逻辑删除
        int rows = generatorConfigGroupService.delete(id,getAccount().getUserName());
				return rows > 0;
    }

}