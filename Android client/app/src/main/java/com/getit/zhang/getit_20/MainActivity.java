package com.getit.zhang.getit_20;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.getit.zhang.getit_20.API.Model.Login;
import com.getit.zhang.getit_20.API.Model.User;
import com.getit.zhang.getit_20.API.Service.UserClient;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    UserClient userClient = retrofit.create(UserClient.class);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_laytou);

        //register all UI
        ButterKnife.bind(this);

        // for status
        fullScreen(this);

    }
    private static String token;

    @BindView(R.id.loginAcount)
    EditText loginAcountText;
    @BindView(R.id.loginPassword)
    EditText loginPassText;
    @OnClick(R.id.loginButton)
    public void LoginClick(){
        Login login  = new Login(loginAcountText.getText().toString()
                ,loginPassText.getText().toString());

        Call<User> call= userClient.login(login);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this,response.body().getToken(),Toast.LENGTH_SHORT).show();
                    token = response.body().getToken();
                }else{
                    Toast.makeText(MainActivity.this,"login no correct :(",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"error:(",Toast.LENGTH_SHORT).show();
            }
        });
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
