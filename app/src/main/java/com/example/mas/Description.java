package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;

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

import static android.os.SystemClock.sleep;

public class Description extends AppCompatActivity {
    private TextView signinn;
    private Button createacctt;
    OnSwipeTouchListener onSwipeTouchListener;
  //  View view;
    //LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        signinn = findViewById(R.id.signinn);
        createacctt = findViewById(R.id.createacctt);

        onSwipeTouchListener = new OnSwipeTouchListener(getApplicationContext(), findViewById(R.id.linearlayout2));


//        layout = findViewById(R.id.relativeLayout);
//        layout.setOnTouchListener(new OnSwipeTouchListener(Description.this) {
//            @Override
//            public void onSwipeLeft() {
//                super.onSwipeLeft();
//                //  Toast.makeText(MainActivity.this, "Swipe Left gesture detected", Toast.LENGTH_SHORT).show();
//
//            }
//            @Override
//            public void onSwipeRight() {
//                super.onSwipeRight();
//                startActivity(new Intent(getApplicationContext(),Welcomescreen.class));
//                // Toast.makeText(MainActivity.this, "Swipe Right gesture detected", Toast.LENGTH_SHORT).show();
//            }
//        });
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity( new Intent(Description.this, Welcomescreen.class));
//
//                finish();
//            }
//        }, 1000*1000);

//        Thread background = new Thread() {
//            public void run() {
//                try {
//                    sleep(5*1000);
//
//                    startActivity( new Intent(getApplicationContext(), Welcomescreen.class));
//                    finish();
//                }
//                catch ( Exception e){}
//            }
//        };
//        background.start();


        createacctt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Description.this, Registration.class);
                startActivity(intent);

            }
        });

        signinn.setOnClickListener(new View.OnClickListener() {
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
            gestureDetector = new GestureDetector(ctx, new OnSwipeTouchListener.GestureListener());
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
            startActivity(  new Intent(Description.this, Welcomescreen.class));
            // this.onSwipe.swipeRight();
        }
        void onSwipeLeft() {
            //  Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show();


            //this.onSwipe.swipeLeft();
        }
        void onSwipeTop() {
          //  Toast.makeText(context, "Swiped Up", Toast.LENGTH_SHORT).show();
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


