package Officers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dutyrosterfornhmp.GetStartActivity;
import com.example.dutyrosterfornhmp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Model.Officers;

public class OfficerProfileActivity extends AppCompatActivity {
    private TextView officerName, officerEmail,officerMobileNo, officerCNIC, officerAddress,ID;
    private ImageView officer_dp;
    private Button Logout;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefe;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officer_profile);

        /* getting current user and reference from real-time database with uid*/
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        String uid=mAuth.getCurrentUser().getUid();
        mRefe = mDatabase.getReference("officers").child(uid);

        // intialization
        officerName=findViewById(R.id.officer_name_in_profile);
        officerEmail=findViewById(R.id.officer_email_in_profile);
        officerMobileNo=findViewById(R.id.officer_mobileNo_in_profile);
        officerCNIC=findViewById(R.id.officer_cnic_in_profile);
        officerAddress=findViewById(R.id.officer_address_in_profile);
        officer_dp=findViewById(R.id.officer_pic_in_profile);
        ID=findViewById(R.id.officer_id_in_profile);
        Logout=findViewById(R.id.officer_logout_in_Profile);

        /* logout to current user */
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.officer_logout_in_Profile:
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(), GetStartActivity.class));
                        break;
                }

            }
        });
        /* setting detail of the current officer */
        mRefe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Officers officerProfile=snapshot.getValue(Officers.class);
                if(officerProfile!=null)
                {
                    String firstName=officerProfile.getFirst_Name();
                    String lastName=officerProfile.getLast_Name();
                    String emailAddress=officerProfile.getEmail_Address();
                    String mobileno=officerProfile.getMobileNo();
                    String cnic=officerProfile.getCNIC();
                    String address=officerProfile.getAddress();
                    String id=String.valueOf(officerProfile.getOfficer_ID());

                    //set values
                    Picasso.get()
                            .load(snapshot.child("profile_Image").getValue().toString())
                            .fit()
                            .centerCrop()
                            .into(officer_dp);
                    officerName.setText(firstName+" "+lastName);
                    officerEmail.setText(emailAddress);
                    officerMobileNo.setText(mobileno);
                    officerCNIC.setText(cnic);
                    officerAddress.setText(address);
                    ID.setText(id);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}