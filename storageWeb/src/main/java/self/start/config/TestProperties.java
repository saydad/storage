package self.start.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lyongy.liu on 下午 8:35.
 */
@Component
@PropertySource("classpath:/test.properties")
@ConfigurationProperties(prefix = "test")
public class TestProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
