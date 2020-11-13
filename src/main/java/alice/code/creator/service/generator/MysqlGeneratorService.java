package alice.code.creator.service.generator;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import alice.code.creator.domain.model.generator.ColumnGenerator;
import alice.code.creator.domain.model.generator.MysqlGenerator;

import java.util.List;

/**
 * MYSQL元数据生成代码Service层接口类
 * @author contact@liuxp.me
 */
public interface MysqlGeneratorService {

    /**
     * 查询数据库列表
     * @return 数据库列表
     */
    List<ColumnGenerator> selectDatabase();

    /**
     * 根据数据库名查询所有表
     * @param columnGenerator 字段信息实体类
     * @return 表列表
     */
    List<ColumnGenerator> selectTableNames(ColumnGenerator columnGenerator);

    /**
     * 根据数据库名查询所有字段
     * @param columnGenerator 字段信息实体类
     * @return 字段列表
     */
    List<ColumnGenerator> selectColumnNames(ColumnGenerator columnGenerator);

    /**
     * 生成代码-压缩包下载
     */
    ResponseEntity<byte[]> generatorDownLoad(MysqlGenerator mysqlGenerator, HttpHeaders headers);

}
