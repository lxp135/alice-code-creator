package alice.code.creator.controller.generator;

import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.enums.DatasourceTypeEnum;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.generator.GeneratorConfigDatasource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.service.generator.GeneratorConfigDatasourceService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据源配置Controller
 * User: contact@liuxp.me
 * DateTime: 2020-12-31
 */
@Controller
@RequestMapping("/web/generator/config/datasource")
public class GeneratorConfigDatasourceController extends BaseController{

    /**
     * 数据源配置Service接口
     */
    @Resource
    private GeneratorConfigDatasourceService generatorConfigDatasourceService;

    /**
     * 数据源类型
     * @return 状态
     */
    @RequestMapping(value = "/type", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<Map<String,Object>>> type() {
        List<Map<String,Object>> list = new ArrayList<>();
        for (DatasourceTypeEnum value : DatasourceTypeEnum.values()) {
            Map<String, Object> one = new HashMap<>();
            one.put("code", value.getCode());
            one.put("name", value.getName());
            one.put("drive", value.getDrive());
            list.add(one);
        }
        return new Result<>(list);
    }

    /**
     * 根据条件分页查询
     * @param generatorConfigDatasource 数据源配置数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<GeneratorConfigDatasource> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<GeneratorConfigDatasource> selectPage(GeneratorConfigDatasource generatorConfigDatasource,
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "sidx", required = false, defaultValue = "ts") String sortName,
            @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<GeneratorConfigDatasource> page = buildPage(generatorConfigDatasource, pageNum,pageSize,sortName,sortOrder);

        generatorConfigDatasource.setOwnerUserId(getAccount().getId()); // 查询条件限定，只能查询自己配置的数据源

        // 执行查询
        return generatorConfigDatasourceService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param generatorConfigDatasource 数据源配置数据实体
     * @return 数据源配置列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<GeneratorConfigDatasource> selectList(GeneratorConfigDatasource generatorConfigDatasource) {

        generatorConfigDatasource.setOwnerUserId(getAccount().getId()); // 查询条件限定，只能查询自己配置的数据源

        return generatorConfigDatasourceService.selectList(generatorConfigDatasource);
    }

    /**
     * 根据主键查询
     * @param id 自增主键
     * @return 数据源配置
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigDatasource selectOne(Long id) {

        return generatorConfigDatasourceService.selectOne(id);
    }

    /**
     * 保存数据
     * @param generatorConfigDatasource 数据源配置数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public GeneratorConfigDatasource insert(GeneratorConfigDatasource generatorConfigDatasource) {

        generatorConfigDatasource.setOwnerUserId(getAccount().getId()); // 所有者用户编号
        generatorConfigDatasource.setOwnerUserName(getAccount().getUserName()); // 所有者用户名称

        // 执行插入，返回已设置主键的数据实体
        return generatorConfigDatasourceService.insert(generatorConfigDatasource,getAccount().getUserName());
    }

    /**
     * 修改数据
     * @param generatorConfigDatasource 数据源配置数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(GeneratorConfigDatasource generatorConfigDatasource) {

        generatorConfigDatasource.setOwnerUserId(getAccount().getId()); // 所有者用户编号
        generatorConfigDatasource.setOwnerUserName(getAccount().getUserName()); // 所有者用户名称

        // 执行更新
        int rows = generatorConfigDatasourceService.update(generatorConfigDatasource,getAccount().getUserName());
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
        int rows = generatorConfigDatasourceService.delete(id,getAccount().getUserName());
        return rows > 0;
    }

}