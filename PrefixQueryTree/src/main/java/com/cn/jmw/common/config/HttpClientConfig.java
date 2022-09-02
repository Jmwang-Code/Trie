package com.cn.jmw.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @author jmw
 * @Description
 * HttpClient是使用HttpClient下包
 * RestTemplate是Spring提供的用于访问Rest服务的客户端，
 * 它提供了很多可以方便访问远程http服务的方法，这些方法可以帮助开发人员减少编写客户端代码的工作量。
 * 特性：
 *
 * 1）面向资源，以资源抽象为核心进行展开的。资源（文档，图片，一条数据）就是存在服务器上的任何信息，我们需要一个唯一的标记去操作这个资源，我们把这个标记称之为URI。
 * 2）可寻址，资源在Web 服务器上都有唯一的地址。
 * 3）联通性，我们在设计的不能把资源隔离开来，要考虑资源关联关系，并且通过超链接的形式将资源关联起来。
 * 4）无状态，只有无状态才能够实现资源的可伸缩性。
 * 5）利用GET、PUT、DELETE、POST来表示操作动作。
 * @date 2022年09月02日 17:30
 * @Version 1.0
 */
@Slf4j
@Configuration
@EnableScheduling
public class HttpClientConfig {

    /**
     * @Author jmw
     * @Description
     * 确定建立连接之前的超时时间（以毫秒为单位）。Determines the timeout in milliseconds until a connection is established.
     * @Date 17:42 2022/9/2
     * @Param
     * @return
     **/
    private static final int CONNECT_TIMEOUT = 30000;

    /**
     * @Author jmw
     * @Description
     * 从连接管理器请求连接时的超时。
     * The timeout when requesting a connection from the connection manager.
     * @Date 17:42 2022/9/2
     * @Param
     * @return
     **/
    private static final int REQUEST_TIMEOUT = 30000;

    /**
     * @Author jmw
     * @Description
     * 等待数据的超时时间
     * The timeout for waiting for data
     * @Date 17:42 2022/9/2
     * @Param
     * @return
     **/
    private static final int SOCKET_TIMEOUT = 60000;

    /**
     * @Author jmw
     * @Description
     * 最大总连接数
     * MAX_TOTAL_CONNECTIONS
     * @Date 17:42 2022/9/2
     * @Param
     * @return
     **/
    private static final int MAX_TOTAL_CONNECTIONS = 50;

    /**
     * @Author jmw
     * @Description
     * 默认保持活动时间
     * DEFAULT_KEEP_ALIVE_TIME_MILLIS
     * @Date 17:42 2022/9/2
     * @Param
     * @return
     **/
    private static final int DEFAULT_KEEP_ALIVE_TIME_MILLIS = 20 * 1000;

    /**
     * @Author jmw
     * @Description
     * 关闭空闲连接等待时间秒
     * CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS
     * @Date 17:42 2022/9/2
     * @Param
     * @return
     **/
    private static final int CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS = 30;

    @Bean
    public PoolingHttpClientConnectionManager poolingConnectionManager() {
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
        } catch (NoSuchAlgorithmException | KeyStoreException e) {
            log.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
        }

        SSLConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(builder.build());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            log.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
        }

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();

        PoolingHttpClientConnectionManager poolingConnectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolingConnectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        return poolingConnectionManager;
    }

    /**
     * @Description 连接保活策略
     **/
    @Bean
    public ConnectionKeepAliveStrategy connectionKeepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {
            @Override
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator
                        (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();

                    if (value != null && param.equalsIgnoreCase("timeout")) {
                        return Long.parseLong(value) * 1000;
                    }
                }
                return DEFAULT_KEEP_ALIVE_TIME_MILLIS;
            }
        };
    }

    /**
     * @Description httpClient
     **/
    @Bean
    public CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(REQUEST_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT).build();

        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(poolingConnectionManager())
                .setKeepAliveStrategy(connectionKeepAliveStrategy())
                .build();
    }

    /**
     * @Description 空闲连接监视器
     **/
    @Bean
    public Runnable idleConnectionMonitor(final PoolingHttpClientConnectionManager connectionManager) {
        return new Runnable() {
            @Override
            @Scheduled(fixedDelay = 10000)
            public void run() {
                try {
                    if (connectionManager != null) {
                        log.trace("run IdleConnectionMonitor - Closing expired and idle connections...");
                        connectionManager.closeExpiredConnections();
                        connectionManager.closeIdleConnections(CLOSE_IDLE_CONNECTION_WAIT_TIME_SECS, TimeUnit.SECONDS);
                    } else {
                        log.trace("run IdleConnectionMonitor - Http Client Connection manager is not initialised");
                    }
                } catch (Exception e) {
                    log.error("run IdleConnectionMonitor - Exception occurred. msg={}, e={}", e.getMessage(), e);
                }
            }
        };
    }

}


