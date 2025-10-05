package alice.code.creator.controller.generator;

import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.generator.GeneratorConfigTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.service.generator.GeneratorConfigTemplateService;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 代码生成器模板Controller
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 */
@Controller
@RequestMapping("/web/generator/template")
public class GeneratorConfigTemplateController extends BaseController{

    /**
     * 代码生成器模板Service接口
     */
    @Resource
    private GeneratorConfigTemplateService generatorConfigTemplateService;

    /**
     * 根据条件分页查询
     * @param generatorConfigTemplate 代码生成器模板数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<GeneratorConfigTemplate> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<GeneratorConfigTemplate> selectPage(GeneratorConfigTemplate generatorConfigTemplate,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "sidx", required = false, defaultValue = "ts") String sortName,
            @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<GeneratorConfigTemplate> page = buildPage(generatorConfigTemplate, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return generatorConfigTemplateService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param generatorConfigTemplate 代码生成器模板数据实体
     * @return 代码生成器模板列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<GeneratorConfigTemplate> selectList(GeneratorConfigTemplate generatorConfigTemplate) {

        return generatorConfigTemplateService.selectList(generatorConfigTemplate);
    }

    /**
     * 根据主键查询
     * @param id 自增主键
     * @return 代码生成器模板
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigTemplate selectOne(Long id) {

        return generatorConfigTemplateService.selectOne(id);
    }

    /**
     * 保存数据
     * @param generatorConfigTemplate 代码生成器模板数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigTemplate insert(GeneratorConfigTemplate generatorConfigTemplate) {

        // 执行插入，返回已设置主键的数据实体
        return generatorConfigTemplateService.insert(generatorConfigTemplate, getAccount().getId(),getAccount().getUserName());
    }

    /**
     * 修改数据
     * @param generatorConfigTemplate 代码生成器模板数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(GeneratorConfigTemplate generatorConfigTemplate) {
        // 执行更新
        int rows = generatorConfigTemplateService.update(generatorConfigTemplate, getAccount().getId(),getAccount().getUserName());
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
        int rows = generatorConfigTemplateService.delete(id,getAccount().getId(),getAccount().getUserName());
        return rows > 0;
    }

}