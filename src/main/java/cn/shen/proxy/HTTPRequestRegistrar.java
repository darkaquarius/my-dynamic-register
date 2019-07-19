package cn.shen.proxy;

import cn.shen.bean.MethodInfo;
import cn.shen.httpUtil.annotation.HttpRequest;
import cn.shen.util.MethodInfoHelper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;

/**
 * @author huishen
 * @date 2019-07-10 19:21
 *
 *
 */

@Component
public class HTTPRequestRegistrar {

    /**
     * 生成代理类
     */
    @SuppressWarnings("unchecked")
    public <T> T createProxy(Class<T> classType) {
        return  (T) Proxy.newProxyInstance(
            this.getClass().getClassLoader(),
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
