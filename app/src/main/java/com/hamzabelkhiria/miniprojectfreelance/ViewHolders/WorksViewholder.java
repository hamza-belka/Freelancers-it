package com.hamzabelkhiria.miniprojectfreelance.ViewHolders;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class WorksViewholder extends RecyclerView.ViewHolder {
    public TextView worktitle,workdescription, postermail, budgettype, budget, jobstatus;
    public CircleImageView posterimg;
    public Button save,apply,terminate;
    public ConstraintLayout job;
    public WorksViewholder(@NonNull View itemView) {
        super(itemView);
        worktitle=itemView.findViewById(R.id.worktitle);
        workdescription=itemView.findViewById(R.id.workdescription);
        postermail=itemView.findViewById(R.id.postermail);
        budgettype=itemView.findViewById(R.id.budgettype);
        budget=itemView.findViewById(R.id.budget);
        posterimg=itemView.findViewById(R.id.posterimg);
        save = itemView.findViewById(R.id.save);
        jobstatus=itemView.findViewById(R.id.jobstatus);
        apply=itemView.findViewById(R.id.apply);
        job=itemView.findViewById(R.id.job);
        terminate=itemView.findViewById(R.id.terminate);

    }
}
