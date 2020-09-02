package com.netposa.template.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * RestTemplate 远程调用工具类
 *
 * @author LP
 */
public class RestTemplateUtils {

    private static RestTemplate restTemplate;

    static {
        restTemplate = new RestTemplate(generateHttpRequestFactory());
    }

    /**
     * 设置忽略SSL
     */
    private static HttpComponentsClientHttpRequestFactory generateHttpRequestFactory() {
        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
        SSLContext sslContext = null;
        try {
            sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        //45秒超时
        factory.setConnectTimeout(45000);
        factory.setReadTimeout(45000);
        return factory;
    }

    // ----------------------------------GET-------------------------------------------------------

    /**
     * GET请求调用方式
     *
     * @param url          请求URL
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> get(String url, Class<T> responseType, String json) {
        return restTemplate.getForEntity(url, responseType, json);
    }

    /**
     * 带请求头的GET请求调用方式
     *
     * @param url          请求URL
     * @param headers      请求头参数
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> get(String url, Map<String, String> headers, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        HttpEntity<?> requestEntity = new HttpEntity<>(httpHeaders);
        return exchange(url, HttpMethod.GET, requestEntity, responseType);
    }

    // ----------------------------------POST-------------------------------------------------------

    /**
     * POST请求调用方式
     *
     * @param url          请求URL
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> post(String url, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, responseType);
    }

    /**
     * POST请求调用方式
     *
     * @param url          请求URL
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> post(String url, Object requestBody, Class<T> responseType) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
    }

    /**
     * 带请求头的POST请求调用方式
     *
     * @param url          请求URL
     * @param headers      请求头参数
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> post(String url, Map<String, String> headers, Object requestBody, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
    }

    public static <T> T post(String url, HttpEntity httpEntity, Class<T> responseType) {
        return restTemplate.postForObject(url, httpEntity, responseType);
    }

    public static <T> ResponseEntity<T> httpPost(String url, HttpEntity httpEntity, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, responseType);
    }

    public static <T> ResponseEntity<T> postFormData(String url, Map<String, String> headers, Object requestBody, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        JSONObject jsonObject = JSONObject.parseObject(requestBody.toString());
        body.add("type", jsonObject.getString("type"));
        if (!StringUtils.isEmpty(jsonObject.getString("imgUrl"))) body.add("imgUrl", jsonObject.getString("imgUrl"));
        if (!StringUtils.isEmpty(jsonObject.getString("imgBase64")))
            body.add("imgBase64", jsonObject.getString("imgBase64"));
        if (!StringUtils.isEmpty(jsonObject.getString("location")))
            body.add("location", jsonObject.getString("location"));
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
    }

    // ----------------------------------PUT-------------------------------------------------------

    /**
     * PUT请求调用方式
     *
     * @param url          请求URL
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> put(String url, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.PUT, HttpEntity.EMPTY, responseType);
    }

    /**
     * PUT请求调用方式
     *
     * @param url          请求URL
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> put(String url, Object requestBody, Class<T> responseType) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType);
    }

    /**
     * 带请求头的PUT请求调用方式
     *
     * @param url          请求URL
     * @param headers      请求头参数
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> put(String url, Map<String, String> headers, Object requestBody, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType);
    }

    // ----------------------------------DELETE-------------------------------------------------------

    /**
     * DELETE请求调用方式
     *
     * @param url          请求URL
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        return restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, responseType);
    }

    /**
     * DELETE请求调用方式
     *
     * @param url          请求URL
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> delete(String url, Object requestBody, Class<T> responseType) {
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType);
    }

    /**
     * 带请求头的DELETE请求调用方式
     *
     * @param url          请求URL
     * @param headers      请求头参数
     * @param requestBody  请求参数体
     * @param responseType 返回对象类型
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> delete(String url, Map<String, String> headers, Object requestBody, Class<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(requestBody, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, responseType);
    }

    // ----------------------------------通用方法-------------------------------------------------------

    /**
     * 通用调用方式
     *
     * @param url           请求URL
     * @param method        请求方法类型
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType  返回对象类型
     * @param uriVariables  URL中的变量，按顺序依次对应
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
    }

    /**
     * 通用调用方式
     *
     * @param url           请求URL
     * @param method        请求方法类型
     * @param requestEntity 请求头和请求体封装对象
     * @param responseType  返回对象类型
     * @param uriVariables  URL中的变量，与Map中的key对应
     * @return ResponseEntity 响应对象封装类
     */
    public static <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.exchange(url, method, requestEntity, responseType, uriVariables);
    }

}

