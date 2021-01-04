package alice.code.creator.controller.generator;

import alice.code.creator.domain.Account;
import alice.code.creator.common.util.AccountUtils;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.generator.GeneratorConfigIndividual;
import alice.code.creator.service.generator.GeneratorConfigIndividualService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户个性化配置Controller
 * User: contact@liuxp.me
 * DateTime: 2020-12-12
 */
@Controller
@RequestMapping("/web/generator/config/individual")
public class GeneratorConfigIndividualController extends BaseController{

    /**
     * 用户个性化配置Service接口
     */
    @Resource
    private GeneratorConfigIndividualService generatorConfigIndividualService;

    /**
     * 根据主键查询
     * @return 用户个性化配置
    */
    @RequestMapping(value = "/mine", method = RequestMethod.POST)
    @ResponseBody
    public Result<GeneratorConfigIndividual> mine() {

        // 取得当前登录用户信息
        Account account = AccountUtils.getCurrentUser();

        // 查询该用户个性化配置
        GeneratorConfigIndividual generatorConfigIndividualQuery = new GeneratorConfigIndividual();
        generatorConfigIndividualQuery.setOwnerUserId(account.getId());
        GeneratorConfigIndividual generatorConfigIndividual = generatorConfigIndividualService.selectOne(generatorConfigIndividualQuery);

        // 返回数据
        return new Result<>(generatorConfigIndividual);
    }

    /**
     * 保存数据
     * @param generatorConfigIndividual 用户个性化配置数据实体
     * @return 执行结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result<GeneratorConfigIndividual> save(GeneratorConfigIndividual generatorConfigIndividual) {

        if(null==generatorConfigIndividual.getId()){

            Account account = AccountUtils.getCurrentUser();
            generatorConfigIndividual.setOwnerUserId(account.getId());

            // 执行插入，返回已设置主键的数据实体
            generatorConfigIndividual = generatorConfigIndividualService.insert(generatorConfigIndividual,getAccount().getUserName());
        }else{
            // 执行更新
            generatorConfigIndividualService.update(generatorConfigIndividual,getAccount().getUserName());
        }

        return new Result<>(generatorConfigIndividual);
    }
}