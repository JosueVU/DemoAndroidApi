package INEC.API_CONECTOR;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import INEC.MODEL.Usuario;


public class ConectorAPI {
    private static Bitmap bitmap;

    public static Object connect(String jsonURL,String TipoMetodo,Usuario[] Data)
    {
        try
        {
            URL url=new URL(jsonURL);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            //String data = "ID_USUARIO=Hello+World!&NOMBRE_USUARIO=prueba2";
            //CON PROPS
            if(TipoMetodo =="GET"){
                con.setRequestMethod(TipoMetodo);
                con.setConnectTimeout(15000);
                con.setReadTimeout(15000);
                con.setDoInput(true);
                con.getInputStream();

            }else if(TipoMetodo !="GET"){
                con.setRequestMethod(TipoMetodo);
                con.setRequestProperty("Connection", "Keep-Alive");
                con.setConnectTimeout(15000);
                MultipartEntity entity = new MultipartEntity(
                        HttpMultipartMode.BROWSER_COMPATIBLE);
                con.setReadTimeout(15000);
                con.setDoInput(true);
                con.setDoOutput(true);

                for(int i =0; i<Data.length;i++ )
                {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap= BitmapFactory.decodeFile(Data[i].getRuta_Foto());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] data = bos.toByteArray();

                    ByteArrayBody bab = new ByteArrayBody(data, Data[i].getRuta_Foto());
                    entity.addPart("FOTO_USUARIO_" + i, bab);
                    entity.addPart("NOMBRE_USUARIO_"+ i, new StringBody(Data[i].getNombre_Usuario()));
                    //entity.addPart("APELLIDO1_"+ i, new StringBody(Data[i].getApellido1_Usuario()));

                }

                con.addRequestProperty("Content-length", entity.getContentLength() + "");
                con.addRequestProperty(entity.getContentType().getName(), entity.getContentType().getValue());
                OutputStream os = con.getOutputStream();
                entity.writeTo(con.getOutputStream());
                os.close();
                con.connect();
            }

            return con;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();

        }
    }
}
