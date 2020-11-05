package AdminOfficer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.example.dutyrosterfornhmp.LoginActivity;
import com.example.dutyrosterfornhmp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.ImageAdapter;

public class AdminDashbordActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ProgressDialog mProgressDialog;
    private ImageView addOfficer, viewListOfficers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashbaord);


        mProgressDialog = new ProgressDialog(this);
        drawerLayout = findViewById(R.id.DrawLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        addOfficer = findViewById(R.id.add_Officer);
        viewListOfficers = findViewById(R.id.view_list_officers);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);


        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        addOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddUserActivity.class);
                // add animations
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(addOfficer, "transition_add_officer");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AdminDashbordActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });
        viewListOfficers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListUserActivity.class);
                // add animations
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(addOfficer, "transition_list_officer");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AdminDashbordActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

    }
 @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            switch (item.getItemId())
            {
                case R.id.Admin_logout:
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(this, LoginActivity.class));
                    break;
                case R.id.Admin_profile:
                    startActivity(new Intent(this, AdminProfileActivity.class));
                    break;
            }
            return true;
        }
}