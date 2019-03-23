package com.example.jesse.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Model.User;

public class Main3Activity extends AppCompatActivity {

    //Login Screen

    //This has to be connected to MindBody to login properly
    //definitely needs work

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText user = (EditText) findViewById(R.id.editText);
        EditText pass = (EditText) findViewById(R.id.editText2);


        SharedPreferences userDetails = getSharedPreferences("UserCredentials", MODE_PRIVATE);
        SharedPreferences.Editor edit = userDetails.edit();
        edit.clear();
        edit.putString("username", "Siteowner");
        edit.putString("password", "apitest1234");
        edit.commit();

        User.username = "Siteowner";
        User.password = "apitest1234";
/*
        Intent intent = new Intent(this, ClassSchedule.class);
        startActivity(intent);
*/


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

    public void onClick(View view) {
        EditText user = (EditText) findViewById(R.id.editText);
        EditText pass = (EditText) findViewById(R.id.editText2);
        String e1 = user.getText().toString();
        String e2 = pass.getText().toString();
        if(e1.equals("") || e2.equals("")) {
            Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
            openPunch();
        } else {
            //validate user with id password
            Intent intent = new Intent(this, AddClient.class);
            startActivity(intent);
        }
        //using Database.java I connected the user by checking it to EditText
        /*
        else{
            Boolean chkuser = db.chkuser(e1,e2);
            if(chkuser==true) {
                openPunch();
                Toast.makeText(getApplicationContext(), "Enjoy your Workout", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Wrong Username and Password", Toast.LENGTH_SHORT).show();
            }
        }
        */
    }
    //opens up the start screen
    private void openPunch() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
