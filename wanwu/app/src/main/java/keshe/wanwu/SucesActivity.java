package keshe.wanwu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;




public class SucesActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnTouchListener, AdapterView.OnItemSelectedListener {

    //下拉选项框 初始化
    private static final String[] m = {"买家","卖家"};
    private List<String> countries;
    private Spinner spinner;
    private ArrayAdapter<String> adapterarray; //rj 保存下拉选项框






    private androidx.viewpager.widget.ViewPager ViewPager;
    public ImageView home1,yutang1,car1,me1;
    //index里的组件
    public TextView local_text;
    public ImageView math,chinese,english,physical,politics,chemistry,biology,geography,fabuI;
    private ViewFlipper vFlipper;

    //鱼塘里的组件
    public ImageView shuji,shenghuo,youhui1,youhui2;

    //发布组件
    public Button buttonfabu;
    //消息里的组件
    public TextView order_teach,order_sug,order_talk,order_cheap,shopping_car;
    PopupWindow cheap_window;
    //me里的组件初始化
    public LinearLayout use_mes;
    public TextView phone,plant,set;
    public ImageView icon_img,order_img,money_img,reward_img;
    public String phonenum="";
    /*
     * 用来设置左右来回滑动
     */

    private List<View> lists = new ArrayList<View>();
    private MyAdapter myAdapter;

    /*
    // 下边4个按钮，用来改变背景颜色
    private LinearLayout layout1, layout2, layout3, layout4;

     */


    //图片轮播组件
    public int imageIds[];
    public String[] titles;
    public ArrayList<ImageView> images;
    public ArrayList<View> dots;
    public TextView title;
    public androidx.viewpager.widget.ViewPager mViewPager;
    public ViewPagerAdapter adapter;
    public int oldPosition = 0;//记录上一次点的位置
    public int currentItem; //当前页面
    private ScheduledExecutorService scheduledExecutorService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_suces);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        phonenum=bundle.getString("phone");
        init();
/*
        // 将可选内容与ArrayAdapter连接起来
        adapterarray = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, countries);
        // 设置下拉列表的风格
        adapterarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        spinner.setAdapter(adapterarray);

 */
        //下拉窗口

       // String[] start = { "买家", "卖家" };

     //   Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
     //   spinner1.setAdapter(new MyAdapter1(this,
        //        android.R.layout.simple_list_item_1, android.R.id.text1, start));


        //多级菜单




    }
    /*下拉窗口
    private class MyAdapter1 extends ArrayAdapter {

        private LayoutInflater infalter;
        private String[] start;
        private int resource;
        private int textViewResourceId;

        public MyAdapter1(Context context, int resource,
                          int textViewResourceId, String[] objects) {
            super(context, resource, textViewResourceId, objects);

            this.start = objects;
            this.resource = resource;
            this.textViewResourceId = textViewResourceId;

            infalter = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = infalter.inflate(resource, null);
            TextView text = (TextView) convertView
                    .findViewById(textViewResourceId);
            text.setText("pos:"+position);
            return convertView;
        }

        @Override
        public int getCount() {
            return start.length;
        }
    }

     */





    //组件初始化
    @SuppressWarnings("deprecation")
    public void init(){
        home1=(ImageView) findViewById(R.id.home1);
        yutang1=(ImageView) findViewById(R.id.yutang1);
        fabuI=(ImageView)findViewById(R.id.fabu);


        car1=(ImageView)findViewById(R.id.car1);

        me1=(ImageView)findViewById(R.id.me1);




      /*  // 加载4个layout,用来设置背景
        layout1=(LinearLayout)findViewById(R.id.layout1);
        layout2=(LinearLayout)findViewById(R.id.layout2);
        layout3=(LinearLayout)findViewById(R.id.layout3);
        layout4=(LinearLayout)findViewById(R.id.layout4);

       */
        // 加载对应的布局文件
        View indexV=getLayoutInflater().inflate(R.layout.index_layout, null);
        View yutangV=getLayoutInflater().inflate(R.layout.yutang_layout, null);
        View fabuV=getLayoutInflater().inflate(R.layout.fabu_layout, null);
        View carV=getLayoutInflater().inflate(R.layout.mes, null);
        View meV=getLayoutInflater().inflate(R.layout.me_layout, null);

        //View me=getLayoutInflater().inflate(R.layout.me, null);




        lists.add(indexV);
        lists.add(yutangV);
        lists.add(fabuV);
        lists.add(carV);
        lists.add(meV);
/*
        lists.add(mes);
        lists.add(me);

 */
        myAdapter=new MyAdapter(lists);
        ViewPager=(ViewPager)findViewById(R.id.viewPager);
        ViewPager.setAdapter(myAdapter);


        //设置底部按钮监听事件
        home1.setOnClickListener(this);
        yutang1.setOnClickListener(this);
        fabuI.setOnClickListener(this);
        car1.setOnClickListener(this);
        me1.setOnClickListener(this);
        /*
        home1.setOnTouchListener(this);
        yutang1.setOnTouchListener(this);
        fabuI.setOnTouchListener(this);
        search_image.setOnTouchListener(this);
        mes_image.setOnClickListener(this);
        mes_image.setOnTouchListener(this);
        me_image.setOnClickListener(this);
        me_image.setOnTouchListener(this);

         */

        //index的 组件

       // LayoutInflater inflater=LayoutInflater.from(SucesActivity.this);
       //  View Susesindex=inflater.inflate(R.layout.index_layout,null);
        spinner = (Spinner)indexV.findViewById(R.id.spinner1);
        //下拉列表框
        countries = new ArrayList<String>();
        for (int i = 0; i < m.length; i++) {// 将数组转化成list集合,方便adapter.add()操作
            countries.add(m[i]);
        }
        adapterarray=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
        //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
        // 设置下拉列表的风格
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将adapter 添加到spinner中
        //spinner.setAdapter(adapter);
        spinner.setAdapter(adapterarray);
        // 添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("切换身份为：" + adapterarray.getItem(position));
                Toast.makeText(SucesActivity.this, "当前身份为【" + m[position] + "】！", Toast.LENGTH_SHORT).show();

                if (adapterarray.getItem(position)=="卖家") {
                    Intent intent001 = new Intent(SucesActivity.this, Suces1Activity.class);
                    startActivity(intent001);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

/*

        // 添加事件Spinner事件监听
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                System.out.println("切换身份为：" + adapterarray.getItem(position));
                Toast.makeText(SucesActivity.this, "当前身份为【" + m[position] + "】！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        }); */
        // 设置默认值
      //  spinner.setVisibility(View.VISIBLE);


        //课程组件

        //消息滚动通知
        vFlipper=(ViewFlipper)indexV.findViewById(R.id.marquee_view);
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao, null));
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao1, null));
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao2, null));
        vFlipper.addView(View.inflate(getApplicationContext(), R.layout.index_toutiao3, null));
        //热门名师
        //图片ID
        imageIds = new int[]{
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
        };
        //图片标题
        titles = new String[]{
                "潮货汇集",
                "女装主会场",
                "狂欢节",
                "美食诱惑",
                "特卖专场"
        };
        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i =0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的点
        //rj dot_0等id在famous_teacher.xml里面。找代码类或布局的控件的定义处,使用右键：usage
        dots = new ArrayList<View>();
        dots.add(findViewById(R.id.dot_0));
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));
        dots.add(findViewById(R.id.dot_3));
        dots.add(findViewById(R.id.dot_4));

        title = (TextView)indexV.findViewById(R.id.image_title);
        title.setText(titles[0]);
        mViewPager = (ViewPager)indexV.findViewById(R.id.vp);
        adapter = new ViewPagerAdapter();
        mViewPager.setAdapter(adapter);
        //mViewPager.setOnPageChangeListener(new android.support.v4.view.ViewPager.OnPageChangeListener() {
        //rj 改
        mViewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                title.setText(titles[position]);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }


        });


        //发布组件
        buttonfabu=(Button) fabuV.findViewById(R.id.buttonfabu);
        buttonfabu.setOnClickListener(this);


        //鱼塘的 组件
        shuji=(ImageView)yutangV.findViewById(R.id.shuji);
        shuji.setOnClickListener(this);
        shenghuo=(ImageView)yutangV.findViewById(R.id.shenghuo);
        shenghuo.setOnClickListener(this);
        youhui1=(ImageView)yutangV.findViewById(R.id.youhui1);
        youhui1.setOnClickListener(this);
        youhui2=(ImageView)yutangV.findViewById(R.id.youhui2);
        youhui2.setOnClickListener(this);



        //message的 组件
        shopping_car=(TextView)carV.findViewById(R.id.shopping_car);
        order_sug=(TextView)carV.findViewById(R.id.order_suggest);
       // order_talk=(TextView)mes.findViewById(R.id.order_talk);
       // order_cheap=(TextView)mes.findViewById(R.id.order_cheaper);
        shopping_car.setOnClickListener(this);
        order_sug.setOnClickListener(this);
      //  order_talk.setOnClickListener(this);
       // order_cheap.setOnClickListener(this);

        /*
        //me的 组件
        use_mes=(LinearLayout)me.findViewById(R.id.user_message);
        use_mes.setOnClickListener(this);
        phone=(TextView)me.findViewById(R.id.phone_numbers);
        phone.setText("手机号："+phonenum);

         */


        order_img=(ImageView)meV.findViewById(R.id.order1);
        order_img.setOnClickListener(this);
        money_img=(ImageView)meV.findViewById(R.id.money1);
        money_img.setOnClickListener(this);
        reward_img=(ImageView)meV.findViewById(R.id.reward1);
        reward_img.setOnClickListener(this);
        plant=(TextView)meV.findViewById(R.id.plant);
        plant.setOnClickListener(this);
        set=(TextView)meV.findViewById(R.id.sets);
        set.setOnClickListener(this);



    }





    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            //首页按钮
            case R.id.home1:
                ViewPager.setCurrentItem(0);
                System.out.println("按下了首页按钮");
                break;




            /*
             * 首页组件监听事件
             * 跳转到各科教师显示界面
             */

            /*


            case R.id.math:
                Intent intent11=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle11=new Bundle();
                bundle11.putString("subject", "数学");
                bundle11.putString("phone",phonenum );
                intent11.putExtras(bundle11);
                startActivity(intent11);
                break;
            case R.id.chinese:
                Intent intent2=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle2=new Bundle();
                bundle2.putString("subject", "语文");
                bundle2.putString("phone",phonenum );
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.english:
                Intent intent3=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle3=new Bundle();
                bundle3.putString("subject", "英语");
                bundle3.putString("phone",phonenum );
                intent3.putExtras(bundle3);
                startActivity(intent3);
                break;
            case R.id.physical:
                Intent intent4=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle4=new Bundle();
                bundle4.putString("subject", "物理");
                bundle4.putString("phone",phonenum );
                intent4.putExtras(bundle4);
                startActivity(intent4);
                break;
            case R.id.politics:
                Intent intent5=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle5=new Bundle();
                bundle5.putString("subject", "政治");
                bundle5.putString("phone",phonenum );
                intent5.putExtras(bundle5);
                startActivity(intent5);
                break;
            case R.id.chemistry:
                Intent intent6=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle6=new Bundle();
                bundle6.putString("subject", "化学");
                bundle6.putString("phone",phonenum );
                intent6.putExtras(bundle6);
                startActivity(intent6);
                break;
            case R.id.biology:
                Intent intent7=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle7=new Bundle();
                bundle7.putString("subject", "生物");
                bundle7.putString("phone",phonenum );
                intent7.putExtras(bundle7);
                startActivity(intent7);
                break;
            case R.id.geography:
                Intent intent8=new Intent(SucesActivity.this, Showtea_Activity.class);
                Bundle bundle8=new Bundle();
                bundle8.putString("subject", "地理");
                bundle8.putString("phone",phonenum );
                intent8.putExtras(bundle8);
                startActivity(intent8);
                break;

             */
            //查找教师
            case R.id.yutang1:
                ViewPager.setCurrentItem(1);
                System.out.println("按下了鱼塘按钮");
             /*   Intent yu=new Intent(SucesActivity.this,Yutang_Activity.class);
                Bundle yu1=new Bundle();
                yu1.putString("phone", phonenum);
                yu.putExtras(yu1);
                startActivity(yu);

              */
                break;

                //下拉测试


                //鱼塘内的组件
            case R.id.shuji:
                Intent intent001=new Intent(SucesActivity.this, Showfenlei_Activity.class);
                Bundle bundle001=new Bundle();
                bundle001.putString("goods", "书籍");

                intent001.putExtras(bundle001);
                startActivity(intent001);
                break;

            case R.id.shenghuo:
                Intent intent002=new Intent(SucesActivity.this, Showfenlei_Activity.class);
                Bundle bundle002=new Bundle();
                bundle002.putString("goods", "生活");

                intent002.putExtras(bundle002);
                startActivity(intent002);
                break;

            case R.id.youhui1:
                Intent intent003=new Intent(SucesActivity.this, Showyouhui_Activity.class);
                Bundle bundle003=new Bundle();
                bundle003.putString("yhgoods", "超级优惠1");

                intent003.putExtras(bundle003);
                startActivity(intent003);
                break;

            case R.id.youhui2:
                Intent intent004=new Intent(SucesActivity.this, Showyouhui_Activity.class);
                Bundle bundle004=new Bundle();
                bundle004.putString("yhgoods", "超级优惠2");

                intent004.putExtras(bundle004);
                startActivity(intent004);
                break;




            //发布
            case R.id.fabu:
                ViewPager.setCurrentItem(2);
             /*   Intent fa=new Intent(SucesActivity.this,Fabu_Activity.class);
                Bundle fa1=new Bundle();
                fa1.putString("phone", phonenum);
                fa.putExtras(fa1);
                startActivity(fa);

              */
                System.out.println("按下了发布按钮");
                Animation circle_anim = AnimationUtils.loadAnimation(SucesActivity.this, R.anim.tip);
                LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
                circle_anim.setInterpolator(interpolator);
                if (circle_anim != null) {
                    v.startAnimation(circle_anim);  //开始动画
                }
                break;


            case R.id.buttonfabu:
                Intent intent009=new Intent(SucesActivity.this, fabu_det_Activity.class);
                Bundle bundle009=new Bundle();
                intent009.putExtras(bundle009);
                startActivity(intent009);
                break;

                //购物车信息
            case R.id.car1:
                ViewPager.setCurrentItem(3);
                System.out.println("按下了购物车按钮");
             /*   Intent yu=new Intent(SucesActivity.this,Yutang_Activity.class);
                Bundle yu1=new Bundle();
                yu1.putString("phone", phonenum);
                yu.putExtras(yu1);
                startActivity(yu);

              */
                break;



            //消息界面的预约教师信息
            case R.id.shopping_car:
                Intent order_teach=new Intent(SucesActivity.this,Shoppingcar_Activity.class);
                Bundle teach=new Bundle();
                teach.putString("phone", phonenum);
                order_teach.putExtras(teach);
                startActivity(order_teach);
                break;

            //消息界面的我的建议
            case R.id.order_suggest:
                Intent order_suggest=new Intent(SucesActivity.this,order_suggestActivity.class);
                Bundle suggest=new Bundle();
                suggest.putString("phone", phonenum);
                order_suggest.putExtras(suggest);
                startActivity(order_suggest);
                break;

                /*
            //消息界面的论坛
            case R.id.order_talk:
                Intent order_talk=new Intent(SucesActivity.this,order_talkActivity.class);
                Bundle talk=new Bundle();
                talk.putString("phone", phonenum);
                order_talk.putExtras(talk);
                startActivity(order_talk);
                break;
            //消息界面的优惠信息
            case R.id.order_cheaper:

                break;
            //我

            */

            //me_money
            case R.id.money1:
                Intent money_intent=new Intent(SucesActivity.this,MoneyActivity.class);
                Bundle money_bundle=new Bundle();
                money_bundle.putString("phone",phonenum );
                money_intent.putExtras(money_bundle);
                startActivity(money_intent);
                break;

            //me_order
            case R.id.order1:
                Intent order_intent=new Intent(SucesActivity.this,OrderActivity.class);
                Bundle bundle010=new Bundle();
                bundle010.putString("phone",phonenum );
                order_intent.putExtras(bundle010);
                startActivity(order_intent);
                break;
            //me_usermes
            //case R.id.user_message:
              //  break;



            //me_reward
            case R.id.reward1:
                Intent reward_intent=new Intent(SucesActivity.this,RewardActivity.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("phone",phonenum );
                reward_intent.putExtras(bundle1);
                startActivity(reward_intent);
                break;

            case R.id.plant:
                Intent plant_intent=new Intent(SucesActivity.this,PlantActivity.class);
                startActivity(plant_intent);
                break;

            case R.id.sets:
                Intent set_intent=new Intent(SucesActivity.this,SetActivity.class);
                startActivity(set_intent);
            default:
                break;



            case R.id.me1:
                ViewPager.setCurrentItem(4);
                System.out.println("按下了我的按钮");
             /*   Intent yu=new Intent(SucesActivity.this,Yutang_Activity.class);
                Bundle yu1=new Bundle();
                yu1.putString("phone", phonenum);
                yu.putExtras(yu1);
                startActivity(yu);

              */
                break;
        }



    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            //首页按钮
            case R.id.home:

                break;
            //查看鱼塘
            case R.id.yutang:

                break;
            case R.id.fabu:


                break;

             /*
                //消息
            case R.id.xiaoxi:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    layout3.setBackgroundColor(Color.rgb(152, 251, 152));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    layout3.setBackgroundColor(Color.parseColor("#F5F5F5"));
                }
                break;
            //我
            case R.id.me:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    layout4.setBackgroundColor(Color.rgb(152, 251, 152));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    layout4.setBackgroundColor(Color.parseColor("#F5F5F5"));
                }
                break;
            default:
                break;

              */
        }
        return false;
    }



    //下拉框选中结果
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }



    private class ViewPagerAdapter extends PagerAdapter {
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            view.removeView(images.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stu
            view.addView(images.get(position));
            return images.get(position);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        //每隔2秒钟切换一张图片
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPagerTask(), 2, 4, TimeUnit.SECONDS);
    }
    //切换图片
    private class ViewPagerTask implements Runnable {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            currentItem = (currentItem +1) % imageIds.length;
            //更新界面
            handler.obtainMessage().sendToTarget();
        }
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            /*
             TODO Auto-generated method stub
            设置当前页面
            */
            mViewPager.setCurrentItem(currentItem);
        }
    };
    @Override
    protected void onStop(){
        super.onStop();
    }
}
