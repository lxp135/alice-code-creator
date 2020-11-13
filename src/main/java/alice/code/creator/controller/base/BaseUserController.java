package alice.code.creator.controller.base;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.base.BaseUser;
import alice.code.creator.service.base.BaseUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户基本信息表Controller
 * User: contact@liuxp.me
 * DateTime: 2018-06-27
 */
@Controller
@RequestMapping("/web/basic/base/user")
@Secured("BASIC_USER")
public class BaseUserController extends BaseController {

    /**
     * 用户基本信息表RemoteService接口
     */
    @Resource
    private BaseUserService baseUserService;

    /**
     * 根据条件分页查询
     * @param baseUser 用户基本信息表数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<BaseUser> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<BaseUser> selectPage(BaseUser baseUser,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
                                     @RequestParam(value = "sidx", required = false, defaultValue = "updateTime") String sortName,
                                     @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<BaseUser> page = buildPage(baseUser, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return baseUserService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param baseUser 用户基本信息表数据实体
     * @return 用户基本信息表列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<BaseUser> selectList(BaseUser baseUser) {

        return baseUserService.selectList(baseUser);
    }

    /**
     * 根据主键查询
     * @param id 主键
     * @return 用户基本信息表
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public BaseUser selectOne(Long id) {

        return baseUserService.selectOne(id);

    }

    /**
     * 保存数据
     * @param baseUser 用户基本信息表数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public Result<BaseUser> insert(BaseUser baseUser) {

        baseUser.setUserName(getAccount().getUserName());

        buildInsert(baseUser);

        // 调用用户注册方法
        return baseUserService.register(baseUser);
    }

    /**
     * 修改数据
     * @param baseUser 用户基本信息表数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Boolean update(BaseUser baseUser) {

        // 密码加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String passwordEncrypt = bCryptPasswordEncoder.encode(baseUser.getUserPassword());
        baseUser.setUserPassword(passwordEncrypt);

        buildUpdate(baseUser);

        // 执行更新
        int rows =  baseUserService.update(baseUser,getAccount().getUserName());
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
        int rows = baseUserService.delete(id,getAccount().getUserName());
        return rows > 0;
    }

}