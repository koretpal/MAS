package com.example.mas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.Map;

import static com.example.mas.R.string.drawer_item_settings;

public class Clockedout extends AppCompatActivity {
    public static TextView tvnameout, tvstudidout,tvtextout, tvsosotimeout, tvsosodateout;
    public static Firebase mref, mtimeout, mdateout, mdatestudid;
    public  static EditText etdigitsout;
    DatabaseReference mtimereg, mtest, mtest1, mtest11;
    FirebaseDatabase mtest2, mtest22;
    DataSnapshot dataSnapshot;
    ProgressDialog progressDialog;
    public static String fname, lname, studid, ktimereg,kkchildref,mtest3,digitsout,mtimeconcout;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nvv;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockedout);
        Firebase.setAndroidContext(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
//        mtest = FirebaseDatabase.getInstance().getReference();
//       mtest1 = mtest.getDatabase().getReferenceFromUrl("https://masfirebaseproject-df6e3.firebaseio.com/UserDetails");
//       Map<String, String> map = dataSnapshot.getValue(Map.class);

        //fname = mtest1.getKey();
        // Log.d("Tag", fname.toString());

        dl = (DrawerLayout)findViewById(R.id.lcio);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open,R.string.Close);
        //  t.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(t);
        t.syncState();




        Toolbar toolbar = (Toolbar) findViewById(R.id.mytoolbar);
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

        nvv = (NavigationView)findViewById(R.id.nvv);
        nvv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
                    mdateout = new Firebase("https://masfirebaseproject-df6e3.firebaseio.com/Time Register/" +Clockin.datein);
                    mtimeout = mdateout.child(Clockout.digitsout);
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
