package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import static com.example.mas.Clockin.etdigitsin;

public class Clockout extends AppCompatActivity {
    FingerprintManager fingerprintManager;
    KeyguardManager keyguardManager;
    Calendar calendar;
    public static EditText etdigitsout;

    public static Boolean di, settings;
    public  static    String timeout, dateout, digitsout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockout);
        etdigitsout = findViewById(R.id.etdigitsout);
        fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
        keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        startfingerprint();



        timeout = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        dateout = java.text.DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        Log.d("Dateout", dateout);
        // timein = Calendar.getInstance().getTime().toString();

    }
    private void startfingerprint() {
        if (checkfingerprintsettings()) {
            Toast.makeText(getApplicationContext(), "Place your finger on sensor ", Toast.LENGTH_SHORT).show();
            FingerPrintAuthenticator authenticator = FingerPrintAuthenticator.getInstance();
            if (authenticator.cipherInit()) {
                FingerprintManager.CryptoObject cryptObj = new FingerprintManager.CryptoObject(authenticator.getCipher());
                FingerprintManager.CryptoObject cryptObjj = new FingerprintManager.CryptoObject(authenticator.getCipher());
                Clockout.FingerprintHandler fingerprintHandler = new Clockout.FingerprintHandler();
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
            Toast.makeText(Clockout.this, "Authentication Help ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
           // Toast.makeText(Clockout.this, "Authentication Failed ", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
//            settings = Settings.ACTION_SECURITY_SETTINGS.contains("Finger 1");
//            Log.d("settings", settings.toString());
            //if (di = Settings.ACTION_SECURITY_SETTINGS.contentEquals("Finger 1")) {
            digitsout = etdigitsout.getText().toString().trim();
            if (TextUtils.isEmpty(digitsout)|| digitsout.length()>3){
                etdigitsout.setError("Enter the last three digits of your Student ID, eg 001");
            } else {

                Toast.makeText(Clockout.this, "Fingerprint Authentication Success ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Clockout.this, Clockedout.class));
            }
            //}
//            else {
//                Toast.makeText(getApplicationContext(),"Wrong Fingerprint, Use your thumb or register if you have not done so",Toast.LENGTH_LONG).show();
//                startActivity( new Intent(Clockin.this, MainActivity.class));
//            }




        }

        void startAuthentication(FingerprintManager.CryptoObject cryptObj) {
            signal = new CancellationSignal();
            if (ActivityCompat.checkSelfPermission(Clockout.this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
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