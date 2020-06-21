package com.example.mas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.core.utilities.Clock;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public  class Clockin extends AppCompatActivity {
    FingerprintManager fingerprintManager;
    KeyguardManager keyguardManager;
    Calendar calendar;
    public static EditText etdigitsin;
    private ImageView imgrefresh;
    public static Firebase mdatecheck;
    public static Boolean di, settings;
 public  static    String timein,timeinn, datein, digitsin,digitsexist;
 public DataSnapshot dataSnapshot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin);
        imgrefresh = findViewById(R.id.imgrefresh);
        etdigitsin = findViewById(R.id.etdigitsin);
        fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
       startfingerprint();

            imgrefresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recreate();
                    etdigitsin.getText().clear();
                }
            });
        timein = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        datein = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());

      // timein = Calendar.getInstance().getTime().toString();
        mdatecheck = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/Time Register/" +datein);
        mdatecheck.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                digitsexist  = dataSnapshot.toString();
                Log.d("dexist", digitsexist);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void startfingerprint() {
        if (checkfingerprintsettings()) {
            Toast.makeText(getApplicationContext(), "1. Enter the last three digits of your student ID\n 2. Place your finger on sensor ", Toast.LENGTH_SHORT).show();
            FingerPrintAuthenticator authenticator = FingerPrintAuthenticator.getInstance();
            if (authenticator.cipherInit()) {
                FingerprintManager.CryptoObject cryptObj = new FingerprintManager.CryptoObject(authenticator.getCipher());
                FingerprintManager.CryptoObject cryptObjj = new FingerprintManager.CryptoObject(authenticator.getCipher());
                FingerprintHandler fingerprintHandler = new FingerprintHandler();
                fingerprintHandler.startAuthentication(cryptObj);

              di = (Boolean) cryptObj.equals(cryptObjj);

                Log.d("Di", di.toString());
                cryptObj.getSignature();

            }
        }
    }

    private class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

        CancellationSignal signal;

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            Toast.makeText(getApplicationContext(), "Authentication failed ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            super.onAuthenticationHelp(helpCode, helpString);
            Toast.makeText(Clockin.this, "Authentication Help ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            //Toast.makeText(Clockin.this, "Authentication Failed ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
//            settings = Settings.ACTION_SECURITY_SETTINGS.contains("Finger 1");
//            Log.d("settings", settings.toString());
            //if (di = Settings.ACTION_SECURITY_SETTINGS.contentEquals("Finger 1")) {
            digitsin = etdigitsin.getText().toString().trim();
            if (TextUtils.isEmpty(digitsin)|| digitsin.length()>3){
                etdigitsin.setError("Enter the last three digits of your Student ID, eg 001");

            }
            else if (!(digitsin.equals(LoginScreen.studentid))){
                etdigitsin.setError("Enter the Student ID you used to logged in");
            }

            else if (digitsexist.contains(digitsin)){
                Toast.makeText(getApplicationContext(), "You have clocked in already", Toast.LENGTH_LONG).show();
            }

            else {
                timeinn = timein;
                Toast.makeText(Clockin.this, "Fingerprint Authentication Success ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Clockin.this, Clockedin.class));
            }
            //}
//            else {
//                Toast.makeText(getApplicationContext(),"Wrong Fingerprint, Use your thumb or register if you have not done so",Toast.LENGTH_LONG).show();
//                startActivity( new Intent(Clockin.this, MainActivity.class));
//            }








        }

        void startAuthentication(FingerprintManager.CryptoObject cryptObj) {
            signal = new CancellationSignal();
            if (ActivityCompat.checkSelfPermission(Clockin.this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            fingerprintManager.authenticate(cryptObj, signal, 0, this, null);
        }

        void cancelFingerprint() {
            signal.cancel();

        }
    }

        private  boolean checkfingerprintsettings() {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.USE_FINGERPRINT)
                    != PackageManager.PERMISSION_GRANTED) {

                return false;
            }


            if (fingerprintManager.isHardwareDetected()) {
                if (fingerprintManager.hasEnrolledFingerprints()) {
                    if (keyguardManager.isKeyguardSecure()) {
                        return true;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Enroll Fingerprint ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Settings.ACTION_SETTINGS));


                }
            }
            return false;


        }
    }

