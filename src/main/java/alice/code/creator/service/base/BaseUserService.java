package alice.code.creator.service.base;

import alice.code.creator.common.service.IService;
import alice.code.creator.domain.model.Result;
import alice.code.creator.domain.model.base.BaseUser;

/**
 * 用户基本信息表Service接口
 * @author contact@liuxp.me
 */
public interface BaseUserService extends IService {

    /**
     * 用户注册
     * @param baseUser 用户信息
     * @return Result<BaseUser>
     */
    Result<BaseUser> register(BaseUser baseUser);
}