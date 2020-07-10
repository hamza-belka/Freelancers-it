package com.hamzabelkhiria.miniprojectfreelance.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.R;

public class EducationViewholder extends RecyclerView.ViewHolder {
    public TextView school,degree,datedf;


    public EducationViewholder(@NonNull View itemView) {
        super(itemView);
        school=itemView.findViewById(R.id.school);
        degree=itemView.findViewById(R.id.degree);
        datedf=itemView.findViewById(R.id.datedf);

    }
}
