package Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dutyrosterfornhmp.R;
import com.google.firestore.v1.StructuredQuery;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private List<Orders> ordersLists;
    public OrderAdapter(Context context, List<Orders> List) {
        this.context = context;
        this.ordersLists = List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Orders order = ordersLists.get(position);
        String imageUrl = null;

        holder.title.setText(order.getTitle());
        holder.desc.setText(order.getDesc());
        imageUrl = order.getImage();
        Picasso.get()
                .load(imageUrl)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return ordersLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView desc;
        public ImageView image;

        public ViewHolder(View view, Context ctx) {
            super(view);
            title = (TextView) view.findViewById(R.id.postTitleList);
            desc = (TextView) view.findViewById(R.id.postTextList);
            image = (ImageView) view.findViewById(R.id.postImageList);

        }
    }
}
