package com.hamzabelkhiria.miniprojectfreelance.Employer;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamzabelkhiria.miniprojectfreelance.Adapters.ViewPagerAdapter;
import com.hamzabelkhiria.miniprojectfreelance.Employer.Subfragments.MyjobsFragment;
import com.hamzabelkhiria.miniprojectfreelance.Employer.Subfragments.SavedfreelancersFragment;
import com.hamzabelkhiria.miniprojectfreelance.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    View myview;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myview= inflater.inflate(R.layout.fragment_emphome, container, false);
        viewPager=myview.findViewById(R.id.empviewpager);
        tabLayout=myview.findViewById(R.id.emptablayout);

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
        ViewPagerAdapter sectionPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        sectionPagerAdapter.AddFragment(new MyjobsFragment(),"My Jobs");
        sectionPagerAdapter.AddFragment(new SavedfreelancersFragment(),"bookmarked freelancers");
        viewPager.setAdapter(sectionPagerAdapter);
    }

}
