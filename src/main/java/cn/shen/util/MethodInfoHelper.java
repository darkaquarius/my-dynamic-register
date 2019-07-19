package cn.shen.util;

import cn.shen.bean.MethodInfo;
import cn.shen.httpUtil.annotation.HttpRequest;

import java.lang.reflect.Method;

/**
 * @author huishen
 * @date 2019-07-10 19:52
 */
public class MethodInfoHelper {

    public static MethodInfo extractMethodInfo(Method method) {
        HttpRequest request = method.getAnnotation(HttpRequest.class);
        return new MethodInfo(request.method(), request.url());
    }

}
