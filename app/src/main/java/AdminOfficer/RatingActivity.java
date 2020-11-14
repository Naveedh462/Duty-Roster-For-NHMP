package AdminOfficer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dutyrosterfornhmp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.RatingAdapter;
import Model.Officers;

public class RatingActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RatingAdapter mRatingAdapter;
    private List<Officers> mDataList;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        mDatabase = FirebaseDatabase.getInstance();
        mRefe = mDatabase.getReference("officers");

        mRecyclerView=findViewById(R.id.officer_RecyclerView_for_Rating);
        mDataList=new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRatingAdapter =new RatingAdapter(this,mDataList);
        mRecyclerView.setAdapter(mRatingAdapter);
    }
    protected void onStart() {

        super.onStart();
        mDataList.clear();
        mRefe.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Officers officers=snapshot.getValue(Officers.class);
                officers.setUid(snapshot.getKey());
                mDataList.add(officers);
                mRatingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Officers officers=snapshot.getValue(Officers.class);
                officers.setUid(snapshot.getKey());

                mDataList.remove(officers);
                mRatingAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}