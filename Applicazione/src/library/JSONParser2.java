package restartCampania.PiccirilloRoffo.RestartApp.library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;
 
public class JSONParser2 {
 
    private static InputStream is = null;
    private static JSONObject jObj = null;
    private static String json = "";
 
    
    public JSONParser2() {
 
    }
    public JSONObject getJSONFromUrl(String url, String[] parametri) {
 
        // Preparo i dati da passare tramite POST
    	ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("nom", parametri[0]));
    	params.add(new BasicNameValuePair("cog", parametri[1]));
    	params.add(new BasicNameValuePair("ema", parametri[2]));
    	params.add(new BasicNameValuePair("psw", parametri[3]));
    	// HTTP request
    	Log.i("Name", parametri[0]);
    	Log.i("Surname", parametri[1]);
    	Log.i("E-mail", parametri[2]);
    	Log.i("Password", parametri[3]);
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(new UrlEncodedFormEntity(params));
 
            HttpResponse httpResponse = httpClient.execute(httppost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            json = sb.toString();
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Errore nella conversione " + e.toString());
        }
 
        
        try {
            jObj = new JSONObject(json);            
        } catch (JSONException e) {
            Log.e("JSON Parser", "Errore nel parse " + e.toString());
        }
 
        // return l'oggetto JSONObject
        return jObj;
 
    }
}