package Model;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import AdminOfficer.DetailActivity;

public class OfficerAdapter extends RecyclerView.Adapter<OfficerAdapter.OfficerViewHolder> {
    public static final String USER_KEY = "user_key";
    private Context context;
    private List<Officers> officersDataList;

    public OfficerAdapter(Context context, List<Officers> officersDataList) {
        this.context = context;
        this.officersDataList = officersDataList;
    }

    public OfficerAdapter() {
    }

    @NonNull
    @Override
    public OfficerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.list_officers, parent, false);
        return new OfficerViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull OfficerViewHolder holder, int position) {
        final Officers officers = officersDataList.get(position);
        holder.first_name.setText(officers.getFirst_Name());
        holder.last_name.setText(officers.getLast_Name());
        Picasso.get()
                .load(officers.getProfile_Image())
                .fit()
                .centerCrop()
                .into(holder.proImage);

        holder.delete_officer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Are you sure?");
                dialog.setMessage("Deleting this account will completely remove" +
                        " from the system and you won't be able to access it into app.");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String officerID = officers.getUid();
                        Task<Void> deleteTask = Utils.removeOfficer(officerID);
                        deleteTask.addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(context, "officer has removed from database ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                dialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();
            }
        });
        holder.show_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String officerID = officers.getUid();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(USER_KEY, officerID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return officersDataList.size();
    }

    class OfficerViewHolder extends RecyclerView.ViewHolder {
        private TextView first_name;
        private TextView last_name;
        private TextView delete_officer;
        private TextView show_detail;
        private ImageView proImage;


        public OfficerViewHolder(@NonNull View itemView) {
            super(itemView);
            first_name = itemView.findViewById(R.id.firt_name);
            last_name = itemView.findViewById(R.id.last_name);
            delete_officer = itemView.findViewById(R.id.delete);
            show_detail = itemView.findViewById(R.id.view_detail);
            proImage = itemView.findViewById(R.id.Pro_pic);
        }
    }

}
