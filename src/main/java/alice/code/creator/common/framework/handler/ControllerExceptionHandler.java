package alice.code.creator.common.framework.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import alice.code.creator.domain.constant.ResultCodeConstant;
import alice.code.creator.domain.model.Result;

import java.sql.SQLException;

/**
 * Controller通用异常处理
 * SpringMVC自身提供的全局异常处理类，仅捕获Controller级别抛出异常
 * 被捕获异常按照顺序匹配处理方法（仅匹配最前面的一个方法）
 * @author contact@liuxp.me
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 参数异常
     * @param e 被捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Result<Boolean> handleIllegalArgumentException(IllegalArgumentException e){
        e.printStackTrace();
        return new Result<>(ResultCodeConstant.ILLEGAL_ARGUMENT_EXCEPTION,e.getMessage());
    }

    /**
     * 数据库异常
     * @param e 被捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result<Boolean> handleSQLException(SQLException e){
        e.printStackTrace();
        return new Result<>(ResultCodeConstant.SQL_EXCEPTION,"数据库异常！");
    }

    /**
     * 运行时异常
     * @param e 被捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result<Boolean> handleRuntimeException(RuntimeException e){
        e.printStackTrace();
        return new Result<>(ResultCodeConstant.RUNTIME_EXCEPTION,e.getMessage());
    }

    /**
     * 其他异常
     * @param e 被捕获的异常
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Boolean> handleException(Exception e){
        e.printStackTrace();
        return new Result<>(ResultCodeConstant.EXCEPTION,e.getMessage());
    }
}
