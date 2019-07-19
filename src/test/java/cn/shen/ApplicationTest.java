package cn.shen;

import cn.shen.http.IRequestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author huishen
 * @date 2019-07-10 16:03
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private IRequestApi requestApi;

    @Test
    public void testGetUrl() {
        Map<String, String> map = requestApi.getUrl();
        System.out.println(map);
    }

    @Test
    public void testPostUrl() {
        Map<String, String> map = requestApi.postUrl();
        System.out.println(map);
    }

}
