package INEC.ConectorUsuario;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Conector {
    public static Object connect(String jsonURL,String TipoMetodo,String Data)
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
                con.setConnectTimeout(15000);
                con.setReadTimeout(15000);
                con.setDoInput(true);
                con.setDoOutput(true);
                con.getOutputStream().write(Data.getBytes("UTF-8"));
                con.getInputStream();
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
