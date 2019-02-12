package com.pllug.course.behen.repositorys.albums_repository;

import com.pllug.course.behen.data_models.Albums;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

     String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @GET("albums")
    Call<List<Albums>> getAlbums();

}
