package com.example.josuevu.myapplication;

import android.Manifest;
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

import java.util.ArrayList;

import INEC.ADAPTER.GridViewImageUsuario;
import INEC.API_CONECTOR.JSONDownloaderUsuarioAPI;
import INEC.Encriptar.encriptar;
import INEC.MODEL.Usuario;
import pub.devrel.easypermissions.EasyPermissions;

public class operacionInsertarMasivo extends Fragment {
    View myView;
    private static int RESULT_LOAD_IMAGE = 1;
    GridView gridView;
    //String jsonURL="http://jsonplaceholder.typicode.com/users";
    String jsonURL="http://192.168.0.102/INEC.TEMA1.WebAPI/api/Usuario/AgregarUsuariosMasivos";
    GridView gv;
    private ImageView profImg;
    private static final int RESULT_OK = -1;
    Bitmap image;
    Usuario[] Data = null;
    Usuario DataModel = null;
    ArrayList<String> rutaFoto = new ArrayList<>();
    ArrayList<String> prueba = new ArrayList<>();
    ArrayList<Bitmap> prueba2 = new ArrayList<>();
    private String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.opcion_insertar_masivo, container, false);
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
                Data = new Usuario[gridView.getAdapter().getCount()];
                encriptar encripcion = new encriptar();
                for(int i=0;i<gridView.getAdapter().getCount();i++){
                    DataModel= new Usuario();
                    try{
                        DataModel.setNombre_Usuario(encripcion.Encrypt("Ingreso","PruebaInec2018.AbAbAb23"));
                    }catch (Exception ex){


                    }

                    DataModel.setRuta_Foto(rutaFoto.get(i));
                    /*Bitmap prueba =(Bitmap)gridView.getAdapter().getItem(i);
                    Data= new String[gridView.getAdapter().getCount()];
                    Data[i] ="&FOTO_USUARIO="+ BitMapToString(prueba)+ "&ID_USUARIO=004&";*/
                    Data[i] = DataModel;
                }

                new JSONDownloaderUsuarioAPI(((AppCompatActivity)getActivity()),jsonURL,"POST",Data).execute();


            }
        });

        return  myView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyPermissions.requestPermissions(this, "Access for storage",
                101, galleryPermissions);
        //GETTING IMAGE FROM GALLERY
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            //ActivityCompat.requestPermissions(new String[]{}, 1);


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
