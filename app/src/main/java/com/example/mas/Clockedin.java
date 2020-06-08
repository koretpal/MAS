package com.example.mas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class Clockedin extends AppCompatActivity {
    public static TextView tvnamein, tvstudidin,tvtextin, tvsosotimein, tvsosodatein;
    public static Firebase mref, mtimein;
            DatabaseReference mtimereg, mtest, mtest1, mtest11;
            FirebaseDatabase mtest2, mtest22;
            DataSnapshot dataSnapshot;
            ProgressDialog progressDialog;
    public static String fname, lname, studid, ktimereg,kkchildref,mtest3, mtimeconc;    ;


    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockedin);
        Firebase.setAndroidContext(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
//        mtest = FirebaseDatabase.getInstance().getReference();
//       mtest1 = mtest.getDatabase().getReferenceFromUrl("https://masfirebaseproject-df6e3.firebaseio.com/UserDetails");
//       Map<String, String> map = dataSnapshot.getValue(Map.class);

       //fname = mtest1.getKey();
       // Log.d("Tag", fname.toString());


        tvnamein = findViewById(R.id.tvnamein);
        tvstudidin = findViewById(R.id.tvstudidin);
        tvsosotimein = findViewById(R.id.tvsosotimein);
        tvsosodatein = findViewById(R.id.tvsosodatein);
        tvtextin = findViewById(R.id.tvtextin);
        kkchildref = Registration2.kchildref;


        mref = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/UserDetails/"+Clockin.digitsin);
//        if (mref.toString().endsWith(studid))
        Log.d("Tag", mref.toString());


        mref.addValueEventListener(new ValueEventListener() {
            @Override
      public void  onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                Map<String, String>map = dataSnapshot.getValue(Map.class);
            if (dataSnapshot.exists()) {
                fname = map.get("First Name");
                lname = map.get("Last Name");
                studid = map.get("Student ID");

                Log.d("Tag", "First Name: " + fname);
                Log.d("Tag", "Student ID" + studid);

                progressDialog.dismiss();
                tvnamein.setText("Hello " + fname + ",");
                tvstudidin.setText(studid);

                tvtextin.setText("You clocked in at:");
                tvsosotimein.setText(Clockin.timein);

                mtimeconc = Clockin.digitsin+"  "+Clockin.datein;
                mtimein = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/Time Register/" +mtimeconc);


                mtimein.child("First Name").setValue(fname);
                mtimein.child("Last Name").setValue(lname);
                mtimein.child("Student ID").setValue(studid);
                mtimein.child("Time in").setValue(Clockin.timein);
            }

                else {
                    startActivity( new Intent(Clockedin.this,Clockin.class));
                Toast.makeText(Clockedin.this, "User not registered, Register user first",Toast.LENGTH_LONG).show();
                Clockin.etdigitsin.setError("User not registered");
            }


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        mtimereg = FirebaseDatabase.getInstance().getReference().child("Time Register");






        DatabaseReference mtimeref = mtimereg.push();
        ktimereg = mtimeref.getKey();
       

//       Log.d("Tag", fname);
       Log.d("Tag", Clockin.timein);




    }

}
