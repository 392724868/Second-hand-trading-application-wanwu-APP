package keshe.wanwu;

import android.app.ListActivity;

import android.os.Bundle;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.Toast;

public class PlantActivity extends ListActivity implements CalendarView.OnDateChangeListener {

    private CalendarView calend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_plant);
        calend=(CalendarView)findViewById(R.id.calendview);
        calend.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Toast.makeText(PlantActivity.this, month+"", Toast.LENGTH_SHORT).show();

    }

}
