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

import INEC.CONECTOR.JSONDownloader;

public class operacionMostrar extends Fragment {
    View myView;
    GridView gridView;
    String jsonURL="http://172.16.30.190/INEC.TEMA1.WebAPI/api/values/ObtenerImagen";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.opcion_mostrar, container, false);
        Toolbar toolbar = (Toolbar)myView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        gridView = (GridView)myView.findViewById(R.id.gridView);


        //RequestParams params = new RequestParams();
        //AsyncHttpClient image = new AsyncHttpClient();
        //image.get(jsonURL,new AsyncHttpResponseHandler() {

            //@Override
            //public void onStart() {
                // called before request is started
            //}

            //@Override
            //public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                /*JSONObject ja=new JSONObject(response.);
                JSONObject jo;*/
                ///String str = new String(response, "UTF-8");

                //boolean resultado = true;
            //}

            /*@Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                boolean resultado = true;
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                boolean resultado = true;
            }
        });*/

        new JSONDownloader(((AppCompatActivity)getActivity()),jsonURL,gridView).execute();

        return  myView;
    }
}
