package self.start.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lyongy.liu on 下午 8:35.
 */
@Component
@ConfigurationProperties(prefix = "tal.platform")
public class TestProperties {

    private Map<String, Map<String, String>> config;

    public Map<String, Map<String, String>> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Map<String, String>> config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "TestProperties{" +
                "config=" + config +
                '}';
    }
}
