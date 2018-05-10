package self.start.web.example

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import self.start.config.TestProperties
import spock.lang.Specification

/**
 *
 * @Author 刘勇 ( liuyong4 )
 * @Date 2018/5/9 上午9:41
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
class ViewControllerTest extends Specification {

    @Autowired
    private TestProperties testProperties;

    def "Multifile"() {
        expect: "bean is not null"
        testProperties != null
    }
}
