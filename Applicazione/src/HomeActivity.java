package restartCampania.PiccirilloRoffo.RestartApp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener{
	TextView txt;
	Button btnLogin, btnToMain;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		initialize();
	}
	
	private void initialize() {
		txt = (TextView) findViewById(R.id.textView1);
		
		btnLogin = (Button) findViewById(R.id.button1);
		btnLogin.setOnClickListener(this);
		btnToMain = (Button) findViewById(R.id.button_toMain);
		btnToMain.setOnClickListener(this);
		
	}
			
	@Override    
    public void onClick(View arg) {
		String pkg = getApplicationContext().getPackageName();
		switch (arg.getId()){
			case R.id.button1:
					Intent log = new Intent(HomeActivity.this,LoginActivity.class);  
					log.putExtra(pkg + "email", "null");
					startActivity(log);
				break;
			case R.id.button_toMain:
					Intent log2 = new Intent(HomeActivity.this,MainActivity.class);
					log2.putExtra(pkg + "nome", "null");
					startActivity(log2);
	            break;
		}
	}
		
};

