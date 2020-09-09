package keshe.wanwu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Yutang_Activity extends AppCompatActivity {

    private HorizontalScrollView horizontalScrollView;
    private LinearLayout container;
    private Integer mImgIds[] = new Integer[]{R.drawable.kouhong, R.drawable.hutui, R.drawable.shuji, R.drawable.shuji};


    private ArrayList<Integer> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yutang_layout);

        bindData();
        setUIRef();
        bindHZSWData();
    }


    private void bindHZSWData() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.setMargins(20, 10, 20, 10);

        for (int i = 0; i < data.size(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(data.get(i));
            imageView.setLayoutParams(layoutParams);

         //   container.addView(imageView);
//            container.invalidate();
        }
    }

    //初始化布局中定义的控件
    private void setUIRef() {
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.Hscroll);
        container = (LinearLayout) findViewById(R.id.HscrollLinear);

    }

    //将字符串数组中的数据加入到集合当中
    private void bindData() {
        //add all cities to our ArrayList
        Collections.addAll(data, mImgIds);
    }


}
