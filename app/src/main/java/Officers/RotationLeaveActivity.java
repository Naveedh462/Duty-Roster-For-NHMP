package Officers;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

import com.example.dutyrosterfornhmp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RotationLeaveActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_leave);
        CalendarView calendarView=(CalendarView)findViewById(R.id.calander_rotation_leave);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String selectedDate = sdf.format(new Date(calendarView.getDate()));

        Log.d(TAG, "onChildAdded: Name: "+selectedDate);

    }
}