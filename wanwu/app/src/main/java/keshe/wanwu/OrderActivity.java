package keshe.wanwu;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderActivity extends AppCompatActivity{
    private String phonenum="";
    public Boolean order_flag=false;
    String content[]={};
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.order_layout);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");
        list=new ArrayList<Map<String,Object>>();
        Thread thread=new Thread(runnable);
        thread.start();
        SimpleAdapter adapter=new SimpleAdapter(OrderActivity.this,
                list,R.layout.order1_layout,new String[]{"bookname","booknum","bookprice","ztai"},
                new int[]{R.id.bookname,R.id.booknum,R.id.bookprice,R.id.ztai});
        ListView listView=(ListView)findViewById(R.id.list_order);
        listView.setAdapter(adapter);
    }


    //开启新的线程用来进行后台订单数据获取
    Runnable runnable=new Runnable() {

        @Override
        public void run() {
            HttpMe httpMe=new HttpMe();
            // 连接到服务器的地址
            String connectURL = "http://192.168.44.1/order.php";


            order_flag=httpMe.getOrder(phonenum, connectURL);
            if (order_flag) {
                //取得返回的内容
                content=httpMe.result.split(",");
                //添加列表内容

               // System.out.println(phonenum);

                Map<String, Object> map=new HashMap<String, Object>();
               // System.out.println(content.length);

                for(int i=0;i<content.length/4;i++){
                    map=new HashMap<String, Object>();
                    System.out.println(content[4*i+0]);
                    map.put("bookname", content[4*i+0]);
                    map.put("booknum", content[4*i+1]);
                    map.put("bookprice", content[4*i+2]+"元");
                    map.put("ztai", content[4*i+3]);
                    list.add(map);
                }
            }
        }
    };
}

