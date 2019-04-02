package com.example.jesse.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.Model.Clazz;

public class ClazzDetail extends AppCompatActivity {

    Clazz clazz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clazz_detail);

        clazz = (Clazz) getIntent().getSerializableExtra("clazz");
        System.out.print("SS");
    }
}
