package INEC.CONECTOR;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * --------------ROLES---------------
 * 1.DOWNLOAD DATA IN BG THREAD
 * 2.PASS IT TO PARSER CLASS
 */
public class JSONDownloader extends AsyncTask<Void,Void,String>  {

    Context c;
    String jsonURL;
    GridView gv;

    ProgressDialog pd;

    public JSONDownloader(Context c, String jsonURL, GridView gv) {
        this.c = c;
        this.jsonURL = jsonURL;
        this.gv = gv;
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
        return download();
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
            new JSONParser(c,jsonData,gv).execute();
        }
    }

    /*private String download()
    {
        int responseCode = -1;
        JSONObject jsonResponse = null;
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet("http://www.grepscience.com/api/get_recent_summary/");

        try {
            HttpResponse response = client.execute(httpget);
            StatusLine statusLine = response.getStatusLine();
            responseCode = statusLine.getStatusCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while((line = reader.readLine()) != null){
                    builder.append(line);
                }

                jsonResponse = new JSONObject(builder.toString());
            }
            else {
                Log.i(TAG, String.format("Unsuccessful HTTP response code: %d", responseCode));
            }
        }
        catch (JSONException e) {
            logException(e);
        }
        catch (Exception e) {
            logException(e);
        }

        return jsonResponse;

    }*/

    private String download()
    {
        Object connection=Connector.connect(jsonURL);
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
                    jsonData.append(line+"n");
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