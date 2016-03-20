package biz.osvit.addbutton.activities;

import java.util.ArrayList;

import biz.osvit.addbutton.activities.SecondActivity;
import biz.osvit.addbutton.models.UserModel;
import biz.osvit.addbutton.utils.C;

import biz.osvit.addbutton.R;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

	private ArrayList<UserModel> mUsers = new ArrayList<UserModel>();

	private EditText mInputIme;
	private EditText mInputPrezime;
	private EditText mInputAdresa;
	private Button mSubmitBtn;
	private Button mCancelBtn;
	private Button mAddBtn;
	private Button mClear;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUi();
		initListeners();

	}

	@Override
	public void initUi() {
		mInputIme = (EditText) findViewById(R.id.activity_main_ime_edit_tekst);
		mInputPrezime = (EditText) findViewById(R.id.activity_main_prezime_edit_tekst);
		mInputAdresa = (EditText) findViewById(R.id.activity_main_adresa_edit_tekst);
		mSubmitBtn = (Button) findViewById(R.id.activity_main_tipka);
		mCancelBtn = (Button) findViewById(R.id.activity_main_tipka2);
		mAddBtn = (Button) findViewById(R.id.activity_main_tipka3);
		mClear = (Button) findViewById(R.id.activity_main_tipka_clear);

	}

	@Override
	public void initListeners() {
		mSubmitBtn.setOnClickListener(mClickListener);
		mCancelBtn.setOnClickListener(mClickListener);
		mAddBtn.setOnClickListener(mClickListener);
		mClear.setOnClickListener(mClickListener);

	}

	private OnClickListener mClickListener = new OnClickListener() { // zapakiran
																		// OnClickListener

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

			case R.id.activity_main_tipka3:
				String Ime = mInputIme.getText().toString();
				String Prezime = mInputPrezime.getText().toString();
				String Adresa = mInputAdresa.getText().toString();
				UserModel user = createUser(Ime, Prezime, Adresa);
				addUserToUsersList(user);
				Toast.makeText(MainActivity.this,
						"User je dodan, trenutna velièina je:" + mUsers.size(),
						Toast.LENGTH_SHORT).show();

				break;

			case R.id.activity_main_tipka_clear:

				mUsers.clear();

				break;
			}
		}
	};

	private UserModel createUser(String ime, String prezime, String adresa) {
		UserModel user = new UserModel();
		user.setIme(ime);
		user.setPrezime(prezime);
		user.setAdresa(adresa);
		return user;
	}

	private void addUserToUsersList(UserModel user) {
		mUsers.add(user);
	}

	private void startSecondActivity(String ime, String prezime, String adresa) {
		Intent namjera = new Intent(this, SecondActivity.class);
		UserModel model = new UserModel();
		model.setIme(ime);
		model.setPrezime(prezime);
		model.setAdresa(adresa);
		namjera.putParcelableArrayListExtra(C.MAIN_ACTIVITY_BUNDLE_KEY_USER,
				mUsers);
		startActivity(namjera);

		// finish();
	}
}