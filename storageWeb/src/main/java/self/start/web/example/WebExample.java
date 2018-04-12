package self.start.web.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import self.start.config.TestProperties;

import javax.annotation.Resource;

/**
 * spring boot web例子
 * @author lyongy.liu on 下午 9:05.
 */
@RestController
public class WebExample {

    @Resource
    private TestProperties testProperties;

    @RequestMapping("/aa")
    public String handle() {
        return testProperties.getName();
    }
}
