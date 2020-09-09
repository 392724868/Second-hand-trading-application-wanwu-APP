package keshe.wanwu;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HttpLogin {
    public String result = ""; // 用来取得返回的String
    public boolean isLoginSucceed = false;

    //登入的方法
    public boolean gotoLogin(String phonenum, String password, String connectUrl) {

        HttpClient httpClient = new DefaultHttpClient();
        // 发送post请求
        HttpPost httpRequest = new HttpPost(connectUrl);
        // Post运作传送变数必须用NameValuePair[]阵列储存
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //BasicNameValuePair存储键值对的类
        params.add(new BasicNameValuePair("phone", phonenum));
        params.add(new BasicNameValuePair("paswd", password));
        try {
            // 发出HTTP请求转为带参数的HTTP网络地址
            httpRequest.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
            // 取得HTTP response
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
            System.out.println(result+"1");
            // 判断返回的数据是否为php中成功登入时输出的success
            if (result.contains("1")) {
                isLoginSucceed = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isLoginSucceed;
    }

}