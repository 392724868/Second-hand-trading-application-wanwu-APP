package keshe.wanwu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class GetGoodsname {
    //登入的方法
    public String result = ""; // 用来取得返回的String
    public boolean getname(String goods_name, String connectUrl) {
        boolean isLoginSucceed = false;
        HttpClient httpClient = new DefaultHttpClient();
        // 发送post请求
        HttpPost httpRequest = new HttpPost(connectUrl);
        // Post运作传送变数必须用NameValuePair[]阵列储存
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("goods_name", goods_name));
        try {
            // 发出HTTP请求
            httpRequest.setEntity(new UrlEncodedFormEntity(params));
            // 取得HTTP response
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
            System.out.println(result+"1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 判断返回的数据是否为php中成功登入是输出的
        if (!result.equals("")) {
            isLoginSucceed = true;
        }
        return isLoginSucceed;
    }
}
