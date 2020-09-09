package keshe.wanwu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowyuActivity extends AppCompatActivity  {
    private String[] message = {};
    public ListView Listview;
    //获取课程名字
    public String subject_name;
    //中文名字
    public String subject_name1;
    public TextView subject_teacher;
    private Boolean isSucceed=false;
  // 测试注释掉了  GetTeach_name getname=new GetTeach_name();
    //保存老师姓名，用来查询具体的教师信息
    private String name="";
    //保存家长手机号
    public String par_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_showyu);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        subject_name=bundle.getString("subject");
        subject_name1=bundle.getString("subject");
        par_phone=bundle.getString("phone");

        // 测试注释掉了  init();
        //中文转英文
        if (subject_name.equals("数学")) {
            subject_name="math";
        }
        if (subject_name.equals("语文")) {
            subject_name="chinese";
        }
        if (subject_name.equals("英语")) {
            subject_name="english";
        }
        if (subject_name.equals("物理")) {
            subject_name="physical";
        }
        if (subject_name.equals("政治")) {
            subject_name="politics";
        }
        if (subject_name.equals("化学")) {
            subject_name="chemistry";
        }
        if (subject_name.equals("生物")) {
            subject_name="biology";
        }
        if (subject_name.equals("地理")) {
            subject_name="geography";
        }
         // 测试注释掉了   new AnotherTask().execute((Void[]) null);
    }

    /*测试注释掉了
    //组件初始化方法
    public void init(){
        subject_teacher=(TextView)findViewById(R.id.subject_teacher);
        subject_teacher.setText(subject_name+"教师");
        Listview =(ListView)findViewById(R.id.show_teacher);
        this.registerForContextMenu(Listview);
        Listview.setOnItemClickListener(this);
    }

     */


/*
    //获取教师信息
    private class AnotherTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // 对UI组件的更新操作,耗时的操作
            try {
                // 连接到服务器的地址
                //String connectURL = "http://120.79.130.134/teacher_pro/teacher.php";
                String connectURL = "http://192.168.137.1/teacher.php";
                // 填入用户名密码和连接地址
                isSucceed = getname.getname(subject_name, connectURL);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (isSucceed) {
                message=getname.result.split(",");
                //rj result里存着所有该科目的教师姓名，并用，号隔开。
                // 但里面有个不合理的地方，就是默认把教师名字当成主键了。暂时不改，留到后期版本改进
                Listview.setAdapter(new ArrayAdapter<String>(Showtea_Activity.this, android.R.layout.simple_list_item_1,message));
            }else {
                Toast.makeText(Showtea_Activity.this, "抱歉！暂时没有你查找的教师！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        name=message[position];
        //传递教师姓名，用来获取对应教师具体信息
        Intent intent=new Intent(Showtea_Activity.this,ShowTeaDet_Activity.class);
        Bundle bundle=new Bundle();
        bundle.putString("teach_name", name);
        bundle.putString("subject", subject_name1);
        bundle.putString("par_phone", par_phone);
        System.out.println( "showtea-act：par_phone:"+par_phone );
        intent.putExtras(bundle);
        startActivity(intent);
    }


 */
}
