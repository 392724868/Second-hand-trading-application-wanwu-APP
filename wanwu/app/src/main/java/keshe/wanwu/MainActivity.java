package keshe.wanwu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //获取布局文件当中的各个组件
    public Button login_btn;
    public EditText phone_edit,paswd_edit;
    public CheckBox reme_box;
    public TextView forget,regesiter;
    public Boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init();
    }
    //组件初始化
    private void init(){
        phone_edit=(EditText)findViewById(R.id.login_phonenumber);
        paswd_edit=(EditText)findViewById(R.id.login_paswd);
        reme_box=(CheckBox)findViewById(R.id.reme_paswd);
        forget=(TextView)findViewById(R.id.forget_paswd);
        forget.setOnClickListener(this);
        regesiter=(TextView)findViewById(R.id.regester_user);
        regesiter.setOnClickListener(this);
        login_btn=(Button)findViewById(R.id.login);
        login_btn.setOnClickListener(this);
        //检查上次是否保存数据
        SharedPreferences preferences=getSharedPreferences("user_mes", Activity.MODE_PRIVATE);
        if(preferences!=null){
            phone_edit.setText(preferences.getString("phone", ""));
            paswd_edit.setText(preferences.getString("paswd", ""));
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //忘记密码弹出修改密码界面
            case R.id.forget_paswd:

                break;
            //注册新用户
            case R.id.regester_user:
                Intent intent=new Intent(MainActivity.this,RegeActivity.class);
                startActivity(intent);
                break;
            //登录
            case R.id.login:
                if (reme_box.isChecked()) {
                    //保存数据
                    SharedPreferences preferences=getSharedPreferences("user_mes",Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("phone", phone_edit.getText().toString().equals("")?"":phone_edit.getText().toString());
                    editor.putString("paswd", paswd_edit.getText().toString().equals("")?"":paswd_edit.getText().toString());
                    //将数据提交
                    editor.commit();
                }
                //这里调用向后台发送请求的方法,判断是否正确输入用户名和密码
                Thread thread=new Thread(runnable);
                thread.start();
            default:
                break;
        }

    }

    //启动一个新的线程用来登录进行耗时操作
    Runnable runnable=new Runnable() {

        @Override
        public void run() {
            HttpLogin httpLogin=new HttpLogin();
            String phone=phone_edit.getText().toString();
            String paswd=paswd_edit.getText().toString();
            // 连接到服务器的地址
            String connectURL =
                    //"http://119.23.35.155/teacher_pro/par_login.php";
                    "http://192.168.44.1/par_login.php";
            flag=httpLogin.gotoLogin(phone, paswd, connectURL);
            //flag=true;
            System.out.println("flag="+flag);
            if (flag) {
                Intent intent2=new Intent(MainActivity.this,SucesActivity.class);

                //传入手机号用来在me_layout界面显示
                //成功后启动Activity
                Bundle bundle=new Bundle();
                bundle.putString("phone", phone);
                intent2.putExtras(bundle);
                startActivity(intent2);
            }else {
                Looper.prepare();
                Toast.makeText(MainActivity.this, "登陆失败，请重新登录", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }

        }
    };

}

