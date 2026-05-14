package com.ruoyi.work;


import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.util.HttpClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.ConnectTimeoutException;

import javax.annotation.Resource;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class EsHttpClient {

    private static final String HEADER_AUTHORIZATION_KEY = "Authorization";

    private static final String HEADER_AUTHORIZATION_VALUE_PREFIX = "Basic ";

    private static final String DEFAULT_SCHEMA = "http";

    private static final String SLASH = "/";

    private static final String URL_PATH_SEARCH = "_search";

    @Resource
    private HttpClient httpClient;

    private ConfigProperties config;

    private HttpHost[] httpHosts;

    private String authorizationHeaderValue;

    public EsHttpClient(ConfigProperties config) {
        this.config = config;

        List<HttpHost> hostList = new ArrayList<>();
        Arrays.stream(config.getAddress().split(",")).forEach(item -> {
            String[] strings = item.split(":");
            hostList.add(new HttpHost(strings[0], Integer.parseInt(strings[1]), DEFAULT_SCHEMA));
        });
        // 转换成 HttpHost 数组
        httpHosts = hostList.toArray(new HttpHost[]{});

        String username = config.getUsername();
        String password = config.getPassword();
        if (StringUtils.isBlank(username) && StringUtils.isBlank(password)) {
            return;
        }

        StringBuilder tmp = new StringBuilder();
        tmp.append(username).append(":").append(password);
        Base64 base64codec = new Base64(0);
        String base64password = base64codec.encodeAsString(tmp.toString().getBytes(StandardCharsets.UTF_8));
        this.authorizationHeaderValue = HEADER_AUTHORIZATION_VALUE_PREFIX + base64password;
    }

    /**
     * 执行ES复杂搜索
     *
     * @param indexName   索引名称
     * @param requestBody 请求 JSON
     * @return 服务器返回的 JSON
     */
    public String search(String indexName, String requestBody) {
        int count = 0;
        return doTry(count, indexName, requestBody);
    }

    private String doTry(int count, String indexName, String requestBody) {
        if (count >= httpHosts.length) {
            // 没有更多地址去重试
            throw new RuntimeException("No avaiable ES host, current hosts: " + Arrays.toString(httpHosts));
        }

        HttpHost httpHost = httpHosts[count];

        try {
            return this.doSearch(httpHost, indexName, requestBody);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);

            if (ExceptionUtils.indexOfThrowable(ex, ConnectTimeoutException.class) >= 0
                || ExceptionUtils.indexOfThrowable(ex, SocketTimeoutException.class) >= 0) {
                // 重试次数加1，进行下次重试
                count++;
                return this.doTry(count, indexName, requestBody);
            } else {
                throw ex;
            }
        }
    }

    private String doSearch(HttpHost httpHost, String indexName, String requestBody) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(httpHost.toURI()).append(SLASH).append(indexName).append(SLASH).append(URL_PATH_SEARCH);
        String url = urlBuilder.toString();
        log.info("ES search url is [{}]", url);

        if (StringUtils.isNotBlank(this.authorizationHeaderValue)) {
            Map<String, String> headers = new HashMap<>();
            headers.put(HEADER_AUTHORIZATION_KEY, this.authorizationHeaderValue);
            return this.httpClient.post(url, requestBody, headers);
        } else {
            return this.httpClient.post(url, requestBody);
        }
    }

    @Data
    public static class ConfigProperties {

        private String address;

        private String username;

        private String password;
    }

}
