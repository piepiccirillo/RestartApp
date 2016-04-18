package restartCampania.PiccirilloRoffo.RestartApp;

import java.util.concurrent.ExecutionException;

import restartCampania.PiccirilloRoffo.RestartApp.task.TaskLogin;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener{
	EditText txtMail, txtPsw;
	Button btnLogin,btnReg;
	Intent currIntent;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		currIntent = getIntent();
		
		initialize();
	}

	private void initialize() {
		String pkg = getApplicationContext().getPackageName();
		String email = currIntent.getStringExtra(pkg + "email");
		String password = currIntent.getStringExtra(pkg + "password");
		txtMail = (EditText) findViewById(R.id.login_mail_user);
		txtPsw = (EditText) findViewById(R.id.login_password);
		
		if(email.equals("null")){
			txtMail.setText("provola@provola.it");	// LASCIAMOLI ANCORA PER I TEST
			txtPsw.setText("provola");	// POI LI LEVIAMO
		}
		else {
			txtMail.setText(email);	
			txtPsw.setText(password);	
		}
		
		btnLogin = (Button) findViewById(R.id.login_btn_login);
		btnLogin.setOnClickListener(this);
		btnReg = (Button) findViewById(R.id.btn_register);
		btnReg.setOnClickListener(this);
	}

	private boolean checkEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
	
	@Override
    protected void onPause() {
            super.onPause();
            finish();
    }
	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()){
			case R.id.login_btn_login:
				if (checkEmail(txtMail.getText().toString())){
					if (txtPsw.getText().toString().length()>3){
	
						String[] params = new String[2];
						String[] result = new String[3];
						params[0] = txtMail.getText().toString();
						params[1] = txtPsw.getText().toString();
					
						try {
							result = new TaskLogin(this).execute(params).get();
						
							if (result[0].equals("null") && result[1].equals("null")){
								Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show();
							} else {
								if(result[2].equals("0")){
									Toast.makeText(this, R.string.validate_error, Toast.LENGTH_LONG).show();
								} else{
								String pkg = getApplicationContext().getPackageName();
								Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
								myIntent.putExtra(pkg + "nome", result[0].toString());
								myIntent.putExtra(pkg + "cognome", result[1].toString());
								startActivity(myIntent);}}}
						catch (InterruptedException e) {e.printStackTrace();}
						catch (ExecutionException e) {e.printStackTrace();}
					} else {
							Toast.makeText(this, R.string.login_error_psw, Toast.LENGTH_LONG).show();}
					} else {
							Toast.makeText(this, R.string.login_error_mail, Toast.LENGTH_LONG).show();}
			break;
		
			case R.id.btn_register:
				Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent1);
			break;	
		}
	}
}
