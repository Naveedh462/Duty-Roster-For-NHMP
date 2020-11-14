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
import com.squareup.picasso.Picasso;

import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {
    public static final String USER_KEY = "user_key";
    private Context context;
    private List<Officers> officersDataList;

    public RatingAdapter(Context context, List<Officers> officersDataList) {
        this.context = context;
        this.officersDataList = officersDataList;
    }

    public RatingAdapter() {
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewroot=LayoutInflater.from(context).inflate(R.layout.rating_list_officers, parent, false);
        return  new RatingViewHolder(viewroot);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        final Officers officers = officersDataList.get(position);
        holder.first_name.setText(officers.getFirst_Name());
        holder.last_name.setText(officers.getLast_Name());
        Picasso.get()
                .load(officers.getProfile_Image())
                .fit()
                .centerCrop()
                .into(holder.proImage);

    }

    @Override
    public int getItemCount() {
        return officersDataList.size();
    }


    class RatingViewHolder extends RecyclerView.ViewHolder {
        private TextView first_name;
        private TextView last_name;
        private ImageView proImage,moveNext;


        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            first_name = itemView.findViewById(R.id.firt_name_rating_list);
            last_name = itemView.findViewById(R.id.last_name_rating_list);
            proImage = itemView.findViewById(R.id.Pro_pic_rating_list);
            moveNext = itemView.findViewById(R.id.next_rating_list);
        }

    }
}