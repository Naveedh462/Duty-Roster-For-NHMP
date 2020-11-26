package AdminOfficer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dutyrosterfornhmp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firestore.v1.StructuredQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Model.OfficerAdapter;
import Model.Officers;
import Model.OrderAdapter;
import Model.Orders;

public class OrderListActivity extends AppCompatActivity {
    private DatabaseReference mRefe;
    private RecyclerView mRecyclerView;
    private OrderAdapter mOrderAdapter;
    private List<Orders> mDataList;
    private FirebaseDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        mDatabase = FirebaseDatabase.getInstance();
        mRefe = mDatabase.getReference("Orders");
        mRefe.keepSynced(true);


        mDataList = new ArrayList<>();


        mRecyclerView = (RecyclerView) findViewById(R.id.orderRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        mRefe.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Orders order=dataSnapshot.getValue(Orders.class);
                mDataList.add(order);
                Collections.reverse(mDataList);

                mOrderAdapter= new OrderAdapter(OrderListActivity.this,mDataList);
                mRecyclerView.setAdapter(mOrderAdapter);
                mOrderAdapter.notifyDataSetChanged();



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}