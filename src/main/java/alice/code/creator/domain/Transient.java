package alice.code.creator.domain;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 指明实体中的字段非持久化
 * 创建日期：2018/1/12
 * @author contact@liuxp.me
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Transient {

}