package biz.osvit.test1.activities;

import biz.osvit.test1.activities.SecondActivity;
import biz.osvit.test1.utils.C;

import biz.osvit.test1.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity {

	public EditText mInputIme;
	public EditText mInputPrezime;
	public EditText mInputAdresa;
	public Button mSubmitBtn;
	public Button mCancelBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUi();
		initListeners();

	}

	@Override
	public void initUi() {
		mInputIme = (EditText) findViewById(R.id.activity_main_ime_edit_text);
		mInputPrezime = (EditText) findViewById(R.id.activity_main_prezime_edit_text);
		mInputAdresa = (EditText) findViewById(R.id.activity_main_adresa_edit_text);
		mSubmitBtn = (Button) findViewById(R.id.activity_main_tipka);
		mCancelBtn = (Button) findViewById(R.id.activity_main_tipka2);

	}

	@Override
	public void initListeners() {
		mSubmitBtn.setOnClickListener(mClickListener);
		mCancelBtn.setOnClickListener(mClickListener);

		
	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.activity_main_tipka:
				String ime = mInputIme.getText().toString();
				String prezime = mInputPrezime.getText().toString();
				String adresa = mInputAdresa.getText().toString();
				startSecondActivity(ime, prezime, adresa);
				break;
				
			case R.id.activity_main_tipka2:
				mInputIme.setText("");
				mInputPrezime.setText("");
				mInputAdresa.setText("");
				
				break;
				

			}
		}

	};

	private void startSecondActivity(String ime, String prezime, String adresa) {
		Intent namjera = new Intent(this, SecondActivity.class);
		namjera.putExtra(C.MAIN_ACTIVITY_BUNDLE_KEY_IME, ime);
		namjera.putExtra(C.MAIN_ACTIVITY_BUNDLE_KEY_PREZIME, prezime);
		namjera.putExtra(C.MAIN_ACTIVITY_BUNDLE_KEY_ADRESA, adresa);
		startActivity(namjera);

		finish();
	}

}
