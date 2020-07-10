package com.hamzabelkhiria.miniprojectfreelance.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.Models.Notification;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.ViewHolders.NotificationsViewholder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationsViewholder> {
    Context mContext;
    List<Notification> listnotifications;

    public NotificationAdapter(Context mContext, List<Notification> listnotifications) {
        this.mContext = mContext;
        this.listnotifications = listnotifications;
    }

    @NonNull
    @Override
    public NotificationsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlenotificationlayout,parent,false);
        NotificationsViewholder vh=new NotificationsViewholder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewholder holder, int position) {
        Notification notification = listnotifications.get(position);
        holder.notiftitle.setText(notification.getNotificationtitle());
        holder.notifsender.setText(notification.getNotifiermail());
        holder.notifobject.setText(notification.getNotificationobject());
        holder.notiftxt.setText(notification.getNotificationtxt());
        holder.notifdate.setText(Datetostring(notification.getNotificationdate()));


    }

    @Override
    public int getItemCount() {
        return listnotifications.size();
    }
    public String Datetostring(Date date){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");


        String dateTime = dateFormat.format(date);
        return dateTime;

    }
}

