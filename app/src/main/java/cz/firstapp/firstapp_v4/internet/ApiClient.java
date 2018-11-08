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
//    private static final String BASED_URL_LOCAL = "http://localhost:4567/";
//    private static final String BASED_URL_LOCAL = "http://10.5.134.240:4567/"; // UTB
    private static final String BASED_URL_LOCAL = "http://10.100.0.27:4567/new/"; // Kolej

    public static Retrofit getClient() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASED_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    public static Retrofit getClientLocal() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASED_URL_LOCAL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

}