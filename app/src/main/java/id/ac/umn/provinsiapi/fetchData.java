package id.ac.umn.provinsiapi;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Abhishek Panwar on 7/14/2017.
 */

public class fetchData extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            //URL url = new URL("http://dev.farizdotid.com/api/daerahindonesia/provinsi");
             URL url = new URL("http://starlord.hackerearth.com/studio");
            //URL url = new URL("https://api.myjson.com/bins/j5f6b");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            //JSONArray JA = new JSONArray(data);
            JSONObject jObject = new JSONObject(data);
            JSONArray jarray = jObject.getJSONArray("semuaprovinsi");
            //JSONObject provObject = jObject.getJSONObject("semuaprovinsi");
            for(int i =0 ;i <jarray.length(); i++){
                //String attributeId = provObject.getString("category");
                JSONObject JO = (JSONObject) jarray.get(i);
                singleParsed =  "id:" + JO.get("id") + "\n"+
                        "nama:" + JO.get("nama") + "\n";

                dataParsed = dataParsed + singleParsed +"\n" ;

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);

    }
}
