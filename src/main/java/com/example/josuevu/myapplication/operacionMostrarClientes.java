package com.example.josuevu.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import INEC.ConectorUsuario.JSONDownloaderUsuario;

public class operacionMostrarClientes extends Fragment {
    View myView;

    GridView gridView;
    //String jsonURL="http://jsonplaceholder.typicode.com/users";
    String jsonURL="http://172.16.31.42/INEC.TEMA1.WebAPI/api/Usuario";
    GridView gv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.opcion_mostrar_clientes, container, false);
        Toolbar toolbar = (Toolbar)myView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        gridView = (GridView)myView.findViewById(R.id.gridView);

        new JSONDownloaderUsuario(((AppCompatActivity)getActivity()),jsonURL,gridView,"GET",null).execute();

        return  myView;
    }
}
