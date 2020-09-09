package keshe.wanwu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Index_Activity extends Activity {

    private static final String[] m = {"买家", "卖家", };
    private List<String> countries;
    private Spinner spinner;
    private ArrayAdapter<String> adapterarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countries = new ArrayList<String>();
        for (int i = 0; i < m.length; i++) {// 将数组转化成list集合,方便adapter.add()操作
            countries.add(m[i]);
        }
        // =============使用List集合作为数据源
        // ===================================================//
        spinner = (Spinner) findViewById(R.id.spinner1);



        // 将可选内容与ArrayAdapter连接起来
        adapterarray = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countries);
        // 设置下拉列表的风格
        adapterarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        spinner.setAdapter(adapterarray);
        // 添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("切换身份为：" + adapterarray.getItem(position));
                //Toast.makeText(Index_Activity.this, "切换身份为【" + m[position] + "】！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        // 设置默认值
        spinner.setVisibility(View.VISIBLE);


    }


}/*extends Activity {
    private Spinner spinner1;
    private ArrayAdapter<String> adapter1;
    private String[] subjects;
    private ArrayAdapter<String> StringArrayadapter;

    private ArrayList<String> arr=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_layout);

       /* spinner1 = (Spinner) findViewById(R.id.spinner1);
        //初始化数据
        subjects = new String[]{"买家", "卖家"};
        //创建数组适配器
        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, subjects);

        //给下拉列表设置适配器
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Index_Activity.this, "你选择了【" + subjects[position] + "】！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        spinner1 = (Spinner) findViewById(R.id.spinner1);
        //初始化数据
        arr.add("买家");
        arr.add("卖家");
        //创建数组适配器
        final ArrayAdapter<String> StringArrayadapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,arr);

        //给下拉列表设置适配器
        spinner1.setAdapter(StringArrayadapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Index_Activity.this, "你选择了【" + subjects[position] + "】！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }
}
*/
