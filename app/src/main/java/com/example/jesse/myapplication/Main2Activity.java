package com.example.jesse.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    // I am pretty sure this is a scrap activity
    //I am unsure because Jesse was the one who finished editing the stuff and made it very cluttery

    Typeface buttonfont;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);

        button1 = findViewById(R.id.button);
        buttonfont = Typeface.createFromAsset(getAssets(), "Finished Sympathy.otf");
        button1.setTypeface(buttonfont);
    }

    public void onClick(View view) {
        openPunch();
    }

    private void openPunch() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
