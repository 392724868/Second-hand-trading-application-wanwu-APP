package keshe.wanwu;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;

public class SetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_setting);

    }

}
