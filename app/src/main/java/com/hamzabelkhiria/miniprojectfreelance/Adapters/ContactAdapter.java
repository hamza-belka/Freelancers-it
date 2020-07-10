package com.hamzabelkhiria.miniprojectfreelance.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hamzabelkhiria.miniprojectfreelance.Generaladress;
import com.hamzabelkhiria.miniprojectfreelance.GetImageFromURL;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.hamzabelkhiria.miniprojectfreelance.User;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<User> {
    Context mcontext;
    ArrayList<User> list;
    Generaladress g;
    LayoutInflater inflater;
    int groupid;
    public ContactAdapter(Context mcontext, int groupid, int id,ArrayList<User> list){
        super(mcontext,id,list);
        this.list=list;
        this.mcontext=mcontext;
        inflater=(LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.groupid=groupid;
        g= new Generaladress();

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initview(position,convertView, parent);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initview(position,convertView, parent);
    }
    private View initview(int position, View convertView, ViewGroup parent) {
        View itemView=inflater.inflate(groupid,parent,false);

          /*  LayoutInflater inflater= (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.from(getContext()).inflate(R.layout.singlerowcontact, parent,false);*/
        ImageView imgc = itemView.findViewById(R.id.contactimage);
        TextView txtc = itemView.findViewById(R.id.contactname);
        User currentItem = getItem(position);

            String imagepath= g.URLfirstpart+currentItem.getUserimage();
            new GetImageFromURL(imgc).execute(imagepath);
            txtc.setText(currentItem.getFullName());
        return itemView;
    }
    }



