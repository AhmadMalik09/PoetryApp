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

public class Add_Poetry extends AppCompatActivity {
    Toolbar toolbar;
    EditText P_Name,Poetry;
    Button btnAdd;
    API_Interface api_interface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_poetry);
        initiailize();
        setTootbar();
        performAction();

    }
    public void initiailize(){
        toolbar=findViewById(R.id.Add_Poetry);
        Poetry=findViewById(R.id.poetry);
        P_Name=findViewById(R.id.poet_name);
        btnAdd=findViewById(R.id.btnAdd);
        Retrofit retrofit= API_Client.getClient();
        api_interface=retrofit.create(API_Interface.class);
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
    public void performAction(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poetry_data=Poetry.getText().toString();
                String poetName=P_Name.getText().toString();
                if(poetry_data.equals("")){
                    Poetry.setError("Field is Empty");
                }
                else if(poetName.equals("")){
                    P_Name.setError("Field is Empty");
                }
                else {
                    callAPI(poetry_data,poetName);
                }
            }
        });
    }
    private void callAPI(String poetryData,String poetName){
        api_interface.Add_Poetry(poetryData,poetName).enqueue(new Callback<deleteResponse>() {
            @Override
            public void onResponse(Call<deleteResponse> call, Response<deleteResponse> response) {
                try {
                    if(response!=null){
                        if(response.body().getStatus().equals("1")){
                            Toast.makeText(Add_Poetry.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(Add_Poetry.this,
                                    "Not Added", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
                catch (Exception e){
                    Log.e("exp",e.getLocalizedMessage());

                }
            }

            @Override
            public void onFailure(Call<deleteResponse> call, Throwable t) {
                Log.e("FaiLL",t.getLocalizedMessage());
            }
        });
    }
}