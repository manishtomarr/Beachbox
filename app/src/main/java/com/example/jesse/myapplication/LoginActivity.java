package com.example.jesse.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Model.Client;
import com.example.Model.User;
import com.example.Services.Service;

public class LoginActivity extends AppCompatActivity {

    //Login Screen

    //This has to be connected to MindBody to login properly
    //definitely needs work

    Service service = new Service();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

    public void onClickLogin(View view) {
        EditText user = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);
        String username = user.getText().toString();
        String password = pass.getText().toString();
        if(username.equals("") || password.equals("")) {
            Toast.makeText(getApplicationContext(), "Fields are Empty", Toast.LENGTH_SHORT).show();
            openPunch();
        } else {

            validateLoginTask validateLoginTask = new validateLoginTask();
            validateLoginTask.execute(username, password);
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

    public void onClickSignUp(View view) {
        Intent intent = new Intent(this, AddClient.class);
        startActivity(intent);
    }
    //opens up the start screen
    private void openPunch() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    class validateLoginTask extends AsyncTask<String, Void, Client> {

        public validateLoginTask() {
        }

        protected void onPreExecute() {
        }

        protected Client doInBackground(String ...  params) {
            try {
                return service.validateLogin(params[0], params[1]);
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                Toast.makeText(getApplicationContext(), "Some error occurred", Toast.LENGTH_SHORT).show();
                return null;
            }
        }

        protected void onPostExecute(Client client) {
            if(client != null) {
                //TODO: save client in shared pref
                Intent intent = new Intent(LoginActivity.this, ClassSchedule.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Username and Password is Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
