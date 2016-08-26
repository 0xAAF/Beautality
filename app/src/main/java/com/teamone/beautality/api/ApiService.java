package com.teamone.beautality.api;

import com.teamone.beautality.models.request.ListRequest;
import com.teamone.beautality.models.request.LoginRequest;
import com.teamone.beautality.models.response.ListResponse;
import com.teamone.beautality.models.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

        @Headers("Content-type: application/json")
        @POST(VERSION +"/login")
        Call<LoginResponse> postLogin(@Body LoginRequest loginRequest);

        @Headers("Content-type: application/json")
        @POST(VERSION +"/data/find")
        Call<ListResponse> postList(@Body ListRequest listRequest);

    }
}
