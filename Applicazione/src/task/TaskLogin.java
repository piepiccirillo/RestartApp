package restartCampania.PiccirilloRoffo.RestartApp.task;

import org.json.JSONObject;

import restartCampania.PiccirilloRoffo.RestartApp.library.JSONParser;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class TaskLogin extends AsyncTask<String, Void, String[]> {
	String[] risultatoLogin = new String [3];
	ProgressDialog pd;
	Context context;
	
	public TaskLogin (Context _context){
		this.context = _context;
	}
	
	@Override
	protected void onPreExecute() {
		pd = new ProgressDialog(context);
		pd.setTitle("Login in corso!");
		pd.setMessage("Attendere...");
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
	}
	
	@Override
	protected String[] doInBackground(String... params) {
		String url = "http://restartcampa.altervista.org/app/login.php";
		
		JSONParser sitoLogin = new JSONParser();
		try{
			JSONObject obj = sitoLogin.getJSONFromUrl(url, params);
			risultatoLogin[0] = obj.getString("nome");
			risultatoLogin[1] = obj.getString("cognome");
			risultatoLogin[2] = obj.getString("validazione");
			
			return risultatoLogin;
		}
		catch (Exception e){
			
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(String[] result) {
		if (pd.isShowing()) {
            pd.dismiss();
        }
	}
}
