package com.hamzabelkhiria.miniprojectfreelance.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list1 =new ArrayList<>();



    List<String> listTitle =new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super (fm);
    }


    @Override
    public Fragment getItem(int position) {
        return list1.get(position);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }

    public void AddFragment(Fragment frag, String title){
        list1.add(frag);
        listTitle.add(title);
    }

}
