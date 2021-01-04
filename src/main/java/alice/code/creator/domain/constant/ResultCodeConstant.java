package alice.code.creator.domain.constant;

/**
 * 返回结果编码常量
 * @author contact@liuxp.me
 */
public final class ResultCodeConstant {

    // 正常
    public static final Integer SUCCESS = 200;

    // 服务器内部错误
    public static final Integer EXCEPTION = 500;

    // 运行时异常
    public static final Integer RUNTIME_EXCEPTION = 501;

    // 参数异常
    public static final Integer ILLEGAL_ARGUMENT_EXCEPTION = 502;

    // 数据库异常
    public static final Integer SQL_EXCEPTION = 503;

    // 业务异常
    public static final Integer BUSINESS_EXCEPTION = 504;
}
