package com.example.jesse.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.Model.Clazz;
import com.example.Services.Service;

import java.util.List;

public class ClassSchedule extends AppCompatActivity {

    Service service = new Service();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        RetrieveClassesTask retrieveClassesTask  = new RetrieveClassesTask();
        retrieveClassesTask.execute();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    class RetrieveClassesTask extends AsyncTask<String, Void, List<Clazz>> {

        public RetrieveClassesTask() {
        }

        protected void onPreExecute() {
        }

        protected List<Clazz> doInBackground(String... params) {
            try {
                return service.getClasses();
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(List<Clazz> listOfClasses) {
            if(listOfClasses.size() == 0) {
                Log.d("Class Schedule", "No Class List");
            } else {

            }
        }
    }

}
