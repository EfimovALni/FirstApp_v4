package cz.firstapp.firstapp_v4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.firstapp.firstapp_v4.apiMy.ApisMy;
import cz.firstapp.firstapp_v4.internet.Api;
import cz.firstapp.firstapp_v4.internet.ApiClient;
import cz.firstapp.firstapp_v4.model.DataResponse;
import cz.firstapp.firstapp_v4.model.Initial_screen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Res ";

    List<String> listApi = new ArrayList<String>();
    Api api;
    String action = "action";   //For asking server
    //    String value = "checkVersion";
    String value = "downloadConfiguration"; //For asking server
    List<Initial_screen> mDataFromServer = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterRecyclerView adapter;

    public ArrayList<String> nameIcons = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Declaration 'RecyclerView + GridLayoutManager' with 3 columns' */
        recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        drawingInterface();
        downloadApis();
    }

    private void downloadApis() {
        api = ApiClient.getClientLocal().create(Api.class);
        api.getApi().enqueue(new Callback<ApisMy>() {
            @Override
            public void onResponse(Call<ApisMy> call, Response<ApisMy> response) {
                if (response.isSuccessful()) {
                    final ApisMy apisMy = response.body();

                    Log.e("APIs ", " " +  listApi.toString());
                } else {
                    Log.e("response ", "is not Success. BODY = " + response.body());
                }

            }

            @Override
            public void onFailure(Call<ApisMy> call, Throwable t) {
                Log.e("Error from ", " " + t);
            }
        });
    }


    /**
     * Get JSON data from Server + transmit data to adapter in RecyclerView ......... Start
     */
    public void drawingInterface() {
//        api = ApiClient.getClient().create(Api.class); // FOR REMOTE SERVER!
//        api.getData(request_Data_For_POST()).enqueue(new Callback<DataResponse>() { // FOR REMOTE SERVER!

        api = ApiClient.getClientLocal().create(Api.class);
        api.getDataMainScreen().enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                final DataResponse dataResponse = response.body();
                /** 1. Drawing all screen
                 * TODO: 2. Compare version on server and current version of app, if it difference, we change appearance of app
                 * .....Start */
                for (int i = 0; i < dataResponse.getConfiguartion().getInitialScreen().size(); i++) {
                    mDataFromServer.add(new Initial_screen(
                            dataResponse.getConfiguartion().getInitialScreen().get(i).getApi(),
                            dataResponse.getConfiguartion().getInitialScreen().get(i).getText(),
                            dataResponse.getConfiguartion().getInitialScreen().get(i).getIcon()
                    ));
                    nameIcons.add(dataResponse.getConfiguartion().getInitialScreen().get(i).getText());

                    /** Fill List<> of APIs */
                        listApi.add(dataResponse.getConfiguartion().getInitialScreen().get(i).getApi());
                }

                System.out.println("Size list of APIs: " + listApi.size() + "\n\t" + listApi);


                /** Transmitting data to Adapter RecyclerView   */
                adapter = new AdapterRecyclerView(MainActivity.this, mDataFromServer);
                recyclerView.setAdapter(adapter);

                /* Compare version on server and current version of app, if it difference, we change appearance of app .....Start */
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Log.e("Error from ", "api.getData(request_Data_For_POST())... " + t);
            }
        });
    }


    /** Get JSON data from Server ....................................................... End */


    /**
     * Method for filling requests according the API of website,  POST ....................... Start
     */
    public Map<String, String> request_Data_For_POST() {
        final Map<String, String> parametersForServer = new HashMap<>();
        parametersForServer.put(action, value);
        return parametersForServer;
    }
    /*Method for filling requests according the API of website,  POST ....................... End */
}
