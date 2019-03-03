package com.example.jesse.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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