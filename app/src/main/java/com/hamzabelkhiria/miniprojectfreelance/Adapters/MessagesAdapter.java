package com.hamzabelkhiria.miniprojectfreelance.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.GetImageFromURL;
import com.hamzabelkhiria.miniprojectfreelance.Messagedetails;
import com.hamzabelkhiria.miniprojectfreelance.Models.Message;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.ViewHolders.MessagesViewholder;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesViewholder> {
    Context mContext;
    List<Message> listmessages;
    Generaladress g;

    public MessagesAdapter(Context mContext, List<Message> listmessages) {
        this.mContext = mContext;
        this.listmessages = listmessages;
        g= new Generaladress();
    }

    @NonNull
    @Override
    public MessagesViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlemessagelayout,parent,false);
         MessagesViewholder vh=new MessagesViewholder(v);
         return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesViewholder holder, int position) {
    final Message currentitem=listmessages.get(position);
    holder.sendermail.setText(currentitem.getSendername());
    holder.messagebody.setText(currentitem.getTitle());
    holder.messagedate.setText(currentitem.getDate());
        String imagepath= g.URLfirstpart+currentitem.getSenderimage();
        new GetImageFromURL(holder.senderimage).execute(imagepath);
        holder.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Messagedetails.class);
                intent.putExtra("msgid",currentitem.getId());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listmessages.size();
    }
}
