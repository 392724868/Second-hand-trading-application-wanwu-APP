package keshe.wanwu;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MoneyActivity extends Activity {
    private String phonenum="";
    private Boolean isSucceed=false;
    HttpMe  httpMe=new HttpMe();
    // 用来存储钱包信息
    private String[] message = {};
    //获取布局文件的组件
    private TextView zong,yue,jifen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.money_layout);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");
        init();
        new AnotherTask().execute((Void[]) null);

    }
    //组件初始化
    private void init(){
        zong=(TextView)findViewById(R.id.zong);
        yue=(TextView)findViewById(R.id.yue);
        jifen=(TextView)findViewById(R.id.jifen);
    }

    // 获取钱包信息
    private class AnotherTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // 对UI组件的更新操作,耗时的操作
            try {
                // 连接到服务器的地址
                String connectURL = "http://192.168.44.1/money.php";
                // 填入用户名密码和连接地址
                isSucceed = httpMe.getOrder(phonenum, connectURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            if (isSucceed) {
                message=httpMe.result.split(",");
                zong.setText("我的总额："+message[0]);
                yue.setText("我的余额："+message[1]);
                jifen.setText("我的积分："+message[2]);
            }
        }
    }

}

