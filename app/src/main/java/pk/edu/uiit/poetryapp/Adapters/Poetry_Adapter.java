package pk.edu.uiit.poetryapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pk.edu.uiit.poetryapp.API.API_Client;
import pk.edu.uiit.poetryapp.API.API_Interface;
import pk.edu.uiit.poetryapp.Model.PoetryMOdel;
import pk.edu.uiit.poetryapp.R;
import pk.edu.uiit.poetryapp.Responce.deleteResponse;
import pk.edu.uiit.poetryapp.Update_Poetry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Poetry_Adapter extends RecyclerView.Adapter<Poetry_Adapter.viewHolder> {
Context context;
List<PoetryMOdel> list;
API_Interface api_interface;

    public Poetry_Adapter(Context context, List<PoetryMOdel> poetryList) {
        this.context = context;
        this.list = poetryList;
        Retrofit retrofit= API_Client.getClient();
        api_interface=retrofit.create(API_Interface.class);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.poetrylist_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    holder.poetName.setText(list.get(position).getPoetName());
    holder.poetry.setText(list.get(position).getPoetry_data());
    holder.dateTime.setText(list.get(position).getDateTime());
    holder.delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            deletePoetry(list.get(holder.getAdapterPosition()).getId()+"",holder.getAdapterPosition());
        }
    });
    holder.update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, Update_Poetry.class);
            intent.putExtra("id",list.get(holder.getAdapterPosition()).getId());
            intent.putExtra("p_data",list.get(holder.getAdapterPosition()).getPoetry_data());
            context.startActivity(intent);
        }
    });
    }
    private void deletePoetry(String id,int pose){
        api_interface.deletePoetry(id).enqueue(new Callback<deleteResponse>() {
            @Override
            public void onResponse(Call<deleteResponse> call, Response<deleteResponse> response) {
                try {
                    if(response!=null){
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                    if(response.body().getStatus().equals("1")){
                        list.remove(pose);
                        notifyDataSetChanged();

                    }

                }
                catch (Exception e){
                    Log.e("Exception",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<deleteResponse> call, Throwable t) {
                Log.e("Fail",t.getLocalizedMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView poetName,poetry,dateTime;
        Button update,delete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            poetName=itemView.findViewById(R.id.poetName);
            poetry=itemView.findViewById(R.id.poetryData);
            dateTime=itemView.findViewById(R.id.dataTime);
            update=itemView.findViewById(R.id.updateBtn);
            delete=itemView.findViewById(R.id.deleteBtn);
        }
    }
}
