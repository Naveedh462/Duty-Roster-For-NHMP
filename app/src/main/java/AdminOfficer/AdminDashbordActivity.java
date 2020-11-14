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
import android.widget.TextView;

import com.example.dutyrosterfornhmp.GetStartActivity;
import com.example.dutyrosterfornhmp.LoginAdminActivity;
import com.example.dutyrosterfornhmp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Model.ImageAdapter;

public class AdminDashbordActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageView addOfficer,addRating, viewListOfficers,admin_dp;
    private TextView adminName,adminId,adminNameInMenu,adminEmailInMenu;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefe;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashbaord);

        /* getting current user and reference from real-time database with uid*/
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        String uid=mAuth.getCurrentUser().getUid();
        mRefe = mDatabase.getReference("Admin").child(uid);


        /* initialization by id from xml layout */
        drawerLayout = findViewById(R.id.DrawLayout_of_admin);
        navigationView = findViewById(R.id.navview_admin);
        toolbar = findViewById(R.id.toolbar_in_admin_dashboard);
        addOfficer = findViewById(R.id.add_Officer);
        viewListOfficers = findViewById(R.id.view_list_officers);
        adminName=findViewById(R.id.admin_name);
        adminId=findViewById(R.id.admin_id);
        admin_dp=findViewById(R.id.admin_dp);
        addRating=findViewById(R.id.add_rating);
        View hView=navigationView.inflateHeaderView(R.layout.header);
        adminNameInMenu=hView.findViewById(R.id.current_name_in_menu);
        adminEmailInMenu=hView.findViewById(R.id.current_email_in_menu);

        /* now set admin detail */
        mRefe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String fName=String.valueOf(snapshot.child("first_Name").getValue());
                String lName=String.valueOf(snapshot.child("last_Name").getValue());
                String id=String.valueOf(snapshot.child("admin_ID").getValue());
                String email=String.valueOf(snapshot.child("email_address").getValue());

                /* getting profile image url from real-time database */
                Picasso.get()
                        .load(snapshot.child("profile_Image").getValue().toString())
                        .fit()
                        .centerCrop()
                        .into(admin_dp);
                adminName.setText(fName+" "+lName);
                adminId.setText(id);
                adminNameInMenu.setText(fName+" "+lName);
                adminEmailInMenu.setText(email);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adminName.setText(error.toString());
                adminId.setText(error.toString());

            }
        });

        // slider
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
        addRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RatingActivity.class));
            }
        });

    }
 @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            switch (item.getItemId())
            {
                case R.id.logout:
                    FirebaseAuth.getInstance().signOut();
                    finish();
                    startActivity(new Intent(this, GetStartActivity.class));
                    break;
                case R.id.profile:
                    startActivity(new Intent(this, AdminProfileActivity.class));
                    break;
            }
            return true;
        }

    @Override
    public void onBackPressed() {
        return;
    }
}