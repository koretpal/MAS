package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class LoginScreen extends AppCompatActivity {
    private ImageView aback;
    private EditText etstudentid, etpwd;
    private Button btnsignin;
    public static String studentid, pwd;

    public TextView tvnamein, tvstudidin,tvtextin, tvsosotimein, tvsosodatein;
    public  Firebase mref, mtimein;
    DatabaseReference mtimereg, mtest, mtest1, mtest11;
    FirebaseDatabase mtest2, mtest22;
    DataSnapshot dataSnapshot;
    ProgressDialog progressDialog;
    public  String fname,IGstudid, lstudid, lpw,kkchildref,mtest3, mtimeconc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        Firebase.setAndroidContext(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        aback = findViewById(R.id.aback);
        etstudentid = findViewById(R.id.etstudentid);
        etpwd = findViewById(R.id.pwd);
        btnsignin = findViewById(R.id.btnsignin);


        aback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),Welcomescreen.class));
            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentid = etstudentid.getText().toString().trim();
                mref = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/UserDetails/"+studentid);
                IGstudid = "IGHub/"+studentid;
                pwd = etpwd.getText().toString().trim();


                if (studentid.isEmpty() || studentid.length()>3)
                {
                    etstudentid.setError("Invalid Student ID");
                }
                else if (pwd.isEmpty())
                {
                    etpwd.setError("Invalid Password");
                }
                else
                {
                    progressDialog.show();
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void  onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                            Map<String, String> map = dataSnapshot.getValue(Map.class);
                           lstudid = map.get("Student ID");
                            lpw = map.get("Password");
                            Log.d("Tag", lstudid+ " " +lpw+ " " +studentid+ " " + pwd);
                            if (IGstudid.equals(lstudid) && pwd.equals(lpw) ) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Sign in successful", Toast.LENGTH_LONG).show();
                                startActivity( new Intent(getApplicationContext(), MainActivity.class));
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "User not registered", Toast.LENGTH_LONG).show();
                            }

                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                }
            }
        });



    }
}
