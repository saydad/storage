package self.start.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.thymeleaf.util.MapUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class YMLUtil {
    private static final YAMLFactory yamlFactory = new YAMLFactory();;
    private static final ObjectMapper mapper = new ObjectMapper();;
    private static final Splitter SPLITTER = Splitter.on("_").trimResults();

    static {
        yamlFactory.configure(YAMLGenerator.Feature.WRITE_DOC_START_MARKER, false);
    }
    public static <T> T parse(InputStream inputStream, Class<T> tClass) {
        try {
            YAMLParser parser = yamlFactory.createParser(inputStream);
            JsonNode treeNode = mapper.readTree(parser);
            TreeTraversingParser treeTraversingParser = new TreeTraversingParser(treeNode);
            return mapper.readValue(treeTraversingParser, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        InputStream resourceAsStream = YMLUtil.class.getResourceAsStream("/xx.yml");

        Map parse = parse(resourceAsStream, Map.class);
//配置文件主节点
        Map<String, Object> config = (Map<String, Object>) parse.get("tal_platform_config");
        if (MapUtils.isEmpty(config)) {
            return;
        }

        Map<String, String> tempResult = Maps.newHashMap();
        config.forEach((businessKey, other) -> {
            process(other, (String) businessKey, tempResult);
        });

        Map<String, Object> stringObjectMap = toJson(tempResult);
        Map<String, Object> revMap = Maps.newHashMap();
        revMap.put("tal_platform_config", stringObjectMap);

        Yaml yaml = new Yaml();
        //JsonNode jsonNode = mapper.readTree(mapper.writeValueAsString(tal_platform_config));

        String jsonAsYaml = new YAMLMapper(yamlFactory).writeValueAsString(revMap);
        System.out.printf(jsonAsYaml);
    }

    @SuppressWarnings("unchecked")
    private static void process(Object businessConfigData, String prefixKey, Map<String, String> result) {
        if (businessConfigData instanceof Map) {
            Map<String, Object> businessConfigDataMap = (Map<String, Object>) businessConfigData;
            businessConfigDataMap.forEach((partKey, otherData) -> {
                process(otherData, prefixKey + "_" + partKey, result);
            });
        } else {
            result.put(prefixKey, String.valueOf(businessConfigData));
        }
    }

    private static Map<String, Object> toJson(Map<String, String> rawData) {
        Map<String, Object> result = Maps.newHashMap();
        rawData.forEach((key, value) -> {
            Map<String, Object> tmpMap = result;
            Iterator<String> iterator = SPLITTER.split(key).iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (!iterator.hasNext()) {
                    tmpMap.put(item, value);
                } else {
                    Object o = tmpMap.get(item);
                    if (o == null) {
                        Map<String, Object> newMap;
                        tmpMap.put(item, newMap = Maps.newHashMap());
                        tmpMap = newMap;
                    } else {
                        tmpMap = (Map<String, Object>) o;
                    }
                }
            }
        });
        return result;
    }
}
