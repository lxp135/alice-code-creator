package alice.code.creator.service.base;

import alice.code.creator.common.service.IService;

/**
 * 菜单基本信息表Service接口
 * @author contact@liuxp.me
 */
public interface BaseMenuService extends IService {

    int delete(Long id, String deleteUser);
}