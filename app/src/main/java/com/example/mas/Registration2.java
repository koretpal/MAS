package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class Registration2 extends AppCompatActivity {
    private TextView tvprevious;
    public Spinner spstate;
    public Button btnregister;
    public EditText etfname2, etlname2, etstudid2,etaddress2, etemail2, etphone2;
    public RadioGroup rggender2;
    public ImageView imgfg,imgbback;
    public RadioButton rbmale2, rbfemale2;
    public static DatabaseReference mdataRef, mdataReff;


    public static String fname2,lname2, studid2, address2, email2, phone2, gender2, male2, female2,
            state2, sgender2, kchildref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration2);
        mdataRef = FirebaseDatabase.getInstance().getReference().child("UserDetails");


        final Registration etf = new Registration();

        tvprevious = findViewById(R.id.tvprevious);
        spstate = findViewById(R.id.spstate);
        imgfg = findViewById(R.id.imgfp);
        btnregister = findViewById(R.id.btnregsiter);
        imgbback = findViewById(R.id.imgbback);
        state2 = spstate.getSelectedItem().toString();

        imgbback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Registration2.this, Registration.class));
            }
        });

        imgfg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity( new Intent(Settings.ACTION_SECURITY_SETTINGS));
            }
        });
        tvprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration2.this, Registration.class));

            }
        });


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state2 = spstate.getSelectedItem().toString();

              if (state2.equalsIgnoreCase("Select State of Origin")){
                  Toast.makeText(getApplicationContext(), "Please select a valid state", Toast.LENGTH_LONG).show();
              }


              else {
                  etfname2 = Registration.etfname;
                  fname2 = Registration.fname;
                  lname2 = Registration.lname;
                  studid2 = Registration.studid;
                  address2 = Registration.address;
                  email2= Registration.email;
                  phone2 = Registration.phone;
                  rbmale2 =Registration.rbmale;
                  rbfemale2 = Registration.rbfemale;
                  sgender2 = Registration.sgender;
                  state2 = spstate.getSelectedItem().toString();

                 // DatabaseReference childRef = mdataRef.push();
                  //                  kchildref = childRef.getKey();
                  mdataReff = mdataRef.child(studid2);
                  mdataReff.child("First Name").setValue(fname2);
                  mdataReff.child("Last Name").setValue(lname2);
                  mdataReff.child("Student ID").setValue("IGHub/"+studid2);
                  mdataReff.child("Address").setValue(address2);
                  mdataReff.child("Email").setValue(email2);
                  mdataReff.child("Phone").setValue(phone2);
                  mdataReff.child("Gender").setValue(sgender2);


//                  childRef.child("State").setValue(state2);

//                  kchildref = childRef.getKey();
//                  childRef.child("First Name").setValue(fname2);
//                  childRef.child("Last Name").setValue(lname2);
//                  childRef.child("Student ID").setValue("IGHub"+studid2);
//                  childRef.child("Address").setValue(address2);
//                  childRef.child("Email").setValue(email2);
//                  childRef.child("Phone").setValue(phone2);
//                  childRef.child("Gender").setValue(sgender2);
//                  childRef.child("State").setValue(state2);
//
                  Toast.makeText(getApplicationContext(), "Registeration Successful", Toast.LENGTH_LONG).show();


                  Registration.etfname.getText().clear();
                  Registration.etlname.getText().clear();
                  Registration.etstudid.getText().clear();
                  Registration.etaddress.getText().clear();
                  Registration.etemail.getText().clear();
                  Registration.etphone.getText().clear();
                  Registration.rbmale.setChecked(false);
                  Registration.rbfemale.setChecked(false);
                  spstate.setSelection(0);;

              }


            }
        });
    }


}
