package keshe.wanwu;

import android.app.ListActivity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Window;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardActivity extends ListActivity {
    private String phonenum="";
    public Boolean order_flag=false;
    String content[]={};
    List<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");
        SimpleAdapter adapter4=new SimpleAdapter(RewardActivity.this,
                this.getData(),R.layout.reward_layout,new String[]{"jine","endtime"},
                new int[]{R.id.jine,R.id.endtime});
        setListAdapter(adapter4);
    }

    private List<? extends Map<String, Object>> getData() {
        System.out.println(content.length);
        list=new ArrayList<Map<String,Object>>();
        Thread  thread=new Thread(runnable);
        thread.start();
        return list;
    }

    //开启新的线程用来进行后台订单数据获取
    Runnable runnable=new Runnable() {

        @Override
        public void run() {
            HttpMe httpMe=new HttpMe();
            // 连接到服务器的地址
            String connectURL = "http://192.168.44.1/reward.php";
            order_flag=httpMe.getOrder(phonenum, connectURL);
            if (order_flag) {
                //取得返回的内容
                content=httpMe.result.split(",");
                //添加列表内容
                Map<String, Object> map=new HashMap<String, Object>();
                System.out.println(content.length+"1");
                for(int i=0;i<content.length/2;i++){
                    map=new HashMap<String, Object>();
                    map.put("jine", content[2*i+0]);
                    map.put("endtime", content[2*i+1]);
                    list.add(map);
                }
            }
        }
    };
}
