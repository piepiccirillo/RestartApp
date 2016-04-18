package restartCampania.PiccirilloRoffo.RestartApp.task;

import org.json.JSONObject;

import restartCampania.PiccirilloRoffo.RestartApp.library.JSONParser2;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class TaskRegister extends AsyncTask<String, Void, String[]> {
	String[] risultatoRegister = new String[4];
	ProgressDialog pd;
	Context context;
	
	public TaskRegister (Context _context){
		this.context = _context;
	}
	
	@Override
	protected void onPreExecute() {
		pd = new ProgressDialog(context);
		pd.setTitle("Registrazione in corso!");
		pd.setMessage("Attendere...");
		pd.setCancelable(false);
		pd.setIndeterminate(true);
		pd.show();
	}
	
	@Override
	protected String[] doInBackground(String... params) {
		String url = "http://restartcampa.altervista.org/app/register.php";
		
		JSONParser2 sitoReg = new JSONParser2();
		try{
			JSONObject obj = sitoReg.getJSONFromUrl(url, params);
			risultatoRegister[0] = obj.getString("exit");
			
			return risultatoRegister;
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