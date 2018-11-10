package cz.firstapp.firstapp_v4.internet;

import java.util.Map;

import cz.firstapp.firstapp_v4.model.DataResponse;
import cz.firstapp.firstapp_v4.modelSecondScreen.SecondScreenModel;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    /**
     * Code for server
     */
    @FormUrlEncoded
    @POST("DAN/ajaxData.php")
    Call<DataResponse> getData(@FieldMap Map<String, String> fields);

    @GET("new")
    Call<SecondScreenModel> getData();

}