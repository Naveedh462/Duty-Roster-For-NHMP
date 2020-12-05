package Model;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dutyrosterfornhmp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private static final String TAG = "MyTag";
    private Context context;
    private List<Orders> ordersLists;
    public OrderAdapter(Context context, List<Orders> List) {
        this.context = context;
        this.ordersLists = List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row_admin_view, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Orders orders = ordersLists.get(position);
        holder.title.setText(orders.getTitle());
        holder.desc.setText(orders.getDesc());
        Picasso.get()
                .load(orders.getImage())
                .into(holder.image);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final  String orderID=String.valueOf(orders);
               // Log.d(TAG, "order uid: "+String.valueOf(orders));
            }
        });
    }

    @Override
    public int getItemCount() {
        return ordersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public ImageView image;
        public ImageView delete;

        public ViewHolder(View view, Context ctx) {
            super(view);
            title = (TextView) view.findViewById(R.id.postTitleList);
            desc = (TextView) view.findViewById(R.id.postTextList);
            image = (ImageView) view.findViewById(R.id.postImageList);
            delete=(ImageView) view.findViewById(R.id.delete_order);


        }
    }
}
