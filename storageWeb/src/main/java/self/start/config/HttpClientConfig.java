package self.start.config;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by liuyong4 2018/5/11 下午7:23
 **/
@Configuration
public class HttpClientConfig {

    @Bean("httpClient")
    public CloseableHttpClient reminderApiHttpClientWrapper() {
        PoolingHttpClientConnectionManager connectionManager = getHttpClientConnectionManager(8);
        return newInstanceUseDefaultRequestConfig(connectionManager);
    }

    private CloseableHttpClient newInstanceUseDefaultRequestConfig(PoolingHttpClientConnectionManager connectionManager) {
        //ConnectionRequestTimeout 从连接池中获取链接的超时时间
        //ConnectTimeout  连接建立时间，三次握手完成时间
        //SocketTimeout  数据传输过程中数据包之间间隔的最大时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(2 * 1000).setConnectTimeout(1 * 1000).setSocketTimeout(20 * 1000).build();

        return HttpClients.custom().setRetryHandler(new DefaultHttpRequestRetryHandler(3, false)).
                setConnectionManager(connectionManager).setServiceUnavailableRetryStrategy(new DefaultServiceUnavailableRetryStrategy(3, 1000L)).setDefaultRequestConfig(requestConfig)
                .build();

    }

    private PoolingHttpClientConnectionManager getHttpClientConnectionManager(int maxPerRoute) {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(maxPerRoute);
        cm.setDefaultMaxPerRoute(maxPerRoute);
        return cm;
    }

    private class DefaultServiceUnavailableRetryStrategy implements ServiceUnavailableRetryStrategy {
        private Integer retryCount;
        private Long retryInterval;

        public DefaultServiceUnavailableRetryStrategy(Integer retryCount, Long retryInterval) {
            this.retryCount = retryCount;
            this.retryInterval = retryInterval;
        }

        @Override
        public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
            int code = response.getStatusLine().getStatusCode();
            return code > HttpStatus.SC_INTERNAL_SERVER_ERROR && executionCount <= this.retryCount;
        }

        @Override
        public long getRetryInterval() {
            return this.retryInterval;
        }
    }
}
