package biz.osvit.zadatak2;

import java.util.ArrayList;

import biz.osvit.zadatak2.SecondActivity;
import biz.osvit.zadatak2.models.UserModel;
import biz.osvit.zadatak2.utils.C;

import biz.osvit.zadatak2.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	private ArrayList<UserModel> mUsers = new ArrayList<UserModel>();

	private EditText mInputIme;
	private EditText mInputPrezime;
	private EditText mInputTelBroj;
	private Button mSubmitBtn;
	private Button mAddBtn;
	private RadioGroup mRadioGroup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUi();
		initListeners();

	}

	@Override
	public void initUi() {
		mInputIme = (EditText) findViewById(R.id.activity_main_unesi_ime);
		mInputPrezime = (EditText) findViewById(R.id.activity_main_unesi_prezime);
		mInputTelBroj = (EditText) findViewById(R.id.activity_main_unesi_telbroj);
		mSubmitBtn = (Button) findViewById(R.id.activity_main_pregled);
		mAddBtn = (Button) findViewById(R.id.activity_main_dodaj);
		mRadioGroup = (RadioGroup) findViewById(R.id.activity_main_radiogroup);

	}

	@Override
	public void initListeners() {
		mSubmitBtn.setOnClickListener(mClickListener);
		mAddBtn.setOnClickListener(mClickListener);


	}

	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.activity_main_pregled:			
				startSecondActivity();
				break;

			case R.id.activity_main_dodaj:
				String Ime = mInputIme.getText().toString();
				String Prezime = mInputPrezime.getText().toString();
				String TelBroj = mInputTelBroj.getText().toString();
				String Spol = getRadioGroup();
				UserModel user = createUser(Ime, Prezime, TelBroj, Spol);
				addUserToUsersList(user);
				Toast.makeText(MainActivity.this,
						"User je dodan, trenutna velièina je:" + mUsers.size(),
						Toast.LENGTH_SHORT).show();

				break;

			}
		}
	};


	private void addUserToUsersList(UserModel user) {
		mUsers.add(user);
	}

	private UserModel createUser(String ime, String prezime, String telbroj, String spol) {
		UserModel model = new UserModel();
		model.setIme(ime);
		model.setPrezime(prezime);
		model.setTelBroj(telbroj);
		model.setSpol(spol);
		return model;
	}
	
	private void startSecondActivity(){
		Intent namjera = new Intent(this, SecondActivity.class);
		namjera.putParcelableArrayListExtra(C.MAIN_ACTIVITY_BUNDLE_KEY_USER, mUsers);
		startActivity(namjera);
	}
	
	private String getRadioGroup(){
		
		int checkedRadioButtonId=mRadioGroup.getCheckedRadioButtonId();
		switch (checkedRadioButtonId) {
		case R.id.activity_main_radiobtn_musko:
			return "musko";
		case R.id.activity_main_radiobtn_zensko:
			return "žensko";
		
		}
	return null;
	}
	
}