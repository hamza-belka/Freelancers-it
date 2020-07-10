package com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.Models.Education;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.ViewHolders.EducationViewholder;

import java.util.List;

public class EduAdapter extends RecyclerView.Adapter<EducationViewholder> {
    Context mContext;
    List<Education> listedu;

    public EduAdapter(Context mContext, List<Education> listedu) {
        this.mContext = mContext;
        this.listedu = listedu;
    }


    @NonNull
    @Override
    public EducationViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singleroweducation,parent,false);

        return  new EducationViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewholder holder, int position) {
        Education currentitem=listedu.get(position);
        holder.school.setText(currentitem.getSchool());
        holder.degree.setText(currentitem.getDegree());
        holder.datedf.setText(currentitem.getStartstudydate()+" - "+currentitem.getEndstudydate());



    }

    @Override
    public int getItemCount() {
        return listedu.size();
    }
}
