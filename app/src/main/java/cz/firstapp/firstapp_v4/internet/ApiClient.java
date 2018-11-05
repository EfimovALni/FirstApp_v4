package cz.firstapp.firstapp_v4.internet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

}