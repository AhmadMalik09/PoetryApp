package pk.edu.uiit.poetryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pk.edu.uiit.poetryapp.API.API_Client;
import pk.edu.uiit.poetryapp.API.API_Interface;
import pk.edu.uiit.poetryapp.Responce.deleteResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Update_Poetry extends AppCompatActivity {
Toolbar toolbar;
EditText update_Poetry;
Button btnUpdate;
int id;
String Poetry_Data;
API_Interface api_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_poetry);
        initialize();
        performAction();
        setTootbar();
    }
    private void initialize(){
        toolbar=findViewById(R.id.Update_Poetry);
        update_Poetry=findViewById(R.id.updtpoetry);
        btnUpdate=findViewById(R.id.btnUpdate);
        id=getIntent().getIntExtra("id",0);
        Poetry_Data=getIntent().getStringExtra("p_data");
        update_Poetry.setText(Poetry_Data);
        Retrofit retrofit= API_Client.getClient();
        api_interface=retrofit.create(API_Interface.class);
    }
    private void performAction(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String p_data=update_Poetry.getText().toString();
            if(p_data.equals("")){
                update_Poetry.setError("Field is Empty");
            }
            else{
            callAPI(p_data,id+"");
            }
            }
        });
    }
    public void setTootbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void callAPI(String pdata,String pId){
        api_interface.update_Poetry(pdata,pId).enqueue(new Callback<deleteResponse>() {
            @Override
            public void onResponse(Call<deleteResponse> call, Response<deleteResponse> response) {
                try {
                    if(response!=null){
                        if(response.body().getStatus().equals("1")){
                            Toast.makeText(Update_Poetry.this, "Data updated Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(Update_Poetry.this, "Not Update", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                catch (Exception e){
                    Log.e("exp",e.getLocalizedMessage());

                }
            }

            @Override
            public void onFailure(Call<deleteResponse> call, Throwable t) {
                Log.e("update",t.getLocalizedMessage());
            }
        });
    }

}