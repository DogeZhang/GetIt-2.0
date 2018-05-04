package com.getit.zhang.getit_20.API.Service;

import com.getit.zhang.getit_20.API.Model.Login;
import com.getit.zhang.getit_20.API.Model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by haha on 2018/5/4.
 */

public interface UserClient {
    @POST("login")
    Call<User> login(@Body Login login);

    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String autoToken );
}
