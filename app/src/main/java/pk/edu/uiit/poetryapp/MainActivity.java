package pk.edu.uiit.poetryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pk.edu.uiit.poetryapp.API.API_Client;
import pk.edu.uiit.poetryapp.API.API_Interface;
import pk.edu.uiit.poetryapp.Adapters.Poetry_Adapter;
import pk.edu.uiit.poetryapp.Model.PoetryMOdel;
import pk.edu.uiit.poetryapp.Responce.GetApiResponce;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Poetry_Adapter poetry_adapter;
    API_Interface api_interface;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
        getPoetry();
        setSupportActionBar(toolbar);

    }
    public void initialization(){
        recyclerView=findViewById(R.id.PoetryRecyclerView);
        Retrofit retrofit= API_Client.getClient();
        api_interface=retrofit.create(API_Interface.class);
        toolbar=findViewById(R.id.mainBar);

    }
    public void performAction(List<PoetryMOdel> list){
        poetry_adapter=new Poetry_Adapter(MainActivity.this, list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(poetry_adapter);


    }
    private void getPoetry(){
        api_interface.getPoetry().enqueue(new Callback<GetApiResponce>() {
            @Override
            public void onResponse(Call<GetApiResponce> call, Response<GetApiResponce> response) {
                try {
                    if(response!=null){
                        if(response.body().getStatus().equals("1")){
                            performAction(response.body().getData());

                        }
                        else {
                            Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                catch (Exception e){
                    Log.e("exp",e.getLocalizedMessage());

                }
            }

            @Override
            public void onFailure(Call<GetApiResponce> call, Throwable t) {
                Log.e("Failure",t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add:
                Intent intent=new Intent(MainActivity.this, Add_Poetry.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
