package com.example.gayathrikasilingam.taxiapp_login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    CheckBox cash, card, paytm, upi;
    Button cardver, submit;
    EditText cardnum;
    TextView tv;
    String id;
    AlertDialog.Builder builder;
    private RadioGroup radioGroup;
    int flag,flag_spl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        builder = new AlertDialog.Builder(this);
        flag=0;
        flag_spl=0;
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);

        cardver = (Button) findViewById(R.id.cardbtn);
        submit = (Button) findViewById(R.id.confirm);
        cardnum = (EditText) findViewById(R.id.cardnum);
        tv = (TextView) findViewById(R.id.status);

        //visibility
        cardnum.setVisibility(View.GONE);
        cardver.setVisibility(View.GONE);




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                if(checkedId== R.id.RadioButton2)
                {
                    cardnum.setVisibility(View.VISIBLE);
                    cardver.setVisibility(View.VISIBLE);
                    flag=1;
                }
                else
                    flag=1;
            }
        });
        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton rb = (RadioButton) findViewById(checkedId);
                id = getResources().getResourceEntryName(rb.getId());
                if(id=="RadioButton2") {
                    cardnum.setVisibility(View.VISIBLE);
                    cardver.setVisibility(View.VISIBLE);

                }
                else
                flag=1;
            }
        });*/


        cardver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardnum.getText().toString().length()!=12)
                {

                    Toast.makeText(PaymentActivity.this,"Enter Valid Card Number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    flag=1;
                    flag_spl=1;
                    Toast.makeText(PaymentActivity.this,"Fetching Bank Details...",Toast.LENGTH_LONG).show();
                    Toast.makeText(PaymentActivity.this,"Verified.",Toast.LENGTH_SHORT).show();
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardnum.setVisibility(View.VISIBLE);
                cardver.setVisibility(View.VISIBLE);
                if(flag==1 && flag_spl==0)
                {
                    Toast.makeText(getApplicationContext(),"Verify Credit Card number.",
                            Toast.LENGTH_SHORT).show();
                }
                else if(flag==1)
                {
                    //Uncomment the below code to Set the message and title from the strings.xml file
                    builder.setMessage("Confirm Ride?") .setTitle("Payment Method & Ride")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(getApplicationContext(),RideConfirmActivity.class));
                                    finish();
                                    Toast.makeText(getApplicationContext(),"Confirmed",
                                            Toast.LENGTH_SHORT).show();
                                }


                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    Toast.makeText(getApplicationContext(),"Not Confirmed",
                                            Toast.LENGTH_SHORT).show();

                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    alert.show();

            }
             else
                {
                    Toast.makeText(PaymentActivity.this,"Choose an Option.",Toast.LENGTH_SHORT).show();
            }

            }
        });
    }

    }
