package pk.edu.uiit.poetryapp.API;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API_Client {
    public static Retrofit retrofit=null;
    public static Retrofit getClient(){
        if(retrofit==null) {
            Gson gson = new Gson().newBuilder().create();
            OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.167/poetry_apis/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }
        return retrofit;
    }
}
