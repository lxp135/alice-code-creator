package alice.code.creator.controller.base;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.base.BaseMenu;
import alice.code.creator.service.base.BaseMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单基本信息表Controller
 * User: contact@liuxp.me
 * DateTime: 2018-06-26
 */
@Controller
@RequestMapping("/web/basic/base/menu")
@Secured("BASIC_MENU")
public class BaseMenuController extends BaseController {

    /**
     * 菜单基本信息表RemoteService接口
     */
    @Resource
    private BaseMenuService baseMenuService;

    /**
     * 根据条件分页查询
     * @param baseMenu 菜单基本信息表数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<BaseMenu> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<BaseMenu> selectPage(BaseMenu baseMenu,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
                                     @RequestParam(value = "sidx", required = false, defaultValue = "updateTime") String sortName,
                                     @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<BaseMenu> page = buildPage(baseMenu, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return baseMenuService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param baseMenu 菜单基本信息表数据实体
     * @return 菜单基本信息表列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseMenu> selectList(BaseMenu baseMenu) {

        return baseMenuService.selectList(baseMenu);
    }

    /**
     * 根据主键查询
     * @param id 主键
     * @return 菜单基本信息表
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public BaseMenu selectOne(Long id) {

        return baseMenuService.selectOne(id);
    }

    /**
     * 保存数据
     * @param baseMenu 菜单基本信息表数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseMenu insert(BaseMenu baseMenu) {

        // 执行插入，返回已设置主键的数据实体
        return baseMenuService.insert(baseMenu,getAccount().getUserName());
    }

    /**
     * 修改数据
     * @param baseMenu 菜单基本信息表数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(BaseMenu baseMenu) {
        // 执行更新
        int rows = baseMenuService.update(baseMenu,getAccount().getUserName());
        return rows > 0;
    }

    /**
     * 逻辑删除数据
     * @param id 主键
     * @return 执行结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(Long id) {
        // 执行逻辑删除
        int rows = baseMenuService.delete(id,getAccount().getUserName());
        return rows > 0;
    }

}