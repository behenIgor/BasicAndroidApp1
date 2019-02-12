package com.pllug.course.behen.repositorys.albums_repository;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pllug.course.behen.ProfileFragmentActivity;
import com.pllug.course.behen.R;


import com.pllug.course.behen.repositorys.albums_repository.MainActivityAlbums;

public class AlbumsFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_albums, container, false);



    }


}





