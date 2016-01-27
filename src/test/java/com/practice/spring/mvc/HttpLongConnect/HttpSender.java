package com.practice.spring.mvc.HttpLongConnect;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HTTP请求发送包装类
 * 
 * @author Chenzy
 * 
 */
public class HttpSender {
    private static Log logger = LogFactory.getLog(HttpSender.class);
    
    private static HttpSender instance;
    
    private HttpSender() {
    	logger.info("HttpSender()");
        RequestConfig config = RequestConfig.custom().setConnectTimeout(100000).setSocketTimeout(100000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    private static class HttpSenderHolder {
        private static HttpSender instance = new HttpSender();
    }

    public static synchronized  HttpSender getInstance() {
    	
//        return HttpSenderHolder.instance;
    	if(instance == null){
    		instance = HttpSenderHolder.instance;
    	}
    	
    	return instance;
    }
    
    public void setProxyIp(String proxyIp) {
        this.proxyIp = proxyIp;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    private String proxyIp;
    private Integer proxyPort;

    private final CloseableHttpClient httpClient;
    public final String CHARSET = "UTF-8";

    public String doGet(String url, Map<String, String> params) {
        return doGet(url, params, CHARSET);
    }
    
    public CloseableHttpResponse keepAlive(String url, Map<String, String> params) {
    	return keepAlive(url, params, CHARSET);
    }

    public String doPost(String url, Map<String, String> params) {
        return doPost(url, params, CHARSET);
    }

    /**
     * HTTP Get 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param params
     *            请求的参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    public String doGet(String url, Map<String, String> params, String charset) {
        logger.debug("doGet");
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            logger.debug("req url=" + url);
            HttpGet httpGet = new HttpGet(url);
            // 设置HTTP代理
            if (!StringUtils.isBlank(this.proxyIp)) {
                HttpHost proxy = new HttpHost(this.proxyIp, this.proxyPort, "http");
                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                httpGet.setConfig(config);
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                ContentType contentType = ContentType.getOrDefault(entity);
                if (contentType.getCharset() != null) {
                    charset = contentType.getCharset().toString();
                    logger.debug("response charset=" + contentType.getCharset().toString());
                }
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            logger.debug("response=" + result);
            return result;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * HTTP Post 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param params
     *            请求的参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    public String doPost(String url, Map<String, String> params, String charset) {
        logger.info("doPost");
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (params != null && !params.isEmpty()) {
                pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            // 设置HTTP代理
            if (!StringUtils.isBlank(this.proxyIp)) {
                HttpHost proxy = new HttpHost(this.proxyIp, this.proxyPort, "http");
                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                httpPost.setConfig(config);
            }
            if (pairs != null && pairs.size() > 0) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                ContentType contentType = ContentType.getOrDefault(entity);
                if (contentType.getCharset() != null) {
                    charset = contentType.getCharset().toString();
                    logger.debug("response charset=" + contentType.getCharset().toString());
                }
                result = EntityUtils.toString(entity, charset);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * HTTP Post 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param buffer
     *            请求的二进制buffer
     * @return 页面内容
     */
    public byte[] postBuff(String url, byte[] buffer) {
        logger.info("doPost");
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            // 设置HTTP代理
            if (!StringUtils.isBlank(this.proxyIp)) {
                HttpHost proxy = new HttpHost(this.proxyIp, this.proxyPort, "http");
                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                httpPost.setConfig(config);
            }
            if (buffer != null && buffer.length > 0) {
                EntityBuilder paramEntity = EntityBuilder.create().setBinary(buffer);
                httpPost.setEntity(paramEntity.build());
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            if (entity != null) {
                byte[] respByte = new byte[512];
                int len = 0;
                while ((len = entity.getContent().read(respByte, 0, 512)) > 0) {
                    out.write(respByte, 0, len);
                }
            }
            EntityUtils.consume(entity);
            response.close();
            return out.toByteArray();
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
    
    /**
     * HTTP Get 获取内容
     * 
     * @param url
     *            请求的url地址 ?之前的地址
     * @param params
     *            请求的参数
     * @param charset
     *            编码格式
     * @return 页面内容
     */
    public CloseableHttpResponse keepAlive(String url, Map<String, String> params, String charset) {
        logger.debug("doGet");
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    String value = entry.getValue();
                    if (value != null) {
                        pairs.add(new BasicNameValuePair(entry.getKey(), value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            logger.debug("req url=" + url);
            HttpGet httpGet = new HttpGet(url);
            // 设置HTTP代理
            if (!StringUtils.isBlank(this.proxyIp)) {
                HttpHost proxy = new HttpHost(this.proxyIp, this.proxyPort, "http");
                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
                httpGet.setConfig(config);
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            return response;
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }
}
