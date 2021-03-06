package AdminOfficer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dutyrosterfornhmp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import Model.Officers;

public class AddUserActivity extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefe;
    private FirebaseAuth mAuth;
    private StorageReference mFirebaseStorage;
    private Button addUser;
    private ProgressDialog mProgressDialog;
    private EditText userFirstName, userLastName, userEmailAddress,
            userPassword, gender, FatherName, Address, CNIC, ID, mobileNo;
    private ImageView profilePic;
    private Uri mImageUri = null;
    private final static int GALLERY_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRefe = mDatabase.getReference("officers");
        mFirebaseStorage = FirebaseStorage.getInstance().getReference();
        mProgressDialog = new ProgressDialog(this);

        profilePic = findViewById(R.id.ProfilePic);
        userFirstName = findViewById(R.id.user_first_name);
        userLastName = findViewById(R.id.user_last_name);
        FatherName = findViewById(R.id.user_father_name);
        userEmailAddress = findViewById(R.id.user_email_address);
        userPassword = findViewById(R.id.user_password);
        addUser = findViewById(R.id.add_user);
        gender = findViewById(R.id.user_gender);
        Address = findViewById(R.id.user_address);
        CNIC = findViewById(R.id.user_cnic_no);
        ID = findViewById(R.id.user_Id);
        mobileNo = findViewById(R.id.user_mobile_no);


        // set officer profile picture
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);

            }
        });

        // create new account for the officer
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewUser();
            }
        });
    }

    public void addNewUser() {
        String imageUrl;
        final String first_name = userFirstName.getText().toString().trim();
        final String last_name = userLastName.getText().toString().trim();
        final String email_address = String.valueOf(userEmailAddress.getText().toString().trim());
        final String password = String.valueOf(userPassword.getText().toString().trim());
        final String Gender = gender.getText().toString().trim();
        final String id = String.valueOf(ID.getText().toString().trim());
        final String address =String.valueOf(Address.getText().toString().trim());
        final String cnic = String.valueOf(CNIC.getText().toString().trim());
        final String Mobileno = String.valueOf(mobileNo.getText().toString().trim());
        final String fathername = FatherName.getText().toString().trim();
        if (first_name.isEmpty()) {
            userFirstName.setError("required first name");
            userFirstName.requestFocus();
            return;
        }
        if (last_name.isEmpty()) {
            userLastName.setError("required last name");
            userLastName.requestFocus();
            return;
        }

        if (email_address.isEmpty()) {
            userEmailAddress.setError("required last name");
            userEmailAddress.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email_address).matches()) {
            userEmailAddress.setError("required valid email address");
            return;
        }
        if (password.isEmpty()) {
            userPassword.setError("required password");
            return;
        }
        if (password.length() < 6) {
            userPassword.setError("password should be 6 character");
            return;
        }
        mProgressDialog.setTitle("Creating user...");
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email_address, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final StorageReference imagePath = mFirebaseStorage.child("Profile_Pics")
                            .child(mImageUri.getLastPathSegment());
                    imagePath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imagePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri donwloadUrl = uri;
                                    String imageUrl = donwloadUrl.toString();
                                    Officers officers = new Officers(imageUrl, first_name, last_name, fathername, Gender, id, email_address, Mobileno, cnic, address);
                                    mRefe.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(officers).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                Toast.makeText(AddUserActivity.this, "user add successfully", Toast.LENGTH_SHORT).show();
                                                mProgressDialog.hide();
                                            } else {
                                                Toast.makeText(AddUserActivity.this, "user not add successfully", Toast.LENGTH_SHORT).show();
                                                mProgressDialog.hide();
                                            }
                                        }
                                    });


                                }
                            });
                        }
                    });

                } else {
                    Toast.makeText(AddUserActivity.this, "user not add successfully", Toast.LENGTH_SHORT).show();
                    mProgressDialog.hide();

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            Uri mImageUri = data.getData();
            profilePic.setImageURI(mImageUri);
            CropImage.activity(mImageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .start(this);
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mImageUri = result.getUri();
                profilePic.setImageURI(mImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}