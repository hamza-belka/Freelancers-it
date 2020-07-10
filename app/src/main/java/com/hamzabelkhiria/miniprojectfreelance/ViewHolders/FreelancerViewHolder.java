package com.hamzabelkhiria.miniprojectfreelance.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FreelancerViewHolder extends RecyclerView.ViewHolder {
    public TextView nom, location , title , skillset;
    public CircleImageView freelancerimage;
    public Button save,hire;


    public FreelancerViewHolder(@NonNull View itemView) {
        super(itemView);
        nom=itemView.findViewById(R.id.nom);
        location=itemView.findViewById(R.id.freelanceradress);
        title=itemView.findViewById(R.id.freelancertitle);
        skillset= itemView.findViewById(R.id.freelancerskills);
        freelancerimage=itemView.findViewById(R.id.freelancerimg);
        save=itemView.findViewById(R.id.savefreelancer);
        hire=itemView.findViewById(R.id.hire);
    }
}
