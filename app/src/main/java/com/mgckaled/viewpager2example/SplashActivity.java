package com.mgckaled.viewpager2example;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    // Initialize Members Class
    ImageView ivTop, ivHeart, ivBottom;
    TextView textView;

    CharSequence charSequence;
    int index;
    long delay = 200;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide toolbar
        getSupportActionBar().hide();

        // Assign variables
        ivTop = findViewById(R.id.iv_top);
        ivHeart = findViewById(R.id.iv_heart);
        ivBottom = findViewById(R.id.iv_bottom);
        textView = findViewById(R.id.splash_text_view);

        // Set Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Initialize top animation
        Animation animation_1  = AnimationUtils.loadAnimation(
                this, R.anim.top_wave);

        // Start top Animation
        ivTop.setAnimation(animation_1);

        //Initialize object animator
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                ivHeart,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        );

        // Set Duration
        objectAnimator.setDuration(500);
        // Set repeat count
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        // Set repeat mode
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        // Start animator
        objectAnimator.start();

        //Set animate text
        animatText("App da Fernanda");

        //Initialize top animation
        Animation animation_2  = AnimationUtils.loadAnimation(
                this, R.anim.bottom_wave);

        // Start top Animation
        ivBottom.setAnimation(animation_2);

        //Initialize Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Redirect to main activity
                startActivity(new Intent(SplashActivity.this,
                        MainActivity.class).
                        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                // Finish activity
                finish();
            }
        }, 5000);

    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // When runnable is run, set text
            textView.setText(charSequence.subSequence(0, index++));
            // Check condition
            if (index <= charSequence.length()) {
                // When index is equal to text lenght, run handler
                handler.postDelayed(runnable, delay);
            }
        }
    };

    // Create animated text method
    public void animatText(CharSequence cs) {
        // Set text
        charSequence = cs;
        // Clear index
        index = 0;
        // Clear Text
        textView.setText("");
        // Remove call back
        handler.removeCallbacks(runnable);
        // Run handler
        handler.postDelayed(runnable, delay);

    }

}