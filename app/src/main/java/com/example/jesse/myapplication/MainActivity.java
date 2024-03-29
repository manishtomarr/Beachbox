package com.example.jesse.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Start Screen
    // I am going to be honest, this part I didn't do this part too much, but in short its the one
    // that puts all of the tabs and main xml screen together
    //i did the faq, which I am pretty sure is straight forward

    public static EditText edittimer;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    FloatingActionButton faqbutton;

    private static boolean isStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsub);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        edittimer = (EditText) findViewById(R.id.TimerET);


        faqbutton = (FloatingActionButton) findViewById(R.id.faqbutton);
        faqbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, EmailActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position)
            {
                case 0:
                    Tab1 tab1 = new Tab1();
                    return tab1;

                case 1:
                    Tab2 tab2 = new Tab2();
                    return tab2;
            }
            return null;
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position)
        {
            switch(position)
            {
                case 0:
                    return "HOME";
                case 1:
                    return "GYM";
            }
            return null;
        }
    }

    public void GoHome(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
    public void nd(View view) {
        startActivity(new Intent(this, MaxActivity.class));
        //MaxActivity.START_TIME_IN_MILLIS = 400000;
    }

    public void GoLogin(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
    public void GoHistory(View view) {
        startActivity(new Intent(this, histActivity.class));
    }

    public void onClick(View view) {
        Snackbar.make(view, "Coding Required", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    public void settimer(View view) {

        EditText edittimer = (EditText) findViewById(R.id.TimerET);
        int value = Integer.parseInt(edittimer.getText().toString());
        startActivity(new Intent(this, MaxActivity.class));
        try {
            MaxActivity.START_TIME_IN_MILLIS = value * 60000;
        }
        catch (NullPointerException q) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No number",
                    Toast.LENGTH_SHORT);

            toast.show();
        }

    }

}

