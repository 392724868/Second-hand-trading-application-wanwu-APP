package keshe.wanwu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegeActivity extends AppCompatActivity implements View.OnClickListener {

    //获取組件
    public EditText phone_edit,paswd_edit,code_edit;
    public Button getCode,rege_btn;
    public Boolean flag=false;
    //发短信定义变量
    String APPKEY = "12522d38d043e";
    String APPSECRETE = "749d699b8544cfbb0af14bfd3fa3ffc9";
    int i = 30;
    private boolean smsflag = true;
    private boolean registerflag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_rege);
        init();
    }
    //組件初始化方法
    public void init(){
        phone_edit=(EditText)findViewById(R.id.register_phonenumber);
        paswd_edit=(EditText)findViewById(R.id.register_paswd);
        //code_edit=(EditText)findViewById(R.id.validatecode);
        //getCode=(Button)findViewById(R.id.getcode);
        rege_btn=(Button)findViewById(R.id.register);
        //为按钮添加监听时间
        //getCode.setOnClickListener(this);
        rege_btn.setOnClickListener(this);
        // 启动短信验证sdk
   /*     SMSSDK.initSDK(this, APPKEY, APPSECRETE);
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
        */
    }
    @Override
    public void onClick(View v) {
        //获取验证码
        String phonenumber=phone_edit.getText().toString();
        switch (v.getId()) {
            /*case R.id.getcode:
                Toast.makeText(RegeActivity.this, "1", Toast.LENGTH_SHORT).show();
                // 1. 通过规则判断手机号
                if (!judgePhoneNums(phonenumber)) {
                    return;
                }
                // 2. 通过sdk发送短信验证
                //    SMSSDK.getVerificationCode("86", phonenumber);
                // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                getCode.setClickable(false);
                getCode.setText("重新发送(" + i + ")");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (; i > 0; i--) {
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.sendEmptyMessage(-8);
                    }
                }).start();
                break;
                获取验证码部分先注释掉*/

            //用户注册
            case R.id.register:
                //用户注册
                //这里调用向后台发送请求的方法,判断是否正确输入用户名和密码
                Thread thread=new Thread(runnable);
                thread.start();
                break;

            default:
                break;
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                getCode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                getCode.setText("获取验证码");
                getCode.setClickable(true);
                i = 30;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
            /*    if (result == SMSSDK.RESULT_COMPLETE) {
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                        smsflag = true;
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码", Toast.LENGTH_SHORT).show();
                    } else {
                        ((Throwable) data).printStackTrace();
                    }
                }
                */
            }
        }
    };

    //判断手机号码是否合理

    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11) && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！", Toast.LENGTH_SHORT).show();
        return false;
    }

    //判断一个字符串的位数
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    //验证手机格式
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或4或8，其他位置的可以为0-9
         */
        String telRegex = "[1][3458]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }
    @Override
    protected void onDestroy() {
        //    SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }

    //启动一个新的线程用来登录进行耗时操作
    Runnable runnable=new Runnable() {

        @Override
        public void run() {
            HttpLogin httpLogin=new HttpLogin();
            String phone=phone_edit.getText().toString();
            String paswd=paswd_edit.getText().toString();
            // 连接到服务器的地址
            //String connectURL = "http://120.79.130.134/teacher_pro/par_rege.php";

            String connectURL = "http://192.168.44.1/par_rege.php";
            flag=httpLogin.gotoLogin(phone, paswd, connectURL);
            if (flag) {
                Intent intent=new Intent(RegeActivity.this,SucesActivity.class);
                //传入手机号用来在me_layout界面显示
                //成功后启动Activity
                intent.putExtra("phone", phone);
                startActivity(intent);
            }else {
                Looper.prepare();
                Toast.makeText(RegeActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

        }
    };
}


