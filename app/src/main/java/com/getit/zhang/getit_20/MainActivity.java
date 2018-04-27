package com.getit.zhang.getit_20;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_laytou);

        //register all UI
        ButterKnife.bind(this);

        // for status
        fullScreen(this);


    }

    /**
     *  @param activity
     */
    private void fullScreen(Activity activity){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                Window window = activity.getWindow();
                View decorView = window.getDecorView();

                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            }else{
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
//                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;

                window.setAttributes(attributes);
            }
        }
    }


}
