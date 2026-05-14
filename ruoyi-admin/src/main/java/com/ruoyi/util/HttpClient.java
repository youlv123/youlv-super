package com.ruoyi.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component("customHttpClient")
public class HttpClient implements Closeable {

    private static final String HEADER_NAME_CONTENT_TYPE = "Content-type";

    private static final String HEADER_VALUE_JSON_UTF8 = "application/json";

    private HttpClientBuilder httpClientBuilder;

    private CloseableHttpClient client;

    public HttpClient(HttpClientBuilder httpClientBuilder) {
        this.httpClientBuilder = httpClientBuilder;
        init();
    }

    public static void main(String[] args) {
        String ss = "刘双叶";
        byte[] bytes = ss.getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }

    private void init() {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslsf)
                .build();
            // 初始化连接管理器
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
            cm.setMaxTotal(200);
            // 设置每个路由的最大连接数 ip+port
            cm.setDefaultMaxPerRoute(100);
            // 根据默认超时限制初始化requestConfig
            int socketTimeout = 60000;
            int connectTimeout = 5000;
            int connectionRequestTimeout = 5000;
            RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setConnectTimeout(connectTimeout)
                .setExpectContinueEnabled(false)
                .build();

            client = httpClientBuilder.setConnectionManager(cm)
                .setDefaultRequestConfig(requestConfig)
                .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getWithPathParams(String url, Map<String, String> pathParams) {
        url = buildUrl(url, pathParams);
        return this.get(url, null);
    }

    private String buildUrl(String url, Map<String, String> pathParams) {
        try {
            URIBuilder builder = new URIBuilder(url);
            if (MapUtils.isNotEmpty(pathParams)) {
                for (Map.Entry<String, String> entry : pathParams.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            return builder.build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public String get(String url) {
        return this.get(url, null);
    }

    public String get(String url, Map<String, String> headers) {
        HttpGet httpget = new HttpGet(url);
        this.composeRequest(httpget, null, headers);
        return getResponseBody(httpget);
    }

    public String post(String url, Object data) {
        return post(url, data, null);
    }

    public String post(String url, Object data, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);
        composeRequest(post, data, headers);
        return getResponseBody(post);
    }

    private String getResponseBody(HttpUriRequest request) {
        log.debug("Request URL: {} {}", request.getMethod(), request.getURI());
        for (Header header : request.getAllHeaders()) {
            log.debug("Request headers: {} {}", header.getName(), header.getValue());
        }

        try (CloseableHttpResponse response = client.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            log.debug("Response code: {}", statusCode);
            String body = convertResponseBody(response.getEntity());
            log.debug("Response body: {}", body);
            if (statusCode < HttpStatus.SC_OK || statusCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
                throw new RuntimeException("Meet response error :" + body);
            }
            return body;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private String convertResponseBody(HttpEntity entity) throws IOException {
        if (entity == null) {
            return null;
        }
        Header contentEncodingHeader = entity.getContentEncoding();
        String contentEncoding = null;
        if (contentEncodingHeader != null) {
            contentEncoding = contentEncodingHeader.getValue();
        }

        if ("gzip".equals(contentEncoding)) {
            return EntityUtils.toString(new GzipDecompressingEntity(entity));
        } else if ("deflate".equals(contentEncoding)) {
            return EntityUtils.toString(new DeflateDecompressingEntity(entity));
        } else {
            return EntityUtils.toString(entity);
        }
    }

    public byte[] getByte(String url, Map<String, String> headers) {
        HttpGet httpget = new HttpGet(url);
        this.composeRequest(httpget, null, headers);
        return getResponseBodyByte(httpget);
    }

    private byte[] getResponseBodyByte(HttpUriRequest request) {
        log.debug("Request URL: {} {}", request.getMethod(), request.getURI());
        for (Header header : request.getAllHeaders()) {
            log.debug("Request headers: {} {}", header.getName(), header.getValue());
        }

        try (CloseableHttpResponse response = client.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            log.debug("Response code: {}", statusCode);
            byte[] body = convertResponseBodyByte(response.getEntity());
            log.debug("Response body: {}", body);
            if (statusCode < HttpStatus.SC_OK || statusCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
                throw new RuntimeException("Meet response error :" + body);
            }
            return body;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private byte[] convertResponseBodyByte(HttpEntity entity) throws IOException {
        if (entity == null) {
            return null;
        }
        return EntityUtils.toByteArray(entity);
    }

    private void composeRequest(HttpRequestBase request, Object data, Map<String, String> headers) {
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
        if (data != null && request instanceof HttpEntityEnclosingRequestBase) {
            request.setHeader(HEADER_NAME_CONTENT_TYPE, HEADER_VALUE_JSON_UTF8);
            String bodyString;
            if (data instanceof String) {
                bodyString = (String) data;
            } else {
                bodyString = JsonUtil.objToJson(data);
            }
            StringEntity body = new StringEntity(bodyString, ContentType.APPLICATION_JSON);
            ((HttpEntityEnclosingRequestBase) request).setEntity(body);
            log.debug("Request body: {}", bodyString);
        }
    }

    /**
     * post上传文件
     *
     * @param url     请求地址
     * @param data    请求体
     * @param headers 请求头
     * @return 上传成功的文件id
     * @deprecated
     */
    public String filePost(String url, Map<String, Object> data, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);
        composeFileRequest(post, data, headers);
        return getResponseBody(post);
    }

    /**
     * POST 方式，通过创建 multipar 请求，上传文件
     *
     * @param url       请求地址
     * @param data      表单数据
     * @param fileKey   表单中文件的key
     * @param fileBytes 文件的数据
     * @param fileName  文件名字
     * @param headers   请求头信息
     * @return respond body
     */
    public String postFileBytes(String url, Map<String, String> data, String fileKey, byte[] fileBytes,
        String fileName, Map<String, String> headers) {
        HttpPost post = new HttpPost(url);
        setHeaders(post, headers);
        composeFileBytesRequest(post, data, fileKey, fileBytes, fileName);
        return getResponseBody(post);
    }

    private void composeFileBytesRequest(HttpEntityEnclosingRequestBase request, Map<String, String> data,
        String fileKey, byte[] fileBytes, String fileName) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.setCharset(Consts.UTF_8);

        if (MapUtils.isNotEmpty(data)) {
            for (Map.Entry<String, String> entry : data.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                builder.addPart(key, new StringBody(value, ContentType.create("text/plain", Consts.UTF_8)));
            }
        }

        builder.addBinaryBody(fileKey, fileBytes, ContentType.DEFAULT_BINARY, fileName);

        HttpEntity body = builder.build();
        request.setEntity(body);
        log.debug("Request body: {}", body);
    }

    private void setHeaders(HttpRequestBase request, Map<String, String> headers) {
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 重新解析请求头转换成对应的 multipart/form-data 格式
     *
     * @param request 请求
     * @param data    请求体
     * @param headers 请求头
     */
    private void composeFileRequest(HttpRequestBase request, Map<String, Object> data, Map<String, String> headers) {
        if (MapUtils.isNotEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }

        if (data != null && request instanceof HttpEntityEnclosingRequestBase) {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Consts.UTF_8);
            for (String key : data.keySet()) {
                Object value = data.get(key);
                if (value instanceof String) {
                    String bodyString = (String) value;
                    builder.addPart(key, new StringBody(bodyString, ContentType.create("text/plain", Consts.UTF_8)));
                } else if (value instanceof File) {
                    File file = (File) value;
                    builder.addPart(key, new FileBody(file, ContentType.DEFAULT_BINARY));
                }
            }
            HttpEntity body = builder.build();
            ((HttpEntityEnclosingRequestBase) request).setEntity(body);
            log.debug("Request body: {}", body);
        }
    }

    @Override
    public void close() throws IOException {
        this.client.close();
    }
}
