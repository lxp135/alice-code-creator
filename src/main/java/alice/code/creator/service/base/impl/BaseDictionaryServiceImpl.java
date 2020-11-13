package alice.code.creator.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import alice.code.creator.common.service.AbstractService;
import alice.code.creator.dao.base.BaseDictionaryDao;
import alice.code.creator.service.base.BaseDictionaryService;

/**
 * 数据字典表Service实现类
 * @author contact@liuxp.me
 */
@Service
public class BaseDictionaryServiceImpl extends AbstractService implements BaseDictionaryService {

	@Autowired
	public void setDao(BaseDictionaryDao dao) {
		this.iDao = dao;
	}
}