package alice.code.creator.controller.generator;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.generator.GeneratorConfigMapping;
import alice.code.creator.service.generator.GeneratorConfigMappingService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 代码生成器映射关系配置Controller
 * User: contact@liuxp.me
 * DateTime: 2018-12-26
 */
@Controller
@RequestMapping("/web/generator/mapping")
@Secured("DEVELOP_GENERATOR")
public class GeneratorConfigMappingController extends BaseController {

    /**
     * 代码生成器映射关系配置RemoteService接口
     */
    @Resource
    private GeneratorConfigMappingService generatorConfigMappingService;

    /**
     * 根据条件分页查询
     * @param generatorConfigMapping 代码生成器映射关系配置数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<GeneratorConfigMapping> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<GeneratorConfigMapping> selectPage(GeneratorConfigMapping generatorConfigMapping,
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                                   @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
                                                   @RequestParam(value = "sidx", required = false, defaultValue = "updateTime") String sortName,
                                                   @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<GeneratorConfigMapping> page = buildPage(generatorConfigMapping, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return generatorConfigMappingService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param generatorConfigMapping 代码生成器映射关系配置数据实体
     * @return 代码生成器映射关系配置列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<GeneratorConfigMapping> selectList(GeneratorConfigMapping generatorConfigMapping) {

        return generatorConfigMappingService.selectList(generatorConfigMapping);
    }

    /**
     * 根据主键查询
     * @param id 自增主键
     * @return 代码生成器映射关系配置
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigMapping selectOne(Long id) {

        return generatorConfigMappingService.selectOne(id);
    }

    /**
     * 保存数据
     * @param generatorConfigMapping 代码生成器映射关系配置数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigMapping insert(GeneratorConfigMapping generatorConfigMapping) {

        // 执行插入，返回已设置主键的数据实体
        return generatorConfigMappingService.insert(generatorConfigMapping,getAccount().getUserName());
    }

    /**
     * 修改数据
     * @param generatorConfigMapping 代码生成器映射关系配置数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(GeneratorConfigMapping generatorConfigMapping) {
        // 执行更新
        int rows =  generatorConfigMappingService.update(generatorConfigMapping,getAccount().getUserName());
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
        int rows =  generatorConfigMappingService.delete(id,getAccount().getUserName());
        return rows > 0 ;
    }

}