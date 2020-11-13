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
import alice.code.creator.domain.model.base.BaseRole;
import alice.code.creator.domain.model.base.BaseRoleMenuRel;
import alice.code.creator.domain.model.base.BaseRoleUserRel;
import alice.code.creator.service.base.BaseMenuService;
import alice.code.creator.service.base.BaseRoleMenuRelService;
import alice.code.creator.service.base.BaseRoleService;
import alice.code.creator.service.base.BaseRoleUserRelService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色基本信息表Controller
 * User: contact@liuxp.me
 * DateTime: 2018-06-27
 */
@Controller
@RequestMapping("/web/basic/base/role")
@Secured("BASIC_ROLE")
public class BaseRoleController extends BaseController {

    // 角色基本信息表RemoteService接口
    @Resource
    private BaseRoleService baseRoleRemoteService;

    // 角色与用户关系RemoteService接口
    @Resource
    private BaseRoleUserRelService baseRoleUserRelRemoteService;

    // 菜单基本信息表RemoteService接口
    @Resource
    private BaseMenuService baseMenuRemoteService;

    // 角色与菜单关系表RemoteService接口
    @Resource
    private BaseRoleMenuRelService baseRoleMenuRelRemoteService;

    /**
     * 根据条件分页查询
     * @param baseRole 角色基本信息表数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<BaseRole> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<BaseRole> selectPage(BaseRole baseRole,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
                                     @RequestParam(value = "sidx", required = false, defaultValue = "updateTime") String sortName,
                                     @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<BaseRole> page = buildPage(baseRole, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return baseRoleRemoteService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param baseRole 角色基本信息表数据实体
     * @return 角色基本信息表列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseRole> selectList(BaseRole baseRole) {

        return baseRoleRemoteService.selectList(baseRole);
    }

    /**
     * 根据主键查询
     * @param id 主键
     * @return 角色基本信息表
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public BaseRole selectOne(Long id) {

        return baseRoleRemoteService.selectOne(id);
    }

    /**
     * 保存数据
     * @param baseRole 角色基本信息表数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public BaseRole insert(BaseRole baseRole) {

        // 执行插入，返回已设置主键的数据实体
        return baseRoleRemoteService.insert(baseRole,getAccount().getUserName());
    }

    /**
     * 修改数据
     * @param baseRole 角色基本信息表数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(BaseRole baseRole) {
        // 执行更新
        int rows = baseRoleRemoteService.update(baseRole,getAccount().getUserName());
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
        int rows = baseRoleRemoteService.delete(id,getAccount().getUserName());
        return rows > 0;
    }

    /**
     * 取得全部角色与需配置用户已有角色集合
     * @param userId 用户编号
     * @return 角色基本信息表列表
     */
    @RequestMapping(value = "/selectUserRel", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseRole> selectUserRel(Long userId) {

        // 取得全部角色
        List<BaseRole> result = baseRoleRemoteService.selectList(new BaseRole());

        BaseRoleUserRel baseRoleUserRelQuery = new BaseRoleUserRel();
        baseRoleUserRelQuery.setUserId(userId);

        // 取得用户已有角色集合
        List<BaseRoleUserRel> rel = baseRoleUserRelRemoteService.selectList(baseRoleUserRelQuery);

        for(BaseRole baseRole : result){
            baseRole.setIsCheck(false);
            for(BaseRoleUserRel baseRoleUserRel : rel){
                if(baseRole.getId().longValue() == baseRoleUserRel.getRoleId().longValue()){
                    baseRole.setIsCheck(true);
                }
            }
        }

        return result;

    }

    @RequestMapping(value = "/updateUserRel", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateUserRel(Long userId, @RequestParam(value = "roleIds[]") List<Long> roleIds){

        BaseRoleUserRel baseRoleUserRelQuery = new BaseRoleUserRel();
        baseRoleUserRelQuery.setUserId(userId);

        List<BaseRoleUserRel> baseRoleUserRes = baseRoleUserRelRemoteService.selectList(baseRoleUserRelQuery);

        // 删除
        List<BaseRoleUserRel> deleteList = new ArrayList<>();
        for(BaseRoleUserRel baseRoleUserRel :baseRoleUserRes){
            boolean flag = false;
            for(Long id : roleIds){
                if(id.longValue() == baseRoleUserRel.getRoleId().longValue()){
                    flag = true;
                }
            }
            if(!flag){
                deleteList.add(baseRoleUserRel);
            }
        }

        for(BaseRoleUserRel baseRoleUserRel : deleteList){
            baseRoleUserRelRemoteService.delete(baseRoleUserRel.getId(),getAccount().getUserName());
        }

        // 新增
        List<BaseRoleUserRel> insertList = new ArrayList<>();
        for(Long id : roleIds){
            boolean flag = false;
            for(BaseRoleUserRel baseRoleUserRel :baseRoleUserRes){
                if(id.longValue() == baseRoleUserRel.getRoleId().longValue()){
                    flag = true;
                }
            }
            if(!flag){
                BaseRoleUserRel insert = new BaseRoleUserRel();
                insert.setUserId(userId);
                insert.setRoleId(id);
                insertList.add(insert);
            }
        }

        for(BaseRoleUserRel baseRoleUserRel : insertList){
            baseRoleUserRelRemoteService.insert(baseRoleUserRel,getAccount().getUserName());
        }

        return true;
    }

    /**
     * 取得全部菜单
     * @param roleId 角色编号
     * @return 菜单集合含选中状态
     */
    @RequestMapping(value = "/selectMenuRel", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseMenu> selectMenuRel(Long roleId) {

        // 取得全部菜单
        List<BaseMenu> result = baseMenuRemoteService.selectList(new BaseMenu());

        BaseRoleMenuRel baseRoleMenuRelQuery = new BaseRoleMenuRel();
        baseRoleMenuRelQuery.setRoleId(roleId);

        // 取得已配置菜单集合
        List<BaseRoleMenuRel> rel = baseRoleMenuRelRemoteService.selectList(baseRoleMenuRelQuery);

        for(BaseMenu baseMenu : result){
            baseMenu.setIsCheck(false);
            for(BaseRoleMenuRel baseRoleMenuRel : rel){
                if(baseMenu.getId().longValue() == baseRoleMenuRel.getMenuId().longValue()){
                    // 已存在关系时，设置选中状态
                    baseMenu.setIsCheck(true);
                }
            }
        }

        return result;

    }

    @RequestMapping(value = "/updateMenuRel", method = RequestMethod.POST)
    @ResponseBody
    public Boolean updateMenuRel(Long roleId, @RequestParam(value = "menuIds[]") List<Long> menuIds){

        BaseRoleMenuRel baseRoleUserRelQuery = new BaseRoleMenuRel();
        baseRoleUserRelQuery.setRoleId(roleId);

        List<BaseRoleMenuRel> baseRoleMenuRes = baseRoleMenuRelRemoteService.selectList(baseRoleUserRelQuery);

        // 删除
        List<BaseRoleMenuRel> deleteList = new ArrayList<>();
        for(BaseRoleMenuRel baseRoleMenuRel :baseRoleMenuRes){
            boolean flag = false;
            for(Long id : menuIds){
                if(id.longValue() == baseRoleMenuRel.getMenuId().longValue()){
                    flag = true;
                }
            }
            if(!flag){
                deleteList.add(baseRoleMenuRel);
            }
        }

        for(BaseRoleMenuRel baseRoleMenuRel : deleteList){
            baseRoleMenuRelRemoteService.delete(baseRoleMenuRel.getId(),getAccount().getUserName());
        }

        // 新增
        List<BaseRoleMenuRel> insertList = new ArrayList<>();
        for(Long id : menuIds){
            boolean flag = false;
            for(BaseRoleMenuRel baseRoleMenuRel :baseRoleMenuRes){
                if(id.longValue() == baseRoleMenuRel.getMenuId().longValue()){
                    flag = true;
                }
            }
            if(!flag){
                BaseRoleMenuRel insert = new BaseRoleMenuRel();
                insert.setRoleId(roleId);
                insert.setMenuId(id);
                insertList.add(insert);
            }
        }

        for(BaseRoleMenuRel baseRoleMenuRel : insertList){
            baseRoleMenuRelRemoteService.insert(baseRoleMenuRel,getAccount().getUserName());
        }

        return true;
    }

}