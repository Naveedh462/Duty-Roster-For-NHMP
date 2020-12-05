package Officers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dutyrosterfornhmp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import AdminOfficer.OrderListActivity;
import Model.OrderAdapter;
import Model.Orders;

public class ViewOrderActivity extends AppCompatActivity {
    private DatabaseReference mRefe;
    private RecyclerView mRecyclerView;
    private OrderAdapter mOrderAdapter;
    private List<Orders> mDataList;
    private FirebaseDatabase mDatabase;
    private TextView addOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        mDatabase = FirebaseDatabase.getInstance();
        mRefe = mDatabase.getReference("Orders");
        mRefe.keepSynced(true);


        mDataList = new ArrayList<>();


        mRecyclerView = (RecyclerView) findViewById(R.id.orderRecyclerView_for_Officer);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    protected void onStart() {
        super.onStart();
        mRefe.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Orders order = dataSnapshot.getValue(Orders.class);
                mDataList.add(order);
                Collections.reverse(mDataList);

                mOrderAdapter = new OrderAdapter(ViewOrderActivity.this, mDataList);
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