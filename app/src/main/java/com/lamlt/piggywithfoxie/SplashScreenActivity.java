package com.lamlt.piggywithfoxie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreenActivity extends BaseActivityFullScreen {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash_screen);
        TextView tv = findViewById(R.id.tv);
        onAnimateLogo(tv);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, RegisterScreenActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }

    private void onAnimateLogo(final View view) {
        // Load the animation
        Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);
        //val animationDuration = 0.2 * 1000
        //myAnim.duration = animationDuration.toLong()

        // Use custom animation interpolator to achieve the bounce effect
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20.0);

        myAnim.setInterpolator(interpolator);

        // Animate the button
        view.startAnimation(myAnim);

        // Run button animation again after it finished
        myAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onAnimateLogo(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}

