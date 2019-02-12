package com.pllug.course.behen.repositorys.albums_repository;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.pllug.course.behen.R;
import com.pllug.course.behen.data_models.Albums;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivityAlbums extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView marvelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_calls_main);


        marvelList = findViewById(R.id.list_info);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<Albums>> call = api.getAlbums();

        call.enqueue(new Callback<List<Albums>>() {

            @Override
            public void onResponse(Call<List<Albums>> call, Response<List<Albums>> response) {

                List<Albums> albums = response.body();
                String[] albumsUserId = new String[albums.size()];
                int i = 0;

                for (Albums album : albums) {
                    albumsUserId[i] =  album.getUserId() + "_" + album.getId() + "_" + album.getTitle();
                    i++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.api_calls_item, albumsUserId);
                marvelList.setAdapter(adapter);
                marvelList.setOnItemClickListener(MainActivityAlbums.this);
            }

            @Override
            public void onFailure(Call<List<Albums>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView textUrl = (TextView) view;
        String data = textUrl.getText().toString();
        String url = data.split("_")[2];
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}

