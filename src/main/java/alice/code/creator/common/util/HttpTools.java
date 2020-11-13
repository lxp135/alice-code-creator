package alice.code.creator.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTTP请求工具
 * @author contact@liuxp.me
 */
public class HttpTools {

    /**
     * 发送请求
     * @param request HTTP请求
     * @return 返回结果
     * @throws Exception 请求异常
     */
    public static <T extends HttpRequestBase> Map<String, Object> send(T request) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 设置超时时间
        request.setConfig(getRequestConfig());
        // 执行请求
        HttpResponse response = httpClient.execute(request);

        String resultString = EntityUtils.toString(response.getEntity(), "utf-8");

        return JSON.parseObject(resultString, new TypeReference<Map<String, Object>>(){});

    }

    /**
     * 根据URL生成一个HTTP请求
     * @param url 请求的地址
     * @return HttpPost
     */
    public static HttpGet getHttpGet(String url){
        HttpGet httpGet = new HttpGet(url);

        // 设置请求的header
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpGet;
    }

    /**
     * 根据URL生成一个HTTP请求
     * @param url 请求的地址
     * @return HttpPost
     */
    public static HttpPost getHttpPost(String url){
        HttpPost httpPost = new HttpPost(url);

        // 设置请求的header
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        return httpPost;
    }

    /**
     * 取得RequestConfig配置
     * @return RequestConfig
     */
    private static RequestConfig getRequestConfig(){
        return RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(5000)
                .build();
    }

    /**
     * 绕过验证
     * @return SSLContext
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("SSLv3");

        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString){
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                    String paramString){
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sc.init(null, new TrustManager[] { trustManager }, null);
        return sc;
    }

    /**
     * 模拟请求
     * @param url 资源地址
     * @param map 参数列表
     * @param encoding 编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public static String send(String url, Map<String, String> map, String encoding) throws KeyManagementException, NoSuchAlgorithmException, ClientProtocolException, IOException {
        String body = "";
        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
        CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).build();

        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        // 释放链接
        response.close();
        return body;
    }
}
