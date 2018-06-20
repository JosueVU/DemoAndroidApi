package INEC.ConectorUsuario;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import INEC.ADAPTER.GridViewImageUsuario;

/**
 * ----------ROLESE-----------
 * 1.RECEIVE JSON DATA
 * 2.PARSE IT
 * 3.BIND IT TO GRIDVIEW
 */
public class JSONParserUsuario extends AsyncTask<Void,Void,Boolean> {
    Context c;
    String jsonData;
    GridView gv;

    ProgressDialog pd;
    ArrayList<Bitmap> users = new ArrayList<Bitmap>();
    //String[] nombreUsuario;
    ArrayList<String> nombreUsuario = new ArrayList<String>();

    public JSONParserUsuario(Context c, String jsonData, GridView gv) {
        this.c = c;
        this.jsonData = jsonData;
        this.gv = gv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parse JSON");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPostExecute(Boolean isParsed) {
        super.onPostExecute(isParsed);

        pd.dismiss();

        if(isParsed)
        {


            GridViewImageUsuario adapter=new GridViewImageUsuario(c,nombreUsuario,users);
            gv.setAdapter(adapter);

            /*gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(c, nombreUsuario.get(i), Toast.LENGTH_SHORT).show();
                }
            });*/

        }else
        {
            Toast.makeText(c, "Unable To Parse,Check Your Log output", Toast.LENGTH_SHORT).show();

        }
    }

    private Boolean parse()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo;


                users.clear();
                nombreUsuario.clear();


            //nombreUsuario = new String[ja.length()];

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                String name=jo.getString("NOMBRE_USUARIO");
                String foto=jo.getString("FOTO_USUARIO");
                users.add(StringToBitMap(foto));
                nombreUsuario.add(name);
            }

            return true;

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte= Base64.decode(image, Base64.DEFAULT);

            InputStream inputStream  = new ByteArrayInputStream(encodeByte);
            Bitmap bitmap  = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
