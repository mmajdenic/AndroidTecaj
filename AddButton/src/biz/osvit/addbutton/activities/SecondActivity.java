package biz.osvit.addbutton.activities;

import java.util.ArrayList;

import biz.osvit.addbutton.R;
import biz.osvit.addbutton.adapters.UsersAdapter;
import biz.osvit.addbutton.models.UserModel;
import biz.osvit.addbutton.utils.C;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {

//	private TextView mZadnjeIme;
//	private TextView mPredZadnjeIme;
//	private TextView mPredPredZadnjeIme;
	public Button mExitBtn;

	private ArrayList<UserModel> mUserItems;
	
	private ListView mListView;
	
	private UsersAdapter mUsersAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		initUi();

		Intent intent = getIntent();
		if (intent != null) {
			initExtras(intent);
		}

		initListeners();
//		fillChildsWithText();
		
		initAdapters();
		
		mUsersAdapter = new UsersAdapter(this, mUserItems);
		mListView.setAdapter(mUsersAdapter);
		
	}

	private void initAdapters() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initUi() {
		
		mListView = (ListView) findViewById(R.id.activity_second_listview);
		
//		mZadnjeIme = (TextView) findViewById(R.id.activity_second_ispisano_ime);
//		mPredZadnjeIme = (TextView) findViewById(R.id.activity_second_ispisano_prezime);
//		mPredPredZadnjeIme = (TextView) findViewById(R.id.activity_second_ispisana_adresa);
		mExitBtn = (Button) findViewById(R.id.activity_second_exit_btn);
	}

	private void initExtras(Intent intent) {
		mUserItems = intent.getParcelableArrayListExtra(C.MAIN_ACTIVITY_BUNDLE_KEY_USER);
	}

//	private void fillChildsWithText() {
//
//		for (int i = 1; i < 4; i++) {
//			if(mUserItems.size() == 0){
//				SecondActivity.this.finish();
//				}
//			else;
//			switch (i) {
//			case 1:
//				tryGetUserModelFromPositionAndShow(mZadnjeIme, i);
//				break;
//			case 2:
//				tryGetUserModelFromPositionAndShow(mPredZadnjeIme, i);
//				break;
//			case 3:
//				tryGetUserModelFromPositionAndShow(mPredPredZadnjeIme, i);
//				break;
//			
//			}
//		}
//	}
//
//	private void tryGetUserModelFromPositionAndShow(TextView textView,
//			int position) {
//
//		try {
//			UserModel firstModel = mUserItems.get(mUserItems.size() - position);
//			textView.setText(firstModel.getIme());	
//		} catch (IndexOutOfBoundsException e) {
//			e.printStackTrace();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	// Exit btn
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