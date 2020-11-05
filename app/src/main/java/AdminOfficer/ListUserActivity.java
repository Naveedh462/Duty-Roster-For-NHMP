package AdminOfficer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.dutyrosterfornhmp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Model.OfficerAdapter;
import Model.Officers;

public class ListUserActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private OfficerAdapter mOfficerAdapter;
    private List<Officers> mDataList;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRefe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        mDatabase = FirebaseDatabase.getInstance();
        mRefe = mDatabase.getReference("officers");

        mRecyclerView=findViewById(R.id.officer_RecyclerView);
        mDataList=new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mOfficerAdapter=new OfficerAdapter(this,mDataList);
        mRecyclerView.setAdapter(mOfficerAdapter);

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
                mOfficerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Officers officers=snapshot.getValue(Officers.class);
                officers.setUid(snapshot.getKey());

                mDataList.remove(officers);
                mOfficerAdapter.notifyDataSetChanged();

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