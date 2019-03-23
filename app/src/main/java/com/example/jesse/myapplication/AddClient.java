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
import com.example.Model.Client;
import com.example.Services.Service;

import java.util.ArrayList;

public class AddClient extends AppCompatActivity {

    Service service = new Service();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /****** TODO : get all these hardcoded fields from user and set in client object and call addClient function *****/

        Client c = new Client();
        c.setFname("Joe");
        c.setLname("Smith");
        c.setBdate("1989-12-12");
        c.setMobile("1111111111");
        c.setUsername("js");
        c.setPwd("abc123!");
        c.setEmail("js@fake.com");
        c.setAddress("Martin Luther king");
        c.setCity("New York");
        c.setState("NY");
        c.setPostalCode("10001");

        addClient(c);

    }


    private void addClient(Client c) {
        c.setId(c.getFname()+c.getLname()+"531");
        AddOrUpdateTask addOrUpdateTask = new AddOrUpdateTask();
        addOrUpdateTask.execute(c);

    }

    class AddOrUpdateTask extends AsyncTask<Client, Void, String> {

        public AddOrUpdateTask() {
        }

        protected void onPreExecute() {
        }

        protected String doInBackground(Client ...  clients) {
            try {
                return service.addOrUpdateClient(clients[0]);
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return "some error occurred";
            }
        }

        protected void onPostExecute(String msg) {
            //TODO :show msg on screen and redirect to home

        }
    }

}
