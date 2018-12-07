package cz.firstapp.firstapp_v4.internet;

import java.util.Map;

import com.google.gson.JsonObject;
import cz.firstapp.firstapp_v4.modelApiSecurity.RootSecurity;
import cz.firstapp.firstapp_v4.model.RootMainScreen;
import cz.firstapp.firstapp_v4.modelLogin.RootLogin;
import cz.firstapp.firstapp_v4.modelSecondScreen.RootSecondScreen;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    /**
     * Code for REMOTE server
     */

    @FormUrlEncoded
    @POST("DAN/ajaxData.php")
    Call<RootMainScreen> getData(@FieldMap Map<String, String> fields);

//    Method for get info by POST ("@FormUrlEncoded" + "@POST") + body like a "@File" - use OBLIGATORY!
    @FormUrlEncoded
    @POST("DAN/ajaxData.php")
    Call<RootLogin> getLoginAccount(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("DAN/ajaxData.php")
    Call<RootSecondScreen> getSecondScreen(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @POST("DAN/ajaxData.php")
    Call<RootSecurity> getSecurity(@FieldMap Map<String, String> fields);




}