package com.example.mas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.KeyguardManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Map;
import java.util.Objects;

import static com.example.mas.R.string.drawer_item_settings;

public class Clockedin extends AppCompatActivity {
    public static TextView tvnamein, tvstudidin,tvtextin, tvsosotimein, tvsosodatein;
    public static Firebase mref, mtimein, mtimeinn, mdatein, mdatestudid;
            DatabaseReference mtimereg, mtest, mtest1, mtest11;
            FirebaseDatabase mtest2, mtest22;
            DataSnapshot dataSnapshot;
            ProgressDialog progressDialog;
    public static String fname, lname, studid, ktimereg,kkchildref,mtest3,mdate, mtimeconc;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    Toolbar toolbar;


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
       //fname = mtest1.getKey();
       // Log.d("Tag", fname.toString());
        dl = (DrawerLayout)findViewById(R.id.lci);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open,R.string.Close);
      //  t.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(t);
        t.syncState();




        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        setSupportActionBar(toolbar);


        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home);
        final SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName(drawer_item_settings);
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.clockin);
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.clockout);
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.logout);
//create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
               .withToolbar(toolbar)
                .addDrawerItems(
//                        item1,
//                        new PrimaryDrawerItem(),
//                        item2,
//                        new SecondaryDrawerItem().withName(drawer_item_settings)
                        item3,
                        item4,
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        if (item3.isSelected()){
                            startActivity(new Intent(getApplicationContext(), Clockin.class));
                        }
                        else if (item4.isSelected()){
                            startActivity(new Intent(getApplicationContext(), Clockout.class));
                        }
                        else if (item5.isSelected()){
                            startActivity(new Intent(getApplicationContext(), Welcomescreen.class));
                        }
                        return true;
                    }
                }).withHeader(R.layout.nav_header)
                .build();

//        Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);

//      getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.clockin:
                       startActivity(new Intent(getApplicationContext(), Clockin.class));break;
                    case R.id.clockout:
                        startActivity(new Intent(getApplicationContext(), Clockout.class));break;
                    case R.id.logout:
                        startActivity(new Intent(getApplicationContext(), Welcomescreen.class));break;
                    default:
                        return true;
                }


                return true;

            }
        });



        tvnamein = findViewById(R.id.tvnamein);
        tvstudidin = findViewById(R.id.tvstudidin);
        tvsosotimein = findViewById(R.id.tvsosotimein);
        tvsosodatein = findViewById(R.id.tvsosodatein);
        tvtextin = findViewById(R.id.tvtextin);
        kkchildref = Registration2.kchildref;


        mref = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/UserDetails/"+Clockin.digitsin);
//        if (mref.toString().endsWith(studid))
        Log.d("Tag", mref.toString());

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
                mdate = Clockin.datein;
                mdatein = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/Time Register/" +mdate);
                mtimein = mdatein.child(Clockin.digitsin);


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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}
