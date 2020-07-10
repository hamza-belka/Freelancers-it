package com.hamzabelkhiria.miniprojectfreelance.Freelancer.Profile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzabelkhiria.miniprojectfreelance.Models.Experience;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.ViewHolders.ExperienceViewholder;

import java.util.List;

public class ExpAdapter extends RecyclerView.Adapter<ExperienceViewholder> {
    Context mContext;
    List<Experience> listexp;


    public ExpAdapter(Context mContext, List<Experience> listexp) {
        this.mContext = mContext;
        this.listexp = listexp;
    }


    @NonNull
    @Override
    public ExperienceViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.singlerowexperience,parent,false);

        return  new ExperienceViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewholder holder, int position) {
        Experience currentitem=listexp.get(position);
        holder.exptitle.setText(currentitem.getExptitle());
        holder.expcompany.setText(currentitem.getExpcompany());
        holder.expdatedf.setText(currentitem.getStartdate()+" - "+currentitem.getEnddate());


    }



    @Override
    public int getItemCount() {
        return listexp.size();
    }
}
