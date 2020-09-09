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

public class Showfenlei_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String[] message = {};
    public ListView Listview;
    //获取课程名字
    public String goods_name;
    //中文名字
    public String goods_name1;
    public TextView kind_goods;
    private Boolean isSucceed=false;
    GetGoodsname getname=new GetGoodsname();
    //保存商品，用来查询具体的商品信息
    private String name="";

    //保存家长手机号
   // public String par_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_showfenlei);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        goods_name=bundle.getString("goods");
        goods_name1=bundle.getString("goods");
       // par_phone=bundle.getString("phone");

        init();
        //中文转英文
        if (goods_name.equals("书籍")) {
            goods_name="shuji";
        }
        if (goods_name.equals("生活")) {
            goods_name="shenghuo";
        }
        if (goods_name.equals("电子")) {
            goods_name="dianzi";
        }
        if (goods_name.equals("手工")) {
            goods_name="shougong";
        }
        if (goods_name.equals("代购")) {
            goods_name="daigou";
        }
        if (goods_name.equals("兼职")) {
            goods_name="jianzhi";
        }
        if (goods_name.equals("烧烤")) {
            goods_name="shaokao";
        }
        if (goods_name.equals("房屋")) {
            goods_name="fangwu";
        }


    }
    //组件初始化方法
    public void init(){
        kind_goods=(TextView)findViewById(R.id.kind_goods);
        kind_goods.setText(goods_name+"产品");
        Listview =(ListView)findViewById(R.id.show_goods);

        this.registerForContextMenu(Listview);
        Listview.setOnItemClickListener(this);
    }

/*

    //获取教师信息
    private class AnotherTask extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            // 对UI组件的更新操作,耗时的操作
            try {
                // 连接到服务器的地址
                //String connectURL = "http://120.79.130.134/teacher_pro/teacher.php";
                String connectURL = "http://192.168.44.1/teacher.php";
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


 */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        name=message[position];
        //传递教师姓名，用来获取对应教师具体信息
        Intent intent=new Intent(Showfenlei_Activity.this,Showfenlei_det_Activity.class);
        Bundle bundle=new Bundle();
        bundle.putString("goods_name", name);
        bundle.putString("goods", goods_name1);
       // bundle.putString("par_phone", par_phone);
       // System.out.println( "showtea-act：par_phone:"+par_phone );
        intent.putExtras(bundle);
        startActivity(intent);
    }

}

