package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static android.os.Build.VERSION_CODES.M;
import static android.os.SystemClock.sleep;

public class Welcomescreen extends AppCompatActivity {
    private TextView signin;
    private Button btncreateacct;
    OnSwipeTouchListener onSwipeTouchListener;

    //  private TouchableLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);
        signin = findViewById(R.id.signin);
        btncreateacct = findViewById(R.id.btncreateacct);

        onSwipeTouchListener = new OnSwipeTouchListener(this, findViewById(R.id.linearLayout));




//        layout = findViewById(R.id.linearLayout);
//
//        layout.setOnTouchListener(new OnSwipeTouchListener(Welcomescreen.this) {
//            @Override
//            public void onSwipeLeft() {
//                super.onSwipeLeft();
//              //  Toast.makeText(MainActivity.this, "Swipe Left gesture detected", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(getApplicationContext(), Description.class));
//            }
//            @Override
//            public void onSwipeRight() {
//                super.onSwipeRight();
//               // Toast.makeText(MainActivity.this, "Swipe Right gesture detected", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//



//        Thread background = new Thread() {
//            public void run() {
//                try {
//                    sleep(5*1000);
//
//                    startActivity( new Intent(getApplicationContext(), Description.class));
//                    finish();
//                }
//                catch ( Exception e){}
//            }
//        };
//                background.start();




        btncreateacct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent (getApplicationContext(), Registration.class));
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), LoginScreen.class));
            }
        });
    }
    public class OnSwipeTouchListener implements View.OnTouchListener {
        private final GestureDetector gestureDetector;
        Context context;
        OnSwipeTouchListener(Context ctx, View mainView) {
            gestureDetector = new GestureDetector(ctx, new GestureListener());
            mainView.setOnTouchListener(this);
            context = ctx;
        }
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }
        public class GestureListener extends
                GestureDetector.SimpleOnGestureListener {
            private static final int SWIPE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean result = false;
                try {
                    float diffY = e2.getY() - e1.getY();
                    float diffX = e2.getX() - e1.getX();
                    if (Math.abs(diffX) > Math.abs(diffY)) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                onSwipeRight();
                            } else {
                                onSwipeLeft();
                            }
                            result = true;
                        }
                    }
                    else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            onSwipeBottom();
                        } else {
                            onSwipeTop();
                        }
                        result = true;
                    }
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
                return result;
            }
        }
        public void onSwipeRight() {
          //  Toast.makeText(context, "Swiped Right", Toast.LENGTH_SHORT).show();
           // this.onSwipe.swipeRight();
        }
        void onSwipeLeft() {
          //  Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Welcomescreen.this, Description.class);
            startActivity(intent);
            //this.onSwipe.swipeLeft();
        }
        void onSwipeTop() {
            //Toast.makeText(context, "Swiped Up", Toast.LENGTH_SHORT).show();
           // this.onSwipe.swipeTop();
        }
        void onSwipeBottom() {
            //Toast.makeText(context, "Swiped Down", Toast.LENGTH_SHORT).show();
            //this.onSwipe.swipeBottom();
        }
//         interface onSwipeListener {
//            void swipeRight();
//            void swipeTop();
//            void swipeBottom();
//            void swipeLeft();
//        }
//        onSwipeListener onSwipe;
    }



}


