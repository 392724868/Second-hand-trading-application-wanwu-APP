package keshe.wanwu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_layout);

       /*
        //中间按钮的旋转   已经成功
        final ImageView fabu = (ImageView) findViewById(R.id.fabu);
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("点击成功");
                view.setPivotX(view.getWidth()/2);
                view.setPivotY(view.getHeight()/2);//支点在图片中心
                view.setRotation(45);

            }
        });
       */

        //旋转动画测试  成功
        final ImageView fabu1 = (ImageView) findViewById(R.id.fabu);
        fabu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("点击成功");
                Animation circle_anim = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.tip);
                LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
                circle_anim.setInterpolator(interpolator);
                if (circle_anim != null) {
                    view.startAnimation(circle_anim);  //开始动画
                }

            }
        });

    }
}
