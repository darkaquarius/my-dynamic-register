package cn.shen;

import cn.shen.http.IRequestApi;
import cn.shen.proxy.HTTPRequestRegistrar;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author huishen
 * @date 2019-07-10 16:02
 */

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }

    /**
     * 注册bean
     * 方法一：直接生成IRequestApi
     *
     * 这里是写死了，要注入IRequestApi
     */
    // @Bean
    // public IRequestApi requestApi() {
    //     IRequestApi ret = (IRequestApi) Proxy.newProxyInstance(
    //         this.getClass().getClassLoader(),
    //         new Class[]{IRequestApi.class},
    //         (proxy, method, args) -> {
    //             HttpRequest request = method.getAnnotation(HttpRequest.class);
    //             RequestMethod requestMethod = request.method();
    //             String url = request.url();
    //             HashMap<String, String> map = new HashMap<String, String>() {{
    //                 put("requestMethod", requestMethod.toString());
    //                 put("url", url);
    //             }};
    //             return map;
    //         }
    //     );
    //     return ret;
    // }

    /**
     * 注册bean
     * 方法二：生成FactoryBean
     *
     * 这里是写死了，要注入IRequestApi
     */
    @Bean
    public FactoryBean<IRequestApi> requestApi() {
        FactoryBean<IRequestApi> factoryBean = new FactoryBean<IRequestApi>() {
            @Override
            public IRequestApi getObject() throws Exception {
                return HTTPRequestRegistrar.createProxy(getObjectType());
            }

            @Override
            public Class<IRequestApi> getObjectType() {
                return IRequestApi.class;
            }
        };
        return factoryBean;
    }

}
