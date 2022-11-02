package alice.code.creator.domain.model.generator;

import alice.code.creator.domain.model.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 表信息实体类
 * @author contact@liuxp.me
 */
@Setter
@Getter
public class DatabaseTable extends AbstractEntity {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;
    /**
     * 字段集合
     */
    private List<ColumnGenerator> columnList;

}