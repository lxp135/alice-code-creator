package alice.code.creator.service.generator;

import alice.code.creator.domain.model.generator.GeneratorConfigDatasource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import alice.code.creator.domain.model.generator.ColumnGenerator;
import alice.code.creator.domain.model.generator.MysqlGenerator;

import java.util.List;

/**
 * MYSQL元数据生成代码Service层接口类
 * @author contact@liuxp.me
 */
public interface GeneratorService {

    /**
     * 生成代码-压缩包下载
     */
    ResponseEntity<byte[]> generatorDownLoad(MysqlGenerator mysqlGenerator, HttpHeaders headers);

    /**
     * 取得数据源信息
     * @param datasourceId 数据源编号
     * @return 数据源信息
     */
    GeneratorConfigDatasource getDataSource(Long datasourceId);

}