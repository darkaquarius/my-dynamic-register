package cn.shen.httpUtil.annotation;

import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huishen
 * @date 2019-07-10 18:53
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpRequest {

    RequestMethod method() default RequestMethod.GET;

    String url() default "";

}
