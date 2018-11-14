package cz.firstapp.firstapp_v4.internet;

import java.util.Map;

import cz.firstapp.firstapp_v4.apiMy.ApisMy;
import cz.firstapp.firstapp_v4.apiSecurity.ApiSecurity;
import cz.firstapp.firstapp_v4.model.DataResponse;
import cz.firstapp.firstapp_v4.modelSecondScreen.SecondScreenModel;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    /**
     * Code for REMOTE server
     */

//     Getting data of 'MainScreen.json'
//    @FormUrlEncoded
//    @POST("DAN/ajaxData.php")
//    Call<DataResponse> getData(@FieldMap Map<String, String> fields);

//    Getting data of list of 'ApiMy.json'
//    @FormUrlEncoded
//    @POST("DAN/ajaxData.php")
//    Call<DataResponse> getApi(@FieldMap Map<String, String> fields);

//    Getting data of ApiSecurity
//    @FormUrlEncoded
//    @POST("DAN/ajaxData.php")
//    Call<DataResponse> getApi(@FieldMap Map<String, String> fields);



//    Getting data of list of 'ApiSecurity.json'
//    Getting data of list of 'SecondScreen.json'







//    For local server - полсел показа Дану локалка перестала работать, просто пустой экран показывает.
    @GET("data")
    Call<DataResponse> getDataMainScreen();

    @GET("security")
    Call<ApiSecurity> getDataSecurity();

    @GET("new")
    Call<SecondScreenModel> getData();

    @GET("api")
    Call<ApisMy> getApi();


//    E/APIs:  [security, notebooks, printers, sip, connectivity, new, dms, desktops, software, mobiles, email, user, videoconference, sap, citrix]


}