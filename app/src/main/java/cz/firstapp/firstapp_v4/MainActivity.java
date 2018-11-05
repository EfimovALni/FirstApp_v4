package cz.firstapp.firstapp_v4;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import cz.firstapp.firstapp_v4.internet.Api;
import cz.firstapp.firstapp_v4.internet.ApiClient;
import cz.firstapp.firstapp_v4.model.DataResponse;
import cz.firstapp.firstapp_v4.model.Initial_screen;
import retrofit2.Call;
import retrofit2.Response;

import static java.util.Objects.*;

//import android.R;
//import android.R.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Res ";

    Api api;
    ApiClient apiClient;

    String action = "action";   //  For asking server
    //    String value = "checkVersion";
    String value = "downloadConfiguration"; //  //  For asking server

    List<Initial_screen> mDataFromServer;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/**_______________________________________________________________________________________________
 *
 * Переделываю код для использовавания с 'RecyclerView + GridLayoutManager с тремя колонками'
 * _______________________________________________________________________________________________ */


        /*mDataFromServer = new ArrayList<>();
        mDataFromServer.add(new Initial_screen("#0000ff", "Security",
                "data:image/png;base64,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"
        ));
        mDataFromServer.add(new Initial_screen("#00ff00", "Notebooks",
                "data:image/png;base64,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"
        ));
        mDataFromServer.add(new Initial_screen("#ff0000", "Printers",
                "data:image/png;base64,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"
        ));
        mDataFromServer.add(new Initial_screen("#f0000f", "SIP phones",
                "data:image/png;base64,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"
        ));
        mDataFromServer.add(new Initial_screen("#0f00f0", "New Equipment",
                "data:image/png;base64,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"
        ));
        mDataFromServer.add(new Initial_screen("#fff000", "DMS system",
                "data:image/png;base64,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"
        ));
        mDataFromServer.add(new Initial_screen("#000fff", "Desktops",
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHgAAABkCAYAAABNcPQyAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAABRlJREFUeNrsnU9MI1Ucx6czU1pbaJfCHsiWhAMQOJjIxsQYPHjB4MWDu7oHL95WBI7GmGg8kPWkFz0QORhPnsToBaN742B0jSFwcImhmvqnoSE0UKalpfPH37O/xrrbIu3O63To95O8zNCEmeF9+nvv994M8wKO4yjg8qKiCiAYQDCAYADBAIIBBAMIhmAAwQCCgffobh4sEAg0/JiLyltwPuLmgM3bhjcKWrl/oMu80kwmMzw4OHjLNM2rlmVpdGEQfA6GYfw+Ojr6Fe2eUSnx1m4m+kJB5+bdpLoIDlQqlbd0Xb8Dba1BwfDn3t7e+9PT01/SjydUCizaaSeCXRdcLBYT4XD4Lu1fh672yeVynw8NDb1Nu8dUjqiUa5JbceZ6kkVyP4bcRyeRSNxMp9Ov0+4glXi73anqcvMyR3JvQo87JJPJV5eXl6/SboxKpJ0k1VXBJPdlaHFxDKuqscXFxedoN8SCW45i3eULutHo893d3fcODg7+0DTNhrbGTE1NzYlm+cHPh4eHp2mjid6PfVU8E8z9xYOp/z3KCNdFC06lyMkCeJivKXl6SHAoFEpy06x7HsH/M3ivcDaYh8vWez+lzYkivYMXabPks/+Y7/GnOpvM/p0n2rskC3RhooYqgGAAwQCCAQQDCAYQDCAYggEEAwgGEAwgGEAwgGAIBhAMIBhAMIBgAMEAggEEQzCAYADBAIIBBAMIBhAMwQCCAQSD3hNceykp6CDS39ERiUSmVldXry0sLPyqVF8F9JhS997FFt9RcWkxDOOGlKhy+SUoTQ9WKBR+UFXVJqE2bbGeXn0zqqpxXdcfbyL+3sDAwGtK9RVU+1SOu+Z1wvVEo9GnoBJJFoBgAMEAgiEYQDCAYADBAIJBdwh2HOc3VKm7nJyc3Ff+XQ3N9lSwZVnrUOIu2Wz2ZxYslkSwWv19V2825PP5iVgs9gu0uEOlUvmrr6/vBaX6lvwclQPxsWcLY8Xj8dTp6em7UOMOGxsbb3KzLBYyKXAUexfBfG83aBjGB9FodBmK2sO27fzOzs6dmZmZb1lujkuFcx1Ps2izv7//jVQqdatUKn0HXa1xeHj4xdra2isk9xulugLpMRezraCTtfoolT4q/SsrK8n5+fnrpmn+s7ysrCVmmy0s5QbpdPojKvfFAwsyH1aYnZ39nptki+WK1UcNpW5hylYjWJbgGmLFrlBd0RVJz2WVy+XPKCF5RpbgsbGxTzjZKbaTzV50pMnHLrPgcqNzddMTHRZXyCl3B9JWAQ8Gg0lZfwR1Oddqia1SXdyrKFFwbbzrSuTJjuCLTI64Mf5+UtO0H2UmPXT8Z1lsVqk+F+X4oc4uxVQlVdJtmccXq4BubW3NiYZCqT4ZqvmlbnwvmMbdiU6sWTwxMfEii41wPgHBnYASq9sk+Irs84inQjc3N5/mCI7SOX0Rxb7ug0X0hsPhVCcEC8Sz3ZRwie5ArKCapWsvdnud+TqCSe5LnZJbi+KlpaUrdcM+NNFSx2CWddTpc/JEjWietYAP/u/G1000nU81TfMnGsI80YnK2t/f/3RkZORDHteL4dLRow6XZNeZrvgbZ3t7+/nx8fF3gsHgDI1XpbVImUzm7uTk5DpPdog7OyXHB6tbex7BLnUz4j8WYzyEkfmlrc3M5Xlre/Kt7qK56I59UXkSIsj9o4wLqU0jnnEUexa9vSi4p/CsD/ZBl9Rz4LFZCAYQDCAYQDCAYADBAIJ7ib8FGAAOEcC5s/gZHAAAAABJRU5ErkJggg=="
        ));*/


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));

        api = ApiClient.getClient().create(Api.class);
        Call<DataResponse> call = api.getData(request_Data_For_POST());
        Log.e("---", call.toString());

        try {
            Response<DataResponse> response = call.execute();
            Log.e("Resrponse ", response.body().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


//        drawingInterface();
        AdapterRecyclerView adapter = new AdapterRecyclerView(this, mDataFromServer);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Get JSON data from Server ......................................................... Start                            TimeUnit.SECONDS.sleep(5);
     */


//    @Background
    public void drawingInterface() {
        api = ApiClient.getClient().create(Api.class);

//        new Thread(()->{ for (int i = 0; i < 3; i++) { Log.e("Thread ", String.valueOf(i)); } }).start(); //Поток отдетьный для теста
        Call<DataResponse> call = api.getData(request_Data_For_POST());


        try {
            Response<DataResponse> res = call.execute();



            Log.e("res", res.toString());
            Log.e("res", res.toString());
            Log.e("res", res.toString());
            Log.e("res", res.toString());
            Log.e("res", res.toString());
            Log.e("res", res.toString());
            Log.e("res", res.toString());
            Log.e("res", res.toString());
            Log.e("res", res.body().toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


//        api.getData(request_Data_For_POST()).enqueue(new Callback<DataResponse>() {
//
//            @Override
//            public void onResponse
//                    (Call<DataResponse> call, Response<DataResponse> response) {
//                /**
//                 * 1. Drawing all screen
//                 * 2. Compare version on server and current version of app, if it difference, we change appearance of app .....Start */
//                final DataResponse dataResponse = response.body();
//
//                mDataFromServer = new ArrayList<>();
//
//                Log.e("response ", response.toString());                                         /** For testing */
//                Log.e("response.body() ", response.body().toString());                           /** For testing */
//                Log.e("Size of array  ", String.valueOf(response.body().getConfiguartion().getInitialScreen().size()));                           /** For testing */
//                Log.e(" ", "");                                                        /** For testing */
//
//                for (int i = 0; i < dataResponse.getConfiguartion().getInitialScreen().size(); i++) {
////                    String str_color_raw = dataResponse.getConfiguartion().getInitialScreen().get(i).getColor();
////                    String str_text_raw = dataResponse.getConfiguartion().getInitialScreen().get(i).getText();
////                    String str_icon_raw = dataResponse.getConfiguartion().getInitialScreen().get(i).getIcon();
////
////                    Log.e("", "" + str_color_raw + " " + str_text_raw + " " + str_icon_raw);    /** For testing */
////
////
////                    /** Convert hex color value ( #ffffff ) to integer value */
////                    int str_color = Color.parseColor(str_color_raw);
////
////                    /** Preparing Base64 for Deserialize ico */
////                    String str_icon = str_icon_raw.replace("data:image/png;base64,", "");
////
////                    /** Here we give PNG from String*/ // TODO Декодирование картинки вынесено в "AdapterRecyclerView -> onBindViewHolder"
//////                            Bitmap bit_ico = decodeImg(str_icon);
////
////                    /** Declare new objects */
//////                    LinearLayout ll_colon_1_dynamical = new LinearLayout(this);
////
//////                    LinearLayout ll_child = new LinearLayout(this);
//////                    ImageView iv = new ImageView(this);
//////                    TextView tv = new TextView(this);
////
////                    /** Create new objects */
////
//////                    ll_child.setLayoutParams(new LinearLayout.LayoutParams(
//////                            LinearLayout.LayoutParams.WRAP_CONTENT,
//////                            LinearLayout.LayoutParams.WRAP_CONTENT
//////                    ));
////
//////                    ll_child.setOrientation(LinearLayout.VERTICAL);
////
////
//////                    /** Margin for linear layout */
//////                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ll_child.getLayoutParams();
//////                    layoutParams.topMargin = 5;
//////                    layoutParams.leftMargin = 5;
//////                    layoutParams.rightMargin = 5;
//////                    layoutParams.bottomMargin = 5;
////
//////                    ll_child.setId(i);
//////                    iv.setId(i);
//////                    tv.setId(i);
//////
////                    /** Filling fields of elements according the JSON. And change some parameters */
//////                    ll_child.setBackgroundColor(str_color);
//////                    iv.setImageBitmap(bit_ico);
//////                    tv.setText(str_text_raw);
//////                    tv.setTextSize(25);
//////                    tv.setGravity(Gravity.CENTER | Gravity.BOTTOM);
////
////                    /** Show on MainScreen */
//////                    ll_main.addView(ll_colon_1_dynamical);
//////                    ll_colon_1_dynamical.addView(ll_child);
//////                    ll_child.addView(iv);
//////                    ll_child.addView(tv);
////
////                    // Отображение всех компонентов в контейнере СкролВью
//////                    this.setContentView(sv_main);
//                    /**_______________________________________________________________________________________________
//                     * Переделываю код для использовавания с 'RecyclerView + GridLayoutManager с тремя колонками' * _______________________________________________________________________________________________ */
//                    mDataFromServer.add(new Initial_screen(
//                            dataResponse.getConfiguartion().getInitialScreen().get(i).getColor(),
//                            dataResponse.getConfiguartion().getInitialScreen().get(i).getText(),
//                            dataResponse.getConfiguartion().getInitialScreen().get(i).getIcon()
//                    ));
//                }
//                /* Compare version on server and current version of app, if it difference, we change appearance of app .....Start */
//            }
//
//
//
//            @Override
//            public void onFailure(Call<DataResponse> call, Throwable t) {
//                Log.e("Error from ", "api.getData(request_Data_For_POST())... " + t);
//            }
//        });

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


    public void printmDataFromServer() {
//        Log.e("mDataFromServer", "printmDataFromServer: ", mDataFromServer.get());
    }


//    /**
//     * Method for DeSerialize picture ........................................................ Start
//     */
//    public Bitmap decodeImg(String str_icon_raw) {
//        /** Preparing Base64 for Deserialize ico */
//        String str_icon = str_icon_raw.replace(
//                "data:image/png;base64,", "");
//        byte[] decodedString = Base64.decode(str_icon, Base64.DEFAULT);
//
//        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//    }
//    /* Method for DeSerialize picture ........................................................ End */


}
