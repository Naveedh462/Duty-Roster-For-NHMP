package Model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class UtilsForOrder {
    public static Task<Void> removeOrder(String orderId)
    {
        Task<Void>task= FirebaseDatabase.getInstance().getReference("Orders")
                .child(orderId)
                .setValue(null);
        return task;
    }
}
