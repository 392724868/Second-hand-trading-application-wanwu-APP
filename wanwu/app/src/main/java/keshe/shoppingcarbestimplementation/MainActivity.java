package com.wangyang.shoppingcarbestimplementation;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wangyang.shoppingcarbestimplementation.adapter.ShoppingCarAdapter;
import com.wangyang.shoppingcarbestimplementation.bean.ShoppingCarDataBean;
import com.wangyang.shoppingcarbestimplementation.customview.RoundCornerDialog;
import com.wangyang.shoppingcarbestimplementation.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 购物车的最佳实现（分店铺）
 * 主要功能：1.刷新数据；
 *           2.单选、全选；
 *           3.合计；
 *           4.删除；
 *           5.商品数量加减；
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_titlebar_center)
    TextView tvTitlebarCenter;
    @BindView(R.id.tv_titlebar_right)
    TextView tvTitlebarRight;
    @BindView(R.id.elv_shopping_car)
    ExpandableListView elvShoppingCar;
    @BindView(R.id.iv_select_all)
    ImageView ivSelectAll;
    @BindView(R.id.ll_select_all)
    LinearLayout llSelectAll;
    @BindView(R.id.btn_order)
    Button btnOrder;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.rl_total_price)
    RelativeLayout rlTotalPrice;
    @BindView(R.id.rl)
    RelativeLayout rl;
    @BindView(R.id.iv_no_contant)
    ImageView ivNoContant;
    @BindView(R.id.rl_no_contant)
    RelativeLayout rlNoContant;
    @BindView(R.id.tv_titlebar_left)
    TextView tvTitlebarLeft;

    private String shoppingCarData = "{\n" +
            "    \"code\": 200,\n" +
            "    \"datas\": [\n" +
            "        {\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": \"111111\",\n" +
            "                    \"goods_image\": \"https://album.u17i.com/image/2011/03/21/f1/120099_9266_47713_3ceL.jpg\",\n" +
            "                    \"goods_name\": \"Pokemon(套装)(两个对折)\",\n" +
            "                    \"goods_num\": \"1\",\n" +
            "                    \"goods_price\": \"198.00\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"store_id\": \"1\",\n" +
            "            \"store_name\": \"王振威的百货超市\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": \"222221\",\n" +
            "                    \"goods_image\": \"https://img14.360buyimg.com/n1/jfs/t1/20137/12/2128/508593/5c19bc64E34b0082b/2222359dff4f7f81.jpg\",\n" +
            "                    \"goods_name\": \"DickiesLOGO 双肩包情侣款学生书包多色潮流背包\",\n" +
            "                    \"goods_num\": \"2\",\n" +
            "                    \"goods_price\": \"159.00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": \"222222\",\n" +
            "                    \"goods_image\": \"https://g-search1.alicdn.com/img/bao/uploaded/i4/1978659143/TB2tKqDchXkpuFjy0FiXXbUfFXa_!!1978659143.jpg_300x300.jpg\",\n" +
            "                    \"goods_name\": \"JanSport杰斯伯双肩包女学生书包男背包运动休闲背包4QUT\",\n" +
            "                    \"goods_num\": \"2\",\n" +
            "                    \"goods_price\": \"228.00\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"store_id\": \"2\",\n" +
            "            \"store_name\": \"何子康的小地摊\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"goods\": [\n" +
            "                {\n" +
            "                    \"goods_id\": \"333331\",\n" +
            "                    \"goods_image\": \"https://www.chanel.cn/fnbv3/image/full/chanel__com/skus_relative/rouge-allure-ink--170-euphorie-6ml.3145891651706.jpg\",\n" +
            "                    \"goods_name\": \"CHANEL 香奈儿炫亮魅力柔雾唇釉 口红#978心的重建\",\n" +
            "                    \"goods_num\": \"3\",\n" +
            "                    \"goods_price\": \"330.00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": \"333332\",\n" +
            "                    \"goods_image\": \"https://img.alicdn.com/bao/uploaded/i1/2981481474/O1CN01hCXZtL1Ml8OFx6NqA_!!2981481474.jpg_400x400.jpg\",\n" +
            "                    \"goods_name\": \" wassup2020ss夏季新款渐变双色混纺防泼水华达呢短裤宽松百搭短裤\",\n" +
            "                    \"goods_num\": \"3\",\n" +
            "                    \"goods_price\": \"189.00\"\n" +
            "                },\n" +
            "                {\n" +
            "                    \"goods_id\": \"333333\",\n" +
            "                    \"goods_image\": \"https://img11.360buyimg.com/n7/jfs/t1/94869/19/14031/148839/5e5fe263Eb3704e29/980e11a214afa6d7.jpg\",\n" +
            "                    \"goods_name\": \"Skechers斯凯奇情侣款户外老爹鞋熊猫鞋休闲运动鞋小白鞋\",\n" +
            "                    \"goods_num\": \"3\",\n" +
            "                    \"goods_price\": \"329.00\"\n" +
            "                }\n" +
            "            ],\n" +
            "            \"store_id\": \"3\",\n" +
            "            \"store_name\": \"李加慧的小商店\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
    private List<ShoppingCarDataBean.DatasBean> datas;
    private Context context;
    private ShoppingCarAdapter shoppingCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        initExpandableListView();
        initData();
    }

    @OnClick({R.id.tv_titlebar_left, R.id.tv_titlebar_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_titlebar_left://刷新数据
                initData();
                break;
            case R.id.tv_titlebar_right://编辑
                String edit = tvTitlebarRight.getText().toString().trim();
                if (edit.equals("编辑")) {
                    tvTitlebarRight.setText("完成");
                    rlTotalPrice.setVisibility(View.GONE);
                    btnOrder.setVisibility(View.GONE);
                    btnDelete.setVisibility(View.VISIBLE);
                } else {
                    tvTitlebarRight.setText("编辑");
                    rlTotalPrice.setVisibility(View.VISIBLE);
                    btnOrder.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    private void initData() {
        Gson gson = new Gson();
        ShoppingCarDataBean shoppingCarDataBean = gson.fromJson(shoppingCarData, ShoppingCarDataBean.class);
        datas = shoppingCarDataBean.getDatas();

        initExpandableListViewData(datas);
    }

    /**
     * 初始化ExpandableListView
     * 创建数据适配器adapter，并进行初始化操作
     */
    private void initExpandableListView() {
        shoppingCarAdapter = new ShoppingCarAdapter(context, llSelectAll, ivSelectAll, btnOrder, btnDelete, rlTotalPrice, tvTotalPrice);
        elvShoppingCar.setAdapter(shoppingCarAdapter);

        //删除的回调
        shoppingCarAdapter.setOnDeleteListener(new ShoppingCarAdapter.OnDeleteListener() {
            @Override
            public void onDelete() {
                initDelete();
            }
        });

        //修改商品数量的回调
        shoppingCarAdapter.setOnChangeCountListener(new ShoppingCarAdapter.OnChangeCountListener() {
            @Override
            public void onChangeCount(String goods_id) {
            }
        });
    }

    /**
     * @param datas
     */
    private void initExpandableListViewData(List<ShoppingCarDataBean.DatasBean> datas) {
        if (datas != null && datas.size() > 0) {
            shoppingCarAdapter.setData(datas);

            for (int i = 0; i < shoppingCarAdapter.getGroupCount(); i++) {
                elvShoppingCar.expandGroup(i);
            }

            //使组点击无效果
            elvShoppingCar.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
                    return true;
                }
            });

            tvTitlebarRight.setVisibility(View.VISIBLE);
            tvTitlebarRight.setText("编辑");
            rlNoContant.setVisibility(View.GONE);
            elvShoppingCar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            rlTotalPrice.setVisibility(View.VISIBLE);
            btnOrder.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.GONE);
        } else {
            tvTitlebarRight.setVisibility(View.GONE);
            rlNoContant.setVisibility(View.VISIBLE);
            elvShoppingCar.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
    }

    private void initDelete() {
            boolean hasSelect = false;
            //创建临时的List，用于存储没有被选中的购物车数据
            List<ShoppingCarDataBean.DatasBean> datasTemp = new ArrayList<>();

            for (int i = 0; i < datas.size(); i++) {
                List<ShoppingCarDataBean.DatasBean.GoodsBean> goods = datas.get(i).getGoods();
                boolean isSelect_shop = datas.get(i).getIsSelect_shop();

                if (isSelect_shop) {
                    hasSelect = true;
                    //跳出本次循环，继续下次循环。
                    continue;
                } else {
                    datasTemp.add(datas.get(i).clone());
                    datasTemp.get(datasTemp.size() - 1).setGoods(new ArrayList<ShoppingCarDataBean.DatasBean.GoodsBean>());
                }

                for (int y = 0; y < goods.size(); y++) {
                    ShoppingCarDataBean.DatasBean.GoodsBean goodsBean = goods.get(y);
                    boolean isSelect = goodsBean.getIsSelect();

                    if (isSelect) {
                        hasSelect = true;
                    } else {
                        datasTemp.get(datasTemp.size() - 1).getGoods().add(goodsBean);
                    }
                }
            }

        if (hasSelect) {
            showDeleteDialog(datasTemp);
        } else {
            ToastUtil.makeText(context, "请选择要删除的商品");
        }
    }

    /**
     * @param datasTemp
     */
    private void showDeleteDialog(final List<ShoppingCarDataBean.DatasBean> datasTemp) {
        View view = View.inflate(context, R.layout.dialog_two_btn, null);
        final RoundCornerDialog roundCornerDialog = new RoundCornerDialog(context, 0, 0, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        TextView tv_message = (TextView) view.findViewById(R.id.tv_message);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        tv_message.setText("确定要删除商品吗？");

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
                datas = datasTemp;
                initExpandableListViewData(datas);
            }
        });
        //取消
        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
            }
        });
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };
}
