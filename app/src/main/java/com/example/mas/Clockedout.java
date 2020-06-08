package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class Clockedout extends AppCompatActivity {
    public static TextView tvnameout, tvstudidout,tvtextout, tvsosotimeout, tvsosodateout;
    public static Firebase mref, mtimeout;
    public  static EditText etdigitsout;
    DatabaseReference mtimereg, mtest, mtest1, mtest11;
    FirebaseDatabase mtest2, mtest22;
    DataSnapshot dataSnapshot;
    ProgressDialog progressDialog;
    public static String fname, lname, studid, ktimereg,kkchildref,mtest3,digitsout,mtimeconcout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockedout);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
//        mtest = FirebaseDatabase.getInstance().getReference();
//       mtest1 = mtest.getDatabase().getReferenceFromUrl("https://masfirebaseproject-df6e3.firebaseio.com/UserDetails");
//       Map<String, String> map = dataSnapshot.getValue(Map.class);

        //fname = mtest1.getKey();
        // Log.d("Tag", fname.toString());


        tvnameout = findViewById(R.id.tvnameout);
        tvstudidout = findViewById(R.id.tvstudidout);
        tvsosotimeout = findViewById(R.id.tvsosotimeout);
        //etdigitsout = findViewById(R.id.etdigitsout);
        tvtextout = findViewById(R.id.tvtextout);
        kkchildref = Registration2.kchildref;
       // digitsout = etdigitsout.getText().toString().trim();


        mref = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/UserDetails/"+Clockout.digitsout);
//        if (mref.toString().endsWith(studid))
        Log.d("Tag", mref.toString());


        mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void  onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                        Map<String, String> map = dataSnapshot.getValue(Map.class);
                        if (dataSnapshot.exists()) {
                    fname = map.get("First Name");
                    lname = map.get("Last Name");
                    studid = map.get("Student ID");

                    Log.d("Tag", "First Name: " + fname);
                    Log.d("Tag", "Student ID" + studid);

                    progressDialog.dismiss();
                    tvnameout.setText("Hello " + fname + ",");
                    tvstudidout.setText(studid);

                    tvtextout.setText("You clocked out at:");
                    tvsosotimeout.setText(Clockout.timeout);

                    mtimeconcout = Clockout.digitsout+"  "+Clockin.datein;
                    mtimeout = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/Time Register/" +mtimeconcout);
                    Log.d("tag",mtimeout.toString());
//                    mtimeout.child("First Name").setValue(fname);
//                    mtimeout.child("Last Name").setValue(lname);
//                    mtimeout.child("Student ID").setValue(studid);
                    mtimeout.child("Time Out").setValue(Clockout.timeout);
                }

                else {
                    startActivity( new Intent(Clockedout.this,MainActivity.class));
                    Toast.makeText(Clockedout.this, "User not registered, Register user first",Toast.LENGTH_LONG).show();
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
        Log.d("Tag", Clockout.timeout);
    }
}
