package com.example.gayathrikasilingam.taxiapp_login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LocActivity extends AppCompatActivity {

    Button btn_next,refresh;
    TextView kms,eta;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);

        //Shared Preferences
        sharedpreferences = getSharedPreferences("Distance", Context.MODE_PRIVATE);

        //
        String[] area_list = getResources().getStringArray(R.array.areas);
        btn_next=(Button) findViewById(R.id.src_next);
        refresh=(Button) findViewById(R.id.refresh);
        kms=(TextView) findViewById(R.id.kms);
        eta=(TextView)findViewById(R.id.eta);




        //Adapter ONE
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,area_list);
        final AutoCompleteTextView actv =  (AutoCompleteTextView)findViewById(R.id.src_dropdown);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView



        //Adapter TWO
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item,area_list);
        final AutoCompleteTextView actv1 =  (AutoCompleteTextView)findViewById(R.id.dest_dropdown);
        actv1.setThreshold(1);//will start working from first character
        actv1.setAdapter(adapter1);//setting the adapter data into the AutoCompleteTextView


        final int[] flag = {0};
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (actv1.getText().toString().equals(actv.getText().toString()))
                {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Enter Different Locations. ", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else
                {
                    if (flag[0] >0) {
                        startActivity(new Intent(getApplicationContext(), CarTypeActivity.class));
                    }

                    int km = ThreadLocalRandom.current().nextInt(3, 14 + 1);
                    double time_per_km = 1.5;
                    int cost_per_km = 12;
                    double eta_val= time_per_km*km;
                    int cost_val= cost_per_km*km;

                    String km_str= Integer.toString(km);
                    String eta_str= Double.toString(eta_val);
                    String cost_str= Integer.toString(cost_val);
                    kms.setText(km_str);
                    eta.setText(eta_str);


                    btn_next.setText("NEXT");


                    // Shared Preferences:
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putInt("km",km);
                    editor.commit();

                    flag[0]++;
                }
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(LocActivity.this, LocActivity.class);  //your class
                startActivity(i);
                finish();
            }
        });
    }
}
