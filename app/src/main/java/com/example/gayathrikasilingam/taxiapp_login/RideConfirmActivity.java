package com.example.gayathrikasilingam.taxiapp_login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RideConfirmActivity extends AppCompatActivity {

    Button call, cancel, emergency;


    private Toolbar mTopToolbar;

    //
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_confirm);
        call = (Button) findViewById(R.id.calldriver);
        //
        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    System.out.println("User logged in");
                }
                else{
                    System.out.println("User not logged in");
                }
            }
        };
      //
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = "+919787959863";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));

                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);

            }
        });

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CancelledBookActivity.class));
            }
        });
        emergency = (Button) findViewById(R.id.emergency);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EmergencyActivity.class));
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.policy:
                startActivity(new Intent(this, PolicyActivity.class));
                return true;
            case R.id.faq:
                startActivity(new Intent(this, FAQActivity.class));
                return true;
            case R.id.feed:
                startActivity(new Intent(this, FeedbackActivity.class));
                return true;
            case R.id.contact:
                startActivity(new Intent(this, ContactUsActivity.class));
                return true;
            case R.id.About:
                startActivity(new Intent(this, AboutUsActivity.class));
                return true;
            case R.id.signout:
                if (mAuthListener != null) {
                    mAuth.removeAuthStateListener(mAuthListener);

                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
