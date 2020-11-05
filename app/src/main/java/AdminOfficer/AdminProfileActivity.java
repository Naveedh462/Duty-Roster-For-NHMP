package AdminOfficer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dutyrosterfornhmp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Model.Officers;

public class AdminProfileActivity extends AppCompatActivity {
    private TextView  adminName, adminEmail,adminMobileNo, adminCNIC, adminAddress;
    private FirebaseUser admin;
    private DatabaseReference mRefe;
    private String adminID;


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


                    //set values
                    adminName.setText(firstName+" "+lastName);
                    adminEmail.setText(emailAddress);
                    adminMobileNo.setText(mobileno);
                    adminCNIC.setText(cnic);
                    adminAddress.setText(address);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AdminProfileActivity.this, "SomeThing wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }
}