package com.hamzabelkhiria.miniprojectfreelance.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.R;

public class NotificationsViewholder extends RecyclerView.ViewHolder {
   public TextView notiftitle,notifobject,notiftxt,notifdate,notifsender;

    public NotificationsViewholder(@NonNull View itemView) {
        super(itemView);
        notiftitle = itemView.findViewById(R.id.notificationtitle);
        notifobject = itemView.findViewById(R.id.notificationobject);
        notifsender = itemView.findViewById(R.id.notifiermail);
        notifdate = itemView.findViewById(R.id.notificationdate);
        notiftxt = itemView.findViewById(R.id.notificationtxt);

    }
}
