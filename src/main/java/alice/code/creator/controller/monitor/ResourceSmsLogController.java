package alice.code.creator.controller.monitor;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.monitor.ResourceSmsLog;
import alice.code.creator.service.monitor.ResourceSmsLogService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 短信发送日志表Controller
 * User: contact@liuxp.me
 * DateTime: 2018-12-12
 */
@Controller
@RequestMapping("/web/basic/resource/sms/log")
@Secured("BASIC_RESOURCE_SMS")
public class ResourceSmsLogController extends BaseController {

    /**
     * 短信发送日志表RemoteService接口
     */
    @Resource
    private ResourceSmsLogService resourceSmsLogService;

    /**
     * 根据条件分页查询
     * @param resourceSmsLog 短信发送日志表数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<ResourceSmsLog> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<ResourceSmsLog> selectPage(ResourceSmsLog resourceSmsLog,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                           @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
                                           @RequestParam(value = "sidx", required = false, defaultValue = "updateTime") String sortName,
                                           @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<ResourceSmsLog> page = buildPage(resourceSmsLog, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return resourceSmsLogService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param resourceSmsLog 短信发送日志表数据实体
     * @return 短信发送日志表列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<ResourceSmsLog> selectList(ResourceSmsLog resourceSmsLog) {

        return resourceSmsLogService.selectList(resourceSmsLog);

    }

    /**
     * 根据主键查询
     * @param id 自增主键
     * @return 短信发送日志表
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public ResourceSmsLog selectOne(Long id) {

        return resourceSmsLogService.selectOne(id);

    }


}