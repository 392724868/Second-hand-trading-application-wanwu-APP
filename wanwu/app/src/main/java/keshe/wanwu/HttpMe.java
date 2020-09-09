package keshe.wanwu ;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpMe {
    //获取返回的信息
    public String result = ""; // 用来取得返回的String
    public boolean getOrder(String phonenum, String connectUrl) {
        boolean isLoginSucceed = false;
        HttpClient httpClient = new DefaultHttpClient();
        // 发送post请求
        HttpPost httpRequest = new HttpPost(connectUrl);
        // Post运作传送变数必须用NameValuePair[]阵列储存
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("phone", phonenum));
        try {
            // 发出HTTP请求
            httpRequest.setEntity(new UrlEncodedFormEntity(params));
            // 取得HTTP response
            HttpResponse httpResponse = httpClient.execute(httpRequest);
            result = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
            System.out.println(result);
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
