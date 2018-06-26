package INEC.API_CONECTOR;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import INEC.MODEL.Usuario;


public class JSONDownloaderUsuarioAPI extends AsyncTask<Void,Void,String> {
    Context c;
    String jsonURL;
    String tipoMetodo;
    Usuario[] data;

    ProgressDialog pd;

    public JSONDownloaderUsuarioAPI(Context c, String jsonURL, String TipoMetodo, Usuario[] Data) {
        this.c = c;
        this.jsonURL = jsonURL;
        this.tipoMetodo = TipoMetodo;
        this.data = Data;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Download JSON");
        pd.setMessage("Downloading...Please wait");
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        return download(jsonURL,tipoMetodo);
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();
        if(jsonData.startsWith("Error"))
        {
            String error=jsonData;
            Toast.makeText(c, error, Toast.LENGTH_SHORT).show();
        }else
        {
            //PARSE
            new JSONParserUsuarioAPI(c,jsonData).execute();
        }
    }

    private String download(String URL, String TipoMetodo)
    {
        Object connection= ConectorAPI.connect(URL,TipoMetodo,data);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }

        try
        {
            //ESTABLISH CONNECTION
            HttpURLConnection con= (HttpURLConnection) connection;
            if(con.getResponseCode()==con.HTTP_OK)
            {
                //GET INPUT FROM STREAM
                InputStream is=new BufferedInputStream(con.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer jsonData=new StringBuffer();

                //READ
                while ((line=br.readLine()) != null)
                {
                    jsonData.append(line);
                }

                //CLOSE RESOURCES
                br.close();
                is.close();

                //RETURN JSON
                return jsonData.toString();

            }else
            {
                return "Error "+con.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error "+e.getMessage();
        }

    }
}
