package self.start.web.example;

import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * create by liuyong4 2018/5/11 下午8:15
 **/
@RestController
public class OrderController {

    @Resource
    private CloseableHttpClient httpClient;


    @PostMapping(value = "/getOrderPageList")
    @HystrixCommand(
            fallbackMethod = "getOrderPageListFallback",
            threadPoolProperties = {  //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20")},
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"), //命令执行超时时间
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //若干10s一个窗口内失败三次, 则达到触发熔断的最少请求量
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000") //断路30s后尝试执行, 默认为5s
            })
    public String getOrderPageList() {
        try {
            System.out.println("====== main function start ======");
            CloseableHttpResponse execute = httpClient.execute(new HttpGet("http://localhost:8080/error"));
            if (execute != null && execute.getEntity() != null) {
                return EntityUtils.toString(execute.getEntity());
            }
            System.out.println("====== main function end ======");
            return "try";
        } catch (IOException e) {
            System.out.println("====== main function exception ======");
            System.out.println(e);
            return "except";
        }
    }

    public String getOrderPageListFallback(){
        System.out.println("===================== 执行降级策略");
        return "fallback";
    }
}
