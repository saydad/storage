package self.start.test.daily;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @Author 刘勇(liuyong4)
 * @Date 2018/5/7 下午8:13
 **/
public class Test {
    /**
     * 解析yml格式文件的必要属性
     */
    private static final YAMLFactory YAML_FACTORY;
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        YAML_FACTORY = new YAMLFactory();
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    /**
     * 解析yml格式配置文件，使用方式见 https://www.cnblogs.com/shouhongxiao/p/5803644.html
     * @param inputStream 文件流
     * @param targetClass 转换的目标Class
     * @param fileName 记录日志使用，解析的文件名称
     */
    public static <T> Optional<T> parse(InputStream inputStream, Class<T> targetClass, String fileName) {
        if (inputStream == null || targetClass == null) {
            return Optional.empty();
        }

        try {
            YAMLParser parser = YAML_FACTORY.createParser(inputStream);
            JsonNode node = OBJECT_MAPPER.readTree(parser);
            return Optional.ofNullable(OBJECT_MAPPER.readValue(new TreeTraversingParser(node), targetClass));
        } catch (IOException e) {
            System.out.print("解析yml格式配置文件异常" + e);
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        try (InputStream inputStream = Test.class.getResourceAsStream("/application-config.yml")) {
            Optional<Map> parse = parse(inputStream, Map.class, "application-config.yml");
            System.out.printf(parse.toString());
        } catch (IOException e) {
            System.out.print(e.toString());
        }
    }

    @org.junit.Test
    public void sort() {
        Map<String, String> param = Maps.newTreeMap(Ordering.natural());
        param.put("stu_id", "124dsaf");
        param.put("city_id", "fdsaaf");
        param.put("type", "1");
        param.put("level", "1");
        param.put("service_id", "45f4dafd");

        System.out.println(param);
    }

    @org.junit.Test
    public void temp() {

    }
}
