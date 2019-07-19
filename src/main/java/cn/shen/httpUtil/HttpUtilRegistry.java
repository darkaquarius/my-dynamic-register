package cn.shen.httpUtil;

import cn.shen.bean.MethodInfo;
import cn.shen.util.MethodInfoHelper;

import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @author huishen
 * @date 2019-07-10 19:21
 *
 *
 */

public class HttpUtilRegistry {

    /**
     * 生成代理类
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxy(Class<T> classType) {
        return  (T) Proxy.newProxyInstance(
            HttpUtilRegistry.class.getClassLoader(),
            new Class[]{classType},
            (proxy, method, args) -> {
                MethodInfo methodInfo = MethodInfoHelper.extractMethodInfo(method);
                HashMap<String, String> map = new HashMap<String, String>() {{
                    put("requestMethod", methodInfo.getMethod().toString());
                    put("url", methodInfo.getUrl());
                }};
                return map;
            }
        );
    }

}
