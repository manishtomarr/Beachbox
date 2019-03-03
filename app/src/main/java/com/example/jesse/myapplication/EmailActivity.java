package com.example.jesse.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends AppCompatActivity {

    //FAQ screen
    //Where people will be emailing beachbox for any questions or comments
    //this really doesn't need that much change, if you want to fix what it says  go to string file

    EditText etsub, etmessage;
    Button button;
    String to, sub, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        
        etsub = (EditText)findViewById(R.id.subject);
        etmessage = (EditText)findViewById(R.id.message);
        button = (Button) findViewById(R.id.emailbutton);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetData();

                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hello@beachboxnow.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, sub);
                intent.putExtra(Intent.EXTRA_TEXT, message);

                intent.setType("message/rfc822");

                startActivity(Intent.createChooser(intent, "Select Email Sending Apps"));
            }
        });
                
    }

    private void GetData() {
        sub =etsub.getText().toString();
        message =etmessage.getText().toString();
    }
}
