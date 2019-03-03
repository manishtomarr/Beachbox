package com.example.jesse.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Tab1 extends Fragment {

    //this was our attempt in creating  working tab
    // it can probably improved, if you choose so


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.tab1, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.tb1TV);
        //textView.setText(getString(R.string.section_format));
        return rootView;
        //return inflater.inflate(R.layout.tab1, container, false);

    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //ViewPager nViewPager = (ViewPager) view.findViewById(R.id.containertab1);
        //nViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        //TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabsub);
        //tabLayout.setupWithViewPager(nViewPager);

        //nViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(nViewPager));
    }




}
