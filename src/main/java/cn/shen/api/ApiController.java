package cn.shen.api;

import cn.shen.http.IRequestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author huishen
 * @date 2019-07-19 17:02
 */

@RestController
@RequestMapping(value = "api")
public class ApiController {

    @Autowired
    private IRequestApi requestApi;

    @GetMapping("get_url")
    public Map<String, String> getUrl() {
        Map<String, String> url = requestApi.getUrl();
        return url;
    }

}
