package biz.osvit.addbutton.activities;

import biz.osvit.addbutton.R;
import biz.osvit.addbutton.models.UserModel;
import biz.osvit.addbutton.utils.C;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {

	private TextView mTitleIme;
	private TextView mTitlePrezime;
	private TextView mTitleAdresa;
	public Button mExitBtn;

	private UserModel mUserModel;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		initUi();
		initListeners();

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			initExtras(extras);
		}

		setTitleTextView();
	}

	@Override
	public void initUi() {
		mTitleIme = (TextView) findViewById(R.id.activity_second_ispisano_ime);
		mTitlePrezime = (TextView) findViewById(R.id.activity_second_ispisano_prezime);
		mTitleAdresa = (TextView) findViewById(R.id.activity_second_ispisana_adresa);
		mExitBtn = (Button) findViewById(R.id.activity_second_exit_btn);
	}

	private void initExtras(Bundle extras) {
		mUserModel = extras.getParcelable(C.MAIN_ACTIVITY_BUNDLE_KEY_USER);

	}

	private void setTitleTextView() {
		mTitleIme.setText(mUserModel.getIme());
		mTitlePrezime.setText(mUserModel.getPrezime());
		mTitleAdresa.setText(mUserModel.getAdresa());
	}

	
	@Override
	public void initListeners() {

		mExitBtn.setOnClickListener(mClickListener);
	}
	
	private OnClickListener mClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			
			case R.id.activity_second_exit_btn:
				
				SecondActivity.this.finish();
				
				break;
			}
		}
	};
	
	
}