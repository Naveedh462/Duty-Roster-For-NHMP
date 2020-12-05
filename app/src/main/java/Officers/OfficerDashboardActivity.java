package Officers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ActionTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.TouchListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.dutyrosterfornhmp.GetStartActivity;
import com.example.dutyrosterfornhmp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import AdminOfficer.AdminProfileActivity;
import AdminOfficer.OrderListActivity;
import Model.ImageAdapter;

public class OfficerDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefe;
    private FirebaseAuth mAuth;

    private TextView officerName,officerId,officerNameInMenu,officerEmailInMenu;
    private ImageView officer_dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_dashboard);

        /* getting current user and reference from real-time database with uid*/
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        String uid=mAuth.getCurrentUser().getUid();
        mRefe = mDatabase.getReference("officers").child(uid);

        /* initialization by id from xml layout */
        drawerLayout = findViewById(R.id.DrawLayout_of_officer);
        navigationView = findViewById(R.id.navview_officer);
        toolbar = findViewById(R.id.toolbar_in_officer_dashboard);
        officerName=findViewById(R.id.officer_name);
        officerId=findViewById(R.id.officer_id);
        officer_dp=findViewById(R.id.officer_dp);
        View hView=navigationView.inflateHeaderView(R.layout.header);
        officerNameInMenu=hView.findViewById(R.id.current_name_in_menu);
        officerEmailInMenu=hView.findViewById(R.id.current_email_in_menu);



        /* slider  */
        final ImageSlider imageSlider=findViewById(R.id.image_slider_officer);
        final List<SlideModel> remoteimages= new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Orders")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data:snapshot.getChildren())
                            remoteimages.add(new SlideModel(data.child("image").getValue().toString(), data.child("desc").getValue().toString(), ScaleTypes.FIT));
                        imageSlider.setImageList(remoteimages,ScaleTypes.FIT);
                        imageSlider.setTouchListener(new TouchListener() {
                            @Override
                            public void onTouched(ActionTypes actionTypes) {
                               // startActivity(new Intent(getApplicationContext(), ViewOrderActivity.class));

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        /* setting of tool bar */
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        /* setting current officer detail*/
        mRefe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String fName=String.valueOf(snapshot.child("first_Name").getValue());
                String lName=String.valueOf(snapshot.child("last_Name").getValue());
                String id=String.valueOf(snapshot.child("officer_ID").getValue());
                String email=String.valueOf(snapshot.child("email_Address").getValue());

                /* getting profile image url from real-time database */
                Picasso.get()
                        .load(snapshot.child("profile_Image").getValue().toString())
                        .fit()
                        .centerCrop()
                        .into(officer_dp);
                officerName.setText(fName+" "+lName);
                officerId.setText(id);
                officerNameInMenu.setText(fName+" "+lName);
                officerEmailInMenu.setText(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                officerName.setText(error.toString());
                officerId.setText(error.toString());
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, GetStartActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(this, OfficerProfileActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}