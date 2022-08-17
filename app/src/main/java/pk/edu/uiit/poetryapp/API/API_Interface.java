package pk.edu.uiit.poetryapp.API;

import pk.edu.uiit.poetryapp.Responce.GetApiResponce;
import pk.edu.uiit.poetryapp.Responce.deleteResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API_Interface {
    @GET("get_poetry.php")
    Call<GetApiResponce> getPoetry();
    @FormUrlEncoded
    @POST("delete_api.php")
    Call<deleteResponse>deletePoetry(@Field("id") String Poetry_id);

   @FormUrlEncoded //because we pass data as "form data" in body of POSTMAN.
    @POST("poetry_app.php")
    Call<deleteResponse>Add_Poetry(@Field("poetry") String poetry_data,@Field("poet_name")String poetName);
    //parament inside the field brackets are called request parameteres
    @FormUrlEncoded
    @POST("updatePoetry.php")
    Call<deleteResponse>update_Poetry(@Field("poetry_data")String poetry_data,@Field("id")String ID);
}
