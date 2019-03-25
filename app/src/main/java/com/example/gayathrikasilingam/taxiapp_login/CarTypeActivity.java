package com.example.gayathrikasilingam.taxiapp_login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CarTypeActivity extends AppCompatActivity {

    ImageButton mini_btn,micro_btn,sedan_btn;
    Button next_pg;
    TextView t1,t2,t3;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_type);

        sharedpreferences = getSharedPreferences("Distance", Context.MODE_PRIVATE);

        //FLAG
        final int[] flag = {0};

        //Button
        mini_btn=(ImageButton) findViewById(R.id.miniimg);
        micro_btn=(ImageButton) findViewById(R.id.microimg);
        sedan_btn=(ImageButton) findViewById(R.id.sedanimg);
        next_pg=(Button)findViewById(R.id.next1);

        //TextViews
        t1=(TextView)findViewById(R.id.minitxt);
        t2=(TextView)findViewById(R.id.microtxt);
        t3=(TextView)findViewById(R.id.sedantxt);

        mini_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setTextColor(getResources().getColor(R.color.colorAccent));
                t2.setTextColor(getResources().getColor(R.color.whity));
                t3.setTextColor(getResources().getColor(R.color.whity));
                flag[0] = 1;


            }
        });
        micro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setTextColor(getResources().getColor(R.color.whity));
                t2.setTextColor(getResources().getColor(R.color.colorAccent));
                t3.setTextColor(getResources().getColor(R.color.whity));
                flag[0] = 2;


            }
        });
        sedan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setTextColor(getResources().getColor(R.color.whity));
                t2.setTextColor(getResources().getColor(R.color.whity));
                t3.setTextColor(getResources().getColor(R.color.colorAccent));
                flag[0] = 3;


            }
        });

        next_pg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag[0]>0)
                {
                    // Shared Preferences:
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putInt("Car_type",flag[0]);
                    int price;
                    if(flag[0]==1)
                    {
                        price=12;
                    }
                    else if(flag[0]==2)
                    {
                        price=18;
                    }
                    else
                    {
                        price=25;
                    }
                    editor.putInt("price_perkm",price);
                    editor.commit();

                    startActivity(new Intent(getApplicationContext(),ChooseDateActivity.class));
                }
                else
                {
                    Toast.makeText(CarTypeActivity.this,"CLick an option to choose Car Type",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
