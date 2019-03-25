package com.example.gayathrikasilingam.taxiapp_login;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ChooseDateActivity extends AppCompatActivity {

    Spinner spinner;
    Button btnDatePicker, btnTimePicker,next;
    TextView txtDate, txtTime,txtFare;
    private int mYear, mMonth, mDay, mHour, mMinute;
    SharedPreferences sharedpreferences;
    int date,time;


    int total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date);

        //coupons SPINNER
        String[] coupons={"Choose Coupon...","PAY50","50OFF"};
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, coupons);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(dataAdapter);




            //DATE PICKER
        btnDatePicker=(Button)findViewById(R.id.date);
        btnTimePicker=(Button)findViewById(R.id.time);
        next=(Button)findViewById(R.id.nexttt);
        txtDate=(TextView) findViewById(R.id.txtdate);
        txtFare=(TextView) findViewById(R.id.fare);
        txtTime=(TextView) findViewById(R.id.txttime);

        sharedpreferences = getSharedPreferences("Distance", Context.MODE_PRIVATE);
        int km= sharedpreferences.getInt("km",0);
        int fare= sharedpreferences.getInt("price_perkm",0);
        total= km*fare;
        txtFare.setText(Integer.toString(total));

        //coupon calc
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                  switch (position) {
                                                      case 1:
                                                          int total_1 = total / 2;
                                                          txtFare.setText(Integer.toString(total_1));
                                                          Toast.makeText(parent.getContext(), "Applied Coupon!", Toast.LENGTH_SHORT).show();
                                                          break;
                                                      case 2:
                                                          int total_2 = total - 50;
                                                          if (total_2 < 25) {
                                                              Toast.makeText(parent.getContext(), "Coupon Invalid!", Toast.LENGTH_SHORT).show();
                                                          } else {
                                                              Toast.makeText(parent.getContext(), "Coupon Applied!", Toast.LENGTH_SHORT).show();
                                                              txtFare.setText(Integer.toString(total_2));
                                                          }
                                                          break;
                                                  }
                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {

                                              }

                                          });

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(ChooseDateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                date=1;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ChooseDateActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                                time=1;

                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(date==1 && time==1)
                {
                    startActivity(new Intent(getApplicationContext(),PaymentActivity.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Enter all details!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /*public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            date=1;

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                            time=1;

                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if(v== next)
        {
            if(date==1 && time==1)
            {
                startActivity(new Intent(getApplicationContext(),PaymentActivity.class));
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Enter all details!", Toast.LENGTH_SHORT).show();
            }
        }
    }*/

}
