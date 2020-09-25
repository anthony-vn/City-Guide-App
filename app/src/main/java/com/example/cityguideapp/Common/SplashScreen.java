package com.example.cityguideapp.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguideapp.R;
import com.example.cityguideapp.User.UserDashbroad;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIMER = 5000;

    ImageView background_img;
    TextView tv_poweredBySimmular;
    Animation sideAnim, bottomAnim;
    SharedPreferences onBroadingScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        //Hooks
        background_img = findViewById(R.id.background_image);
        tv_poweredBySimmular = findViewById(R.id.powered_by_line);

        //Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        //Set Animation on elements
        background_img.setAnimation(sideAnim);
        tv_poweredBySimmular.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBroadingScreen = getSharedPreferences("onBroadingScreen", MODE_PRIVATE);
                boolean isFirstTime = onBroadingScreen.getBoolean("firstTime", true);
                if (isFirstTime) {

                    SharedPreferences.Editor editor = onBroadingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();


                    Intent intent = new Intent(getApplicationContext(), OnBroading.class);
                    startActivity(intent);
                    finish();

                }else{
                    Intent intent = new Intent(getApplicationContext(), UserDashbroad.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIMER);
    }
}