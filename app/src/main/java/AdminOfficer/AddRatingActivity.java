package AdminOfficer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.dutyrosterfornhmp.R;

public class AddRatingActivity extends AppCompatActivity {
    ProgressBar helpsProressBar,tickingProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);

        /* initialization*/
        helpsProressBar=findViewById(R.id.help_progress_bar);
        tickingProgressBar=findViewById(R.id.eticking_progress_bar);
        int helps=70;
        int ticking=90;
        helpsProressBar.setMax(100);
        tickingProgressBar.setMax(100);
        helpsProressBar.setProgress(helps);
        tickingProgressBar.setProgress(ticking);


    }
}