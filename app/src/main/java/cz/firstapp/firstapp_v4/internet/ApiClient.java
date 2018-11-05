package cz.firstapp.firstapp_v4.internet;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import cz.firstapp.firstapp_v4.BuildConfig;
import cz.firstapp.firstapp_v4.model.DataResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The {@code ApiClient} class which  build URL using 'Retrofit' library.
 *
 * @author Alexander Efimov
 * @version 0.0.1
 * @date last changed: 14.10.2018
 */

public class ApiClient {
    /**
     * Code for server
     */
    private static final String BASED_URL = "https://www.videotechnik.cz/";

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASED_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;


    }



  /*  @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASED_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        Api api = retrofit.create(Api.class);
        Call<DataResponse> call = api.getData();

        try {
            Response<DataResponse> result = call.execute();
//            api.getData(request_Data_For_POST()).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}