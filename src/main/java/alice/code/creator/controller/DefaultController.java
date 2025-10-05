package alice.code.creator.controller;

import alice.code.creator.domain.Account;
import alice.code.creator.common.util.AccountUtils;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.base.BaseMenu;
import alice.code.creator.domain.model.base.BaseUser;
import alice.code.creator.service.base.BaseUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 默认控制器
 * @author contact@liuxp.me
 */
@Controller
public class DefaultController {

	// 用户信息表Service
	@Resource
	private BaseUserService baseUserService;

	/**
	 * 首页
	 * @return 模型视图
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "main.html";
	}

	/**
	 * 登录页
	 * @param error 错误提示
	 * @param model 模型参数
	 * @return 模型视图
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(String error, Model model) {
		if(StringUtils.isNotEmpty(error)){
			model.addAttribute("error", error);
		}
		return "login.html";
	}

	/**
	 * 用户注册
	 * @param baseUser 用户信息
	 * @return 返回结果
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Result<BaseUser> register(BaseUser baseUser){

		if(null==baseUser.getUserAccount()||"".equals(baseUser.getUserAccount())){
			throw new IllegalArgumentException("用户名不能为空！");
		}

		if(null==baseUser.getUserPassword()||"".equals(baseUser.getUserPassword())){
			throw new IllegalArgumentException("密码不能为空！");
		}

		BaseUser baseUserQuery = new BaseUser();
		baseUserQuery.setUserAccount(baseUser.getUserAccount());

		// 账号是否重复
		List<BaseUser> baseUserList = baseUserService.selectList(baseUserQuery);
		if(null!=baseUserList&&baseUserList.size()>0){
			throw new IllegalArgumentException("用户名重复，请尝试其他账号！");
		}

		// 设置用户名（账号）为姓名
		baseUser.setUserName(baseUser.getUserAccount());

		return baseUserService.register(baseUser);

	}

	/**
	 * 取得当前用户拥有菜单列表
	 * @return 菜单集合
	 */
	@RequestMapping(value = "/web/menu", method = RequestMethod.POST)
	@ResponseBody
	public List<BaseMenu> menu() {
		List<BaseMenu> menuTrees = new ArrayList<>();
		if(AccountUtils.getCurrentUser().getBaseMenus() != null){
			menuTrees = AccountUtils.getCurrentUser().getBaseMenus();
		}
		return menuTrees;
	}

	/**
	 * 取得当前登录用户信息
	 * @return Account 用户信息
	 */
	@RequestMapping(value = "/web/account", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getLoginUserInfo(){
		Account account = AccountUtils.getCurrentUser();

		BaseUser baseUser = baseUserService.selectOne(account.getId());

		Map<String, Object> map = new HashMap<>();
		map.put("id",baseUser.getId()); 						// 用户编号
		map.put("userAccount",baseUser.getUserAccount()); 	// 用户账号
		map.put("userName",baseUser.getUserName()); 			// 用户姓名
		map.put("userPhone",baseUser.getUserPhone()); 		    // 用户手机号
		map.put("userFace",baseUser.getUserFace());			// 用户头像
		map.put("locked",account.isLocked()); 				    // 用户锁定状态

		return map;
	}

	/**
	 * 判断用户是否登录
	 * @return 是否登录
	 */
	@RequestMapping(value = "/isLogin", method = RequestMethod.GET)
	@ResponseBody
	public boolean isLogin(){
	    try{
            Account account = AccountUtils.getCurrentUser();
            return account!= null;
        }catch (Exception e){
	        e.printStackTrace();
        }
	    return false;
	}
}