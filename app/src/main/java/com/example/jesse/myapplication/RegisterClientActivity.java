package com.example.jesse.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.Model.Client;
import com.example.Services.Service;

public class RegisterClientActivity extends AppCompatActivity {

    Service service = new Service();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onClickRegister(View view) {
        Client client = prepareClient();
        if(client != null) {
            addClient(client);
        } else {
            Toast.makeText(getApplicationContext(), "All fields are mandatory!", Toast.LENGTH_SHORT).show();
        }
    }

    private Client prepareClient() {
        EditText fname = (EditText) findViewById(R.id.fname);
        EditText lname = (EditText) findViewById(R.id.lname);
        EditText email = (EditText) findViewById(R.id.email);
        EditText username = (EditText) findViewById(R.id.username);
        EditText pwd = (EditText) findViewById(R.id.password);
        EditText bdate = (EditText) findViewById(R.id.bdate);
        EditText mobile = (EditText) findViewById(R.id.mobile);
        EditText address = (EditText) findViewById(R.id.address);
        EditText city = (EditText) findViewById(R.id.city);
        EditText state = (EditText) findViewById(R.id.state);
        EditText postal = (EditText) findViewById(R.id.postal_code);
        RadioButton maleRadioButton = (RadioButton) findViewById(R.id.radio_male);

        Client c = new Client();
        c.setFname(fname.getText().toString());
        c.setLname(lname.getText().toString());
        c.setBdate(bdate.getText().toString());
        c.setMobile(mobile.getText().toString());
        c.setUsername(username.getText().toString());
        c.setPwd(pwd.getText().toString());
        c.setEmail(email.getText().toString());
        c.setAddress(address.getText().toString());
        c.setCity(city.getText().toString());
        c.setState(state.getText().toString());
        c.setPostalCode(postal.getText().toString());
        if(maleRadioButton.isChecked()) {
            c.setGender("male");
        } else {
            c.setGender("female");
        }
        c.setId(c.getFname()+c.getLname()+"531");

        if(!validateClient(c)) {
            c = null;
        }

        return c;
    }

    private Boolean validateClient(Client c) {
        if(c.getFname().equals("") || c.getLname().equals("") || c.getBdate().equals("") || c.getMobile().equals("") || c.getUsername().equals("") ||
                c.getPwd().equals("") || c.getEmail().equals("") || c.getAddress().equals("") || c.getCity().equals("") ||
                c.getState().equals("") || c.getPostalCode().equals("") || c.getGender().equals("") || c.getId().equals("")) {
            return false;
        }
        return true;
    }
    private void addClient(Client c) {
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
            if(msg.equals("Success")) {
                //TODO create preference here of client
                Toast.makeText(getApplicationContext(), "User Created! Please Login with your username and password", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterClientActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
