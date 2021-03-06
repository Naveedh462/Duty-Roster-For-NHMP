package AdminOfficer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dutyrosterfornhmp.GetStartActivity;
import com.example.dutyrosterfornhmp.LoginAdminActivity;
import com.example.dutyrosterfornhmp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class AdminProfileActivity extends AppCompatActivity {
    private TextView  adminName, adminEmail,adminMobileNo, adminCNIC, adminAddress,ID;
    private FirebaseUser admin;
    private DatabaseReference mRefe;
    private String adminID;
    private ImageView admin_dp;
    private Button Logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        admin= FirebaseAuth.getInstance().getCurrentUser();
        mRefe=FirebaseDatabase.getInstance().getReference("Admin");
        adminID=admin.getUid();

        // intialization
        adminName=findViewById(R.id.user_name_in_profile);
        adminEmail=findViewById(R.id.user_email_in_profile);
        adminMobileNo=findViewById(R.id.user_mobileNo_in_profile);
        adminCNIC=findViewById(R.id.user_cnic_in_profile);
        adminAddress=findViewById(R.id.user_address_in_profile);
        admin_dp=findViewById(R.id.user_pic_in_profile);
        ID=findViewById(R.id.user_id_in_profile);
        Logout=findViewById(R.id.logout_in_Profile);


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.logout_in_Profile:
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(), GetStartActivity.class));
                        break;
                }

            }
        });




        mRefe.child(adminID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Admin adminProfile=snapshot.getValue(Admin.class);
                if(adminProfile!=null){
                    String firstName=adminProfile.getFirst_Name();
                    String lastName=adminProfile.getLast_Name();
                    String emailAddress=adminProfile.getEmail_address();
                    String mobileno=adminProfile.getMobileNo();
                    String cnic=adminProfile.getCNIC();
                    String address=adminProfile.getAddress();
                    String id=String.valueOf(adminProfile.getAdmin_ID());


                    //set values
                    Picasso.get()
                            .load("https://firebasestorage.googleapis.com/v0/b/duty-roster-for-nhmp.appspot.com/o/Profile_Pics%2FNaveed%203.jpg?alt=media&token=15c4b1b1-e44c-4085-863d-6602a401314d")
                            .fit()
                            .centerCrop()
                            .into(admin_dp);
                    adminName.setText(firstName+" "+lastName);
                    adminEmail.setText(emailAddress);
                    adminMobileNo.setText(mobileno);
                    adminCNIC.setText(cnic);
                    adminAddress.setText(address);
                    ID.setText(id);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminProfileActivity.this, "SomeThing wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
