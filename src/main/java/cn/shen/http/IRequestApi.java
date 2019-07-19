package cn.shen.http;

import cn.shen.httpUtil.annotation.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author huishen
 * @date 2019-07-10 16:05
 */

@Component
public interface IRequestApi {

    @HttpRequest(method= RequestMethod.GET, url = "http://www.baidu.com")
    Map<String, String> getUrl();

    @HttpRequest(method= RequestMethod.POST, url = "http://www.google.com")
    Map<String, String> postUrl();

}
