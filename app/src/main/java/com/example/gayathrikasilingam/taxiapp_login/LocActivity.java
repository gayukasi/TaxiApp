package com.example.gayathrikasilingam.taxiapp_login;

import android.app.Activity;
import android.content.Intent;
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

    Button btn_next;
    TextView kms,eta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);

        String[] area_list = getResources().getStringArray(R.array.areas);
        btn_next=(Button) findViewById(R.id.src_next);
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

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actv1.getOnItemClickListener().toString().equals(actv.getText().toString()))
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Enter Different Locations. ", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    int randomNum = ThreadLocalRandom.current().nextInt(3, 14 + 1);
                    String l=
                    kms.setText();
                    startActivity(new Intent(getApplicationContext(), CarTypeActivity.class));
                }
            }
        });
    }
}
