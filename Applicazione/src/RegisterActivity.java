package restartCampania.PiccirilloRoffo.RestartApp;

import java.util.concurrent.ExecutionException;

import restartCampania.PiccirilloRoffo.RestartApp.task.TaskRegister;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener{
	EditText txtMail, txtPsw, txtPsw2, txtName, txtSurname;
	Button btnRegister;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		initialize();
	}
	
	private void initialize() {
		txtName = (EditText) findViewById(R.id.name_user);
		txtSurname = (EditText) findViewById(R.id.surname_user);
		txtMail = (EditText) findViewById(R.id.login_mail_user);
		txtPsw = (EditText) findViewById(R.id.login_password);
		txtPsw2 = (EditText) findViewById(R.id.loginPassword2);
		
		btnRegister = (Button) findViewById(R.id.btn_register);
		btnRegister.setOnClickListener(this);
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
			case R.id.btn_register:
				if (checkEmail(txtMail.getText().toString())){
					if (txtSurname.getText().toString().length()>=3 ){
						if (txtPsw.getText().toString().length()>=3){
							if (txtPsw2.getText().toString().equals(txtPsw.getText().toString())){
								if (txtName.getText().toString().length()>=3 ){	
									String[] params = new String[4];
									String[] result = new String[1];
									params[0] = txtName.getText().toString();
									params[1] = txtSurname.getText().toString();
									params[2] = txtMail.getText().toString();
									params[3] = txtPsw.getText().toString();
									
									try {result = new TaskRegister(this).execute(params).get();
										if (result[0].equals("1")){
											Toast.makeText(this, "Verifica la tua e-mail ed effettua il login", Toast.LENGTH_LONG).show();
											String pkg = getApplicationContext().getPackageName(); 
											Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class); 
											myIntent.putExtra(pkg + "email", params[2].toString()); 
											myIntent.putExtra(pkg + "password", params[3].toString()); 
											startActivity(myIntent);
										} else {Toast.makeText(this, R.string.register_error, Toast.LENGTH_LONG).show();}}
									catch (InterruptedException e) {e.printStackTrace();}
									catch (ExecutionException e) {e.printStackTrace();}
									
								} else {Toast.makeText(this, R.string.login_error_name, Toast.LENGTH_LONG).show();}
							} else {Toast.makeText(this, R.string.passwordError, Toast.LENGTH_LONG).show();}
						} else {Toast.makeText(this, R.string.login_error_psw, Toast.LENGTH_LONG).show();}
					} else {Toast.makeText(this, R.string.login_error_sur, Toast.LENGTH_LONG).show();}
				} else {Toast.makeText(this, R.string.login_error_mail, Toast.LENGTH_LONG).show();}
			break;
		}
	}
	
 }

		
		
		
		
		