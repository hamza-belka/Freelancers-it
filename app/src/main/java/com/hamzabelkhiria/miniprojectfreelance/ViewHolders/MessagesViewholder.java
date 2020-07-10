package com.hamzabelkhiria.miniprojectfreelance.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessagesViewholder extends RecyclerView.ViewHolder {
   public TextView sendermail , messagebody, messagedate;
   public Button reply;
   public CircleImageView senderimage;
   public ConstraintLayout msg;
    public MessagesViewholder(@NonNull View itemView) {
        super(itemView);
    sendermail= itemView.findViewById(R.id.senderusermail);
    messagebody= itemView.findViewById(R.id.messagebody);

    senderimage= itemView.findViewById(R.id.senderimage);
    msg=itemView.findViewById(R.id.msg);
    messagedate=itemView.findViewById(R.id.messagedate);
    }
}
