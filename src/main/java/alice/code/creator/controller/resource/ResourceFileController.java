package alice.code.creator.controller.resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.controller.BaseController;
import alice.code.creator.domain.model.Page;
import alice.code.creator.domain.model.resource.ResourceFile;
import alice.code.creator.service.resource.ResourceFileService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文件存储信息表Controller
 * User: contact@liuxp.me
 * DateTime: 2018-12-12
 */
@Controller
@RequestMapping("/web/basic/resource/file")
@Secured("BASIC_RESOURCE_FILE")
public class ResourceFileController extends BaseController {

    /**
     * 文件存储信息表RemoteService接口
     */
    @Resource
    private ResourceFileService resourceFileService;

    /**
     * 根据条件分页查询
     * @param resourceFile 文件存储信息表数据实体
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param sortName 排序字段
     * @param sortOrder 排序方式
     * @return Page<ResourceFile> 分页列表
     */
    @RequestMapping(value = "/selectPage", method = RequestMethod.POST)
    @ResponseBody
    public Page<ResourceFile> selectPage(ResourceFile resourceFile,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                         @RequestParam(value = "rows", required = false, defaultValue = "20") int pageSize,
                                         @RequestParam(value = "sidx", required = false, defaultValue = "updateTime") String sortName,
                                         @RequestParam(value = "sord", required = false, defaultValue = "desc") String sortOrder) {

        Page<ResourceFile> page = buildPage(resourceFile, pageNum,pageSize,sortName,sortOrder);
        // 执行查询
        return resourceFileService.selectPage(page);
    }

    /**
     * 根据条件查询
     * @param resourceFile 文件存储信息表数据实体
     * @return 文件存储信息表列表
    */
    @RequestMapping(value = "/selectList", method = RequestMethod.POST)
    @ResponseBody
    public List<ResourceFile> selectList(ResourceFile resourceFile) {

        return resourceFileService.selectList(resourceFile);


    }

    /**
     * 根据主键查询
     * @param id 主键
     * @return 文件存储信息表
    */
    @RequestMapping(value = "/selectOne", method = RequestMethod.POST)
    @ResponseBody
    public ResourceFile selectOne(Long id) {

        return resourceFileService.selectOne(id);

    }


}