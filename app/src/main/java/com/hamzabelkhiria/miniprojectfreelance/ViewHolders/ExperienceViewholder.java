package com.hamzabelkhiria.miniprojectfreelance.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.R;

public class ExperienceViewholder extends RecyclerView.ViewHolder {
    public TextView exptitle,expcompany,expdatedf;
    public ExperienceViewholder(@NonNull View itemView) {
        super(itemView);
        exptitle=itemView.findViewById(R.id.exptitle);
        expcompany=itemView.findViewById(R.id.expcompany);
        expdatedf=itemView.findViewById(R.id.expdatedf);
    }
}
