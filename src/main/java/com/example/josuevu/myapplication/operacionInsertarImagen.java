package com.example.josuevu.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import INEC.ADAPTER.GridViewImageUsuario;
import INEC.MODEL.Usuario;
import cz.msebera.android.httpclient.Header;

public class operacionInsertarImagen extends Fragment {
    View myView;
    private static int RESULT_LOAD_IMAGE = 1;
    GridView gridView;
    //String jsonURL="http://jsonplaceholder.typicode.com/users";
    String jsonURL="http://192.168.0.102/INEC.TEMA1.WebAPI/api/UsuarioImagen/AgregarUsuariosMasivos";
    GridView gv;
    private ImageView profImg;
    private static final int RESULT_OK = -1;
    Bitmap image;
    Usuario[] Data = null;
    Usuario DataModel = null;
    ArrayList<String> rutaFoto = new ArrayList<>();
    ArrayList<String> prueba = new ArrayList<>();
    ArrayList<Bitmap> prueba2 = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.opcion_insertar_imagen, container, false);
        Toolbar toolbar = (Toolbar)myView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        gridView = (GridView)myView.findViewById(R.id.gridViewClientes);



        profImg = (ImageView) myView.findViewById(R.id.imgView);
        Button buttonLoadImage = (Button)myView.findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        Button buttonLoadImages = (Button)myView.findViewById(R.id.buttonLoadImages);
        buttonLoadImages.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                RequestParams params = new RequestParams();

                //params.put("key2", "value2");

                for(int i=0;i<gridView.getAdapter().getCount();i++){
                    File imgFile = new File(rutaFoto.get(i));
                    try {
                        params.put("NOMBRE_USUARIO_"+i, "Prueba");
                        params.put("FOTO_USUARIO_"+i,imgFile);
                    } catch(FileNotFoundException e) {
                        boolean resultado = true;
                    }
                    //params.put("NOMBRE_USUARIO", "Prueba");
                }




                AsyncHttpClient image = new AsyncHttpClient();
                image.post(jsonURL, params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onStart() {
                        // called before request is started
                    }

                    @Override
                    public void onSuccess(int arg0,
                                          Header[] arg1, byte[] arg2) {
                        // called when response HTTP status is "200 OK"
                        boolean resultado = true;;
                        JSONObject jo;
                        String resultadoString = new String(arg2);
                        resultado = resultadoString.contains("true");
                        /*try
                        {
                            String prueba = new String(arg2);
                            JSONObject json = new JSONObject(
                                    new String(arg2));
                            //jo=response.getJSONObject(index);
                            resultado = json.getBoolean("");
                        }
                        catch (JSONException e){

                            e.printStackTrace();
                        }*/

                        if(resultado){

                            Toast.makeText(((AppCompatActivity)getActivity()), "Se inserto con exito", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            Toast.makeText(((AppCompatActivity)getActivity()), "Hubo un error al insertar las imagenes", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(int arg0,
                                          Header[] arg1, byte[] arg2,
                                          Throwable arg3) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        boolean resultado = true;
                        String s = new String(arg2);
                    }

                    @Override
                    public void onRetry(int retryNo) {
                        // called when request is retried
                        boolean resultado = true;
                    }
                });



                /*Data = new Usuario[gridView.getAdapter().getCount()];
                for(int i=0;i<gridView.getAdapter().getCount();i++){
                    DataModel= new Usuario();
                    DataModel.setNombre_Usuario("Ingreso");
                    DataModel.setRuta_Foto(rutaFoto.get(i));*/
                    /*Bitmap prueba =(Bitmap)gridView.getAdapter().getItem(i);
                    Data= new String[gridView.getAdapter().getCount()];
                    Data[i] ="&FOTO_USUARIO="+ BitMapToString(prueba)+ "&ID_USUARIO=004&";*/
                    /*Data[i] = DataModel;
                }

                new JSONDownloaderUsuarioAPI(((AppCompatActivity)getActivity()),jsonURL,"POST",Data).execute();*/
            }
        });

        return  myView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //GETTING IMAGE FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = ((AppCompatActivity)getActivity()).getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            rutaFoto.add(picturePath);
            cursor.close();

            ImageView imageView = (ImageView)myView.findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

            prueba.add("Prueba");
            prueba2.add(BitmapFactory.decodeFile(picturePath));
            GridViewImageUsuario adapter=new GridViewImageUsuario(((AppCompatActivity)getActivity()),prueba,prueba2);
            gridView.setAdapter(adapter);

        }
    }
}
