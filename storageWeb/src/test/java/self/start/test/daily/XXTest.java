package self.start.test.daily;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.rabbitmq.tools.json.JSONUtil;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * java - josn 中文转义为unicode问题
 * create by liuyong4 2018/5/29 下午8:55
 **/
public class XXTest {

    @Test
    public void Test () throws ClassNotFoundException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String content = "北京";
        String s1 = StringEscapeUtils.escapeJava(content);
        String s = s1.toLowerCase();
        Map<String, String> map = Maps.newHashMap();
        map.put("param", s);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(map);
        String s2 = StringEscapeUtils.unescapeJava(jsonNode.toString());
        System.out.println(s2);
    }
}
