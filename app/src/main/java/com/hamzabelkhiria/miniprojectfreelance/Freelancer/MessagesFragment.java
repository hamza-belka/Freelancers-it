package com.hamzabelkhiria.miniprojectfreelance.Freelancer;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzabelkhiria.miniprojectfreelance.Adapters.ViewPagerAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Subfragments.Inbox;
import com.hamzabelkhiria.miniprojectfreelance.Freelancer.Subfragments.Sentmessages;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    View myview;
    ViewPagerAdapter sectionPagerAdapter;
        // Required empty public constructor


    public MessagesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         myview=inflater.inflate(R.layout.fragment_messages, container, false);


        viewPager=myview.findViewById(R.id.msgviewpager);
        tabLayout=myview.findViewById(R.id.msgtablayout);




        return myview;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//sectionPagerAdapter.getItem(tab.getPosition()-1).getActivity().recreate();
                //tab.getCustomView().refreshDrawableState();


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });

    }



    private void setUpViewPager(ViewPager viewPager) {
        sectionPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        sectionPagerAdapter.AddFragment(new Inbox(),"Inbox");
        sectionPagerAdapter.AddFragment(new Sentmessages(),"Sent");


        viewPager.setAdapter(sectionPagerAdapter);
    }

}
