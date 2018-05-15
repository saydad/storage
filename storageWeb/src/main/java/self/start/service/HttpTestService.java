package self.start.service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * create by liuyong4 2018/5/11 下午7:29
 **/
@Component
public class HttpTestService {

    @Resource
    private CloseableHttpClient httpClient;

    public void test500(String url) {
        try {
            CloseableHttpResponse execute = httpClient.execute(new HttpGet(url));
            HttpEntity entity = execute.getEntity();
            System.out.println(entity);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
