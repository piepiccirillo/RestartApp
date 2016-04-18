package restartCampania.PiccirilloRoffo.RestartApp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView txtStampa;
	Intent currIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		currIntent = getIntent();
		
		txtStampa = (TextView) findViewById (R.id.main_lblStampa);
		
		stampaValori();
		
	}

	private void stampaValori() {
		String pkg = getApplicationContext().getPackageName();
		String nome = currIntent.getStringExtra(pkg + "nome");
		String stampa;
		
		if (nome.equals("null")) // se è vero, allora l'utente non si è loggato
		{
			stampa = "Benvenuto, ospite!\n";
		} else {
			stampa = "Benvenuto, " + currIntent.getStringExtra(pkg + "nome");
			stampa += " " + currIntent.getStringExtra(pkg + "cognome") + "!\n";
		}
		
		txtStampa.setText(stampa);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
