package alice.code.creator.common.dao;

/**
 * Mapper映射接口
 * @author contact@liuxp.me
 *
 */
public interface IMapper {

	/**
	 * 获取映射文件命名空间 ，默认使用DAO实例的类名
	 * @return 映射文件命名空间
	 */
	String getNamespace();

	/**
	 * 获取SQL语句全名
	 * @param statementKey 查询id
	 * @return SQL语句全名namespace+statementKey
	 */
	String getStatement(String statementKey);

}
