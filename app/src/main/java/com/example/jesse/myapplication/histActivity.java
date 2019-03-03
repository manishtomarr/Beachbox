package com.example.jesse.myapplication;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class histActivity extends AppCompatActivity {

    //History Screen
    //Change it as you wish, the other guy who programmed this , Jesse, did it that you have to click it to work,
    // but I would have it show up. If you can add previous sessions, we have it that it adds only from the current
    // session, but for that you need a better database, yeah, we could have done better, but their was no time and
    // not a lot of help

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.histtab);
        TextView tv17 = (TextView) findViewById(R.id.textView17);
        //try {
        //for (int i = 0; i <= MaxActivity.histlist.size(); i++) {
        //tv17.setText(MaxActivity.histlist.get(i).toString());
        //    }
        //}
        //catch (IndexOutOfBoundsException z) {
        //    tv17.setText("No List");
        //}
        Button button =(Button) findViewById(R.id.displayhist);

        Typeface tizafont = Typeface.createFromAsset(getAssets(), "tiza.ttf");
        tv17.setTypeface(tizafont);
        button.setTypeface(tizafont);


    }

    public void display(View view) {
        TextView tv17 = (TextView) findViewById(R.id.textView17);
        //File Readfile = new File(getExternalFilesDir(null), "savefile.txt");
        //FileReader fw = new FileReader(Readfile);
        //BufferedReader bw = new BufferedReader(fw);
        //tv17.setText("");
        //for (int s = 0; s <= 5; s++) {
         //   String line = bw.readLine();
         //   if (line != null) {
         //       tv17.append(line + '\n');
         //       tv17.append(Integer.toString(s) + '\n' + bw.readLine());
//
  //          }
    //    }
        tv17.setText(String.valueOf(MaxActivity.histlist.size()));
        for (int i = 0; i < MaxActivity.histlist.size(); i++) {
            tv17.append(MaxActivity.histlist.get(i).toString() + '\n');
        }
    }

}
