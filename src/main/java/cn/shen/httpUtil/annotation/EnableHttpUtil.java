package cn.shen.httpUtil.annotation;

import cn.shen.httpUtil.HttpUtilRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huishen
 * @date 2019-07-19 17:11
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(HttpUtilRegistrar.class)
public @interface EnableHttpUtil {
}
