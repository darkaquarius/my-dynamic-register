package cn.shen.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author huishen
 * @date 2019-07-10 19:54
 */

@Data
@AllArgsConstructor
public class MethodInfo {

    private RequestMethod method;

    private String url;

}
