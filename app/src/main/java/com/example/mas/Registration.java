package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.Map;

public class Registration extends AppCompatActivity {
    public static TextView tvnext;
    public static EditText etfname, etlname, etstudid,etaddress, etemail, etphone;
    public static RadioGroup rggender;
    public static RadioButton rbmale, rbfemale;
    public static ImageView imgback;

    public static String fname,lname, studid, address, email, phone, gender, male, female, fbstudid, state, sgender;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etfname = findViewById(R.id.etfn);
        etlname = findViewById(R.id.etln);
        etstudid = findViewById(R.id.etstudid);
        etaddress = findViewById(R.id.etaddress);
        etemail = findViewById(R.id.etemail);
        etphone = findViewById(R.id.etphone);
        rggender = findViewById(R.id.rggender);
        rbmale = findViewById(R.id.rbmale);
        rbfemale = findViewById(R.id.rbfemale);
        tvnext = findViewById(R.id.tvnext);
        imgback = findViewById(R.id.imgback);
        fname = etfname.getText().toString().trim();
        lname = etlname.getText().toString().trim();
        studid = etstudid.getText().toString().trim();
        address = etaddress.getText().toString().trim();
        email = etemail.getText().toString().trim();
        phone = etphone.getText().toString().trim();
        male = rbmale.getText().toString().trim();
        female = rbfemale.getText().toString().trim();


        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(Registration.this, MainActivity.class));
            }
        });
        tvnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = etfname.getText().toString().trim();
                lname = etlname.getText().toString().trim();
                studid = etstudid.getText().toString().trim();
                address = etaddress.getText().toString().trim();
                email = etemail.getText().toString().trim();
                phone = etphone.getText().toString().trim();
                male = rbmale.getText().toString().trim();
                female = rbfemale.getText().toString().trim();




                if (TextUtils.isEmpty(fname)){
                    etfname.setError("First name cannot be empty");
                }
                else if (lname.isEmpty()){
                    etlname.setError("Last name cannot be empty");
                }
                else if (studid.isEmpty()){
                    etstudid.setError("Student id cannot be empty");
                }


                else if (address.isEmpty()){
                    etaddress.setError("Address cannot be empty");
                }
                else if (email.isEmpty()){
                    etemail.setError("Email cannot be empty");
                }
                else if (phone.isEmpty()){
                    etphone.setError("Phone cannot be empty");
                }
                else if (!rbmale.isChecked() && !rbfemale.isChecked()) {
                    rbmale.setError("Choose Gender");
                }
                 else if (rbmale.isChecked()){
                        sgender = male;
                    startActivity(new Intent(Registration.this, Registration2.class));
                    }
                    else if (rbfemale.isChecked()) {
                    sgender = female;
                    startActivity(new Intent(Registration.this, Registration2.class));
                }

            }
        });
    }
}
