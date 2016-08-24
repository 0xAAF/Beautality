package com.teamone.beautality.api;

import com.teamone.beautality.models.response.CompaniesItemResponse;
import com.teamone.beautality.models.response.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by oshhepkov on 20.08.16.
 */
public class ApiService {

    private static final String DOMAIN = "https://api.scorocode.ru";
    private static final String VERSION = "/api/v1";

    private Api mApi;

    public Api getApi() {
        if (mApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(DOMAIN)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mApi = retrofit.create(Api.class);
        }

        return mApi;
    }

    public interface Api {

        @GET("/beautality/service")
        Call<List<CompaniesItemResponse>> getProjectList();

        @GET("/beautality/service")
        Call<CompaniesItemResponse> getOwner(
                @Query("id") String id);
        @FormUrlEncoded


        @POST(VERSION +"/login")
        Call<LoginResponse> getLogin(
                @Field("app") String app,
                @Field("cli") String cli,
                @Field("email") String email,
                @Field("password") String passwordMd5);

/*
        @FormUrlEncoded
        @POST(VERSION + "/auth/login")
        Call<UserResponse> getAccess(@Field("grant_type") String grant_type,
                                     @Field("username") String username,
                                     @Field("password") String password);
                                     */
    }
}
