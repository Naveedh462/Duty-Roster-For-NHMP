package Model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Utils {
    public static Task<Void> removeOfficer(String officerId)
    {
        Task<Void>task=FirebaseDatabase.getInstance().getReference("officers")
                .child(officerId)
                .setValue(null);
        return task;
    }
}
