package com.hamzabelkhiria.miniprojectfreelance.Freelancer;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzabelkhiria.miniprojectfreelance.Adapters.NotificationAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Models.Notification;
import com.hamzabelkhiria.miniprojectfreelance.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {
    View view;
    ArrayList<Notification> list = new ArrayList<Notification>();

    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Notification notification1= new Notification();
        Notification notification2= new Notification();
        Notification notification3= new Notification();
        try {
             notification1 = new Notification(3,"title1","notifiermail1","notificationobject1","notificationtxt1", Stringtodate("2019-10-15T09:27:37Z"));
            notification2 = new Notification(3,"title2","notifiermail2","notificationobject2","notificationtxt2", Stringtodate("2019-10-18T19:27:37Z"));
            notification3 = new Notification(3,"title3","notifiermail3","notificationobject3","notificationtxt3", Stringtodate("2019-11-20T22:10:37Z"));

        } catch (ParseException e) {
            e.printStackTrace();
        }


        list.add(notification1);
        list.add(notification2);
        list.add(notification3);
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_notifications, container, false);

        RecyclerView notificationlist= view.findViewById(R.id.notificationlist);
        NotificationAdapter notificationAdapter = new NotificationAdapter(getContext(),list);
        notificationlist.setLayoutManager(new LinearLayoutManager(getActivity()));
        notificationlist.setAdapter(notificationAdapter);



        return view;
    }
    public Date Stringtodate(String sdate) throws ParseException {

        //String dtStart = "2010-10-15T09:27:37Z";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

            Date date = format.parse(sdate);
            return (date);


    }
}
