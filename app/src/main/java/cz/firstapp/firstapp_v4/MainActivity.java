package cz.firstapp.firstapp_v4;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import cz.firstapp.firstapp_v4.internet.Api;
import cz.firstapp.firstapp_v4.internet.ApiClient;
import cz.firstapp.firstapp_v4.model.Initial_screen;
import cz.firstapp.firstapp_v4.model.RootMainScreen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "myLogs";

    List<String> listApi = new ArrayList<String>();
    Api api;
    LoginActivity loginActivity;

    String action = "action";   //For asking server
    String api_ = "api";   //For asking server
    //    String value = "checkVersion";
    String valueDownloadConfiguration = "downloadConfiguration";
    String valueCheckVersion = "checkVersion";
    String valueDownloadMainScreen = "downloadMainScreen";
    String valueDownloadSecondScreen = "downloadSecondScreen";
    String valueDownloadApiScreen = "downloadApiScreen";
    String valueSecurity = "Security";

    List<Initial_screen> mDataFromServer = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterRecyclerView adapter;
    public ArrayList<String> nameIcons = new ArrayList<>();
    public List<String> listNameBtn = new ArrayList<>();
    public List<Integer> listNameBtnSize = new ArrayList<>();
    public boolean flagItem = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isConnectedToInternet();

//        Checking the Internet connection
        if (!isConnectedToInternet(MainActivity.this)) {
            builderDialog(MainActivity.this).show();
        } else {
            calculateNoOfColumns(MainActivity.this);
            int mNoOfColumns = calculateNoOfColumns(getApplicationContext());


            /** Declaration 'RecyclerView + GridLayoutManager' with 3 columns' */
            recyclerView = (RecyclerView) findViewById(R.id.rv_main);

//            if (getScreenOrientation() == true) {
            if (true) {
                recyclerView.setLayoutManager(
                        new GridLayoutManager(this, mNoOfColumns, 1, false));
            } else {
                recyclerView.setLayoutManager(
                        new GridLayoutManager(this, mNoOfColumns, 1, false));
            }

//        recyclerView.setLayoutManager(
//                new GridLayoutManager(this,  4, 1, false));

            drawingInterface();
//            downloadApis();
        }
    }

    private void isConnectedToInternet() {
    }

    /**
     * Method for checking connection with the Internet ...................................... Start
     */
    public boolean isConnectedToInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (mobile != null && mobile.isConnectedOrConnecting() || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        } else
            return false;
    }
    /* Method for checking connection with the Internet .................................. Start */

    /**
     * Method for printing dialog window if no the Internet.   ............................... Start
     */
    public AlertDialog.Builder builderDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.TitleNotInternetConnection);
        builder.setMessage(R.string.MessageNotInternetConnection);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder;
    }
    /* Method for printing dialog window if no the Internet ................................. End */


    // Высчитивыет сколько кнопок можно в ширину разместить. Нужно потому что у нас блядь всё динамичесмкое. И хуй ты это укажешь в *.xml
    public static int calculateNoOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
//        TODO: Но. Лучше не от количества пикселей, а от физ размера экрана.  Ибо сам прикинь, два дисплея с диагональю 5 дюймов.
        int noOfColumns = (int) (dpWidth / 130); // Взял 120 - так как ширина иконки у меня 120px
        return noOfColumns;
    }


    // Detected rotation screen
    private boolean getScreenOrientation() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            return true; //"Портретная ориентация";
        else
            return false;
//        (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
//            return false; // "Альбомная ориентация";
    }


//    private void downloadApis() {
//        api = ApiClient.getClientLocal().create(Api.class);// FOR local SERVER!
//        api.getSecurity().enqueue(new Callback<RootSecurity>() {    // FOR local SERVER!
//            @Override
//            public void onResponse(Call<RootSecurity> call, Response<RootSecurity> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<RootSecurity> call, Throwable t) {
//
//            }
//        });


//        api.getSecurity().enqueue(new Callback<RootSecurity>() {
//
////        api = ApiClient.getClient().create(Api.class); // FOR REMOTE SERVER!
////        api.getApi(requestDataConfiguration()).enqueue(new Callback<MainScreen>() { // FOR REMOTE SERVER!
//            @Override
//            public void onResponse(Call<ApisMy> call, Response<ApisMy> response) {
//                if (response.isSuccessful()) {
//                    final ApisMy apisMy = response.body();
//
//                    Log.e("APIs ", " " + listApi.toString());
//                } else {
//                    Log.e("response ", "is not Success. BODY = " + response.body());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApisMy> call, Throwable t) {
//                Log.e("Error from ", " " + t);
//            }
//        });
//    }


    /**
     * Get JSON data from Server + transmit data to adapter in RecyclerView ......... Start
     */
    public void drawingInterface() {
        api = ApiClient.getClient().create(Api.class); // FOR REMOTE SERVER!
        api.getData(requestDataMainScreen()).enqueue(new Callback<RootMainScreen>() {
            @Override
            public void onResponse(Call<RootMainScreen> call, Response<RootMainScreen> response) {
                final RootMainScreen rootMainScreen = response.body();
                /** 1. Drawing all screen
                 * TODO: 2. Compare version on server and current version of app, if it difference, we change appearance of app
                 * .....Start */
                for (int i = 0; i < rootMainScreen.getMainScreen().getConfiguartion().getInitialScreen().size(); i++) {
                    mDataFromServer.add(new Initial_screen(
                            rootMainScreen.getMainScreen().getConfiguartion().getInitialScreen().get(i).getApi(),
                            rootMainScreen.getMainScreen().getConfiguartion().getInitialScreen().get(i).getText(),
                            rootMainScreen.getMainScreen().getConfiguartion().getInitialScreen().get(i).getIcon()
                    ));
                    nameIcons.add(rootMainScreen.getMainScreen().getConfiguartion().getInitialScreen().get(i).getText());

                    /** Fill List<> of APIs */
                    listApi.add(rootMainScreen.getMainScreen().getConfiguartion().getInitialScreen().get(i).getApi());
                    listNameBtn.add(rootMainScreen.getMainScreen().getConfiguartion().getInitialScreen().get(i).getText());

                }
                Log.e("-----> ", "" + listNameBtn);
                int max = 0;
                for (int i = 0; i < listApi.size(); i++) {
                    int iLenTemp = listApi.get(i).length();
                    listNameBtnSize.add(iLenTemp);
                }
                Log.e(".... ", " " + listNameBtnSize);

                System.out.println(getMax(listNameBtnSize));


                /** Transmitting data to Adapter RecyclerView   */
                adapter = new AdapterRecyclerView(MainActivity.this, mDataFromServer);
                recyclerView.setAdapter(adapter);

                /* Compare version on server and current version of app, if it difference, we change appearance of app .....Start */
            }

            @Override
            public void onFailure(Call<RootMainScreen> call, Throwable t) {
                Log.e("ERROR from ", "api.getData(requestDataMainScreen()).en... " + t);
            }
        });

//        api.getData(requestDataMainScreen()).enqueue((new Callback<MainScreen>() { // FOR REMOTE SERVER!
//
////        api = ApiClient.getClientLocal().create(Api.class);   // FOR local SERVER!
////        api.getDataMainScreen().enqueue(new Callback<MainScreen>() {    // FOR local SERVER!
//            @Override
//            public void onResponse(Call<MainScreen> call, Response<MainScreen> response) {
//                final MainScreen dataResponse = response.body();
//                /** 1. Drawing all screen
//                 * TODO: 2. Compare version on server and current version of app, if it difference, we change appearance of app
//                 * .....Start */
//                for (int i = 0; i < dataResponse.getConfiguartion().getInitialScreen().size(); i++) {
//                    mDataFromServer.add(new Initial_screen(
//                            dataResponse.getConfiguartion().getInitialScreen().get(i).getApi(),
//                            dataResponse.getConfiguartion().getInitialScreen().get(i).getText(),
//                            dataResponse.getConfiguartion().getInitialScreen().get(i).getIcon()
//                    ));
//                    nameIcons.add(dataResponse.getConfiguartion().getInitialScreen().get(i).getText());
//
//                    /** Fill List<> of APIs */
//                    listApi.add(dataResponse.getConfiguartion().getInitialScreen().get(i).getApi());
//                    listNameBtn.add(dataResponse.getConfiguartion().getInitialScreen().get(i).getText());
//
//                }
//                Log.e("-----> ", "" + listNameBtn);
//                int max = 0;
//                for (int i = 0; i < listApi.size(); i++) {
//                    int iLenTemp = listApi.get(i).length();
//                    listNameBtnSize.add(iLenTemp);
//                }
//                Log.e(".... ", " " + listNameBtnSize);
//
//                System.out.println(getMax(listNameBtnSize));
//
//
//                /** Transmitting data to Adapter RecyclerView   */
//                adapter = new AdapterRecyclerView(MainActivity.this, mDataFromServer);
//                recyclerView.setAdapter(adapter);
//
//                /* Compare version on server and current version of app, if it difference, we change appearance of app .....Start */
//            }
//
//            @Override
//            public void onFailure(Call<MainScreen> call, Throwable t) {
//                Log.e("ERROR from ", "api.getData(requestDataMainScreen()).en... " + t);
//            }
//        });
    }

    // TODO: Костыль, ИСПРАВИТЬ! Функция возвращает разницу между длинным названием кнокуи и более коротким. Нужно что бы забить пробелами. ЧТо бы на галвном экране не было полосы...
    // Function to find maximum value in an unsorted list in Java
    public static Integer getMax(List<Integer> list) {
        if (list == null || list.size() == 0)
            return Integer.MIN_VALUE;

        return Collections.max(list);
    }

    // TODO: Костыль, ИСПРАВИТЬ! Функция возвращает разницу между длинным названием кнокуи и более коротким. Нужно что бы забить пробелами. ЧТо бы на галвном экране не было полосы...
    public static Integer getMaxMinusMin(List<Integer> list) {
        if (list == null || list.size() == 0)
            return Integer.MIN_VALUE;

        return Collections.max(list);
    }


    /**
     * Get JSON data from Server ....................................................... End
     */


    /* Method for filling requests according the API of website,  POST ....................... Start */
    public Map<String, String> requestDataMainScreen() {
        final Map<String, String> parametersForServer = new HashMap<>();
        parametersForServer.put(action, valueDownloadMainScreen);
        return parametersForServer;
    }
    /*Method for filling requests according the API of website,  POST ....................... End */


    /* Method for filling requests according the API of website,  POST ....................... Start */
    public Map<String, String> requestdownloadApiScreen() {
        final Map<String, String> parametersForServer = new HashMap<>();
        parametersForServer.put(action, valueDownloadSecondScreen);
        parametersForServer.put(api_, valueSecurity);
        return parametersForServer;
    }
    /*Method for filling requests according the API of website,  POST ....................... End */


    /**
     * Methods for MenuIem
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    public void onAbout(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Application 'Ticket' \n v.: 4.0.1", Toast.LENGTH_SHORT).show();
    }

    /**
     * Тут передаю флаг null, через интент в другую активность. И там уже исходя из флага равного null, очищаем данные о логине и пароле
     */
    public void onSingOut(MenuItem item) {
        Intent intentLogOut = new Intent(MainActivity.this, LoginActivity.class);
        String flag = null;
        intentLogOut.putExtra("clearLogInDataFlag", flag);
        intentLogOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentLogOut);
        this.finish();
    }


    public void onTest(MenuItem item) {
        Toast.makeText(getApplicationContext(), "Was pressed \n" + item.getTitle().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.exit);
        builder.setMessage(R.string.exit_message);

        builder.setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.show();
    }


    /*@Override
    public Object onRetainCustomNonConfigurationInstance() {
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d(LOG_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(LOG_TAG, "onRestoreInstanceState");
    }*/
}


























