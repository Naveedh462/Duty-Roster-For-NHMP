package AdminOfficer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dutyrosterfornhmp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import Model.OfficerAdapter;
import Model.Officers;

public class DetailActivity extends AppCompatActivity {
    private TextView Name, emailAddress, mobileNumber, CNIC, Address, ID;
    private ImageView profileImage;
    private static final String TAG = "MyTag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        // Initialisation to variables by getting id's
        ID = findViewById(R.id.user_id_in_detail);
        profileImage = findViewById(R.id.user_pic_in_detail);
        Name = findViewById(R.id.user_name_in_detail);
        emailAddress = findViewById(R.id.user_address_in_detail);
        mobileNumber = findViewById(R.id.user_mobileNo_in_detail);
        emailAddress = findViewById(R.id.user_email_in_detail);
        CNIC = findViewById(R.id.user_cnic_in_detail);
        Address = findViewById(R.id.user_address_in_detail);

        final String officerID = getIntent().getStringExtra(OfficerAdapter.USER_KEY);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("officers").child(officerID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Officers officers = snapshot.getValue(Officers.class);

                Picasso.get()
                        .load(officers.getProfile_Image())
                        .fit()
                        .centerCrop()
                        .into(profileImage);
                ID.setText(officers.getOfficer_ID());
                Name.setText(officers.getFirst_Name() + " " + officers.getLast_Name());
                emailAddress.setText(officers.getEmail_Address());
                mobileNumber.setText(officers.getMobileNo());
                CNIC.setText(officers.getCNIC());
                Address.setText(officers.getAddress());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}