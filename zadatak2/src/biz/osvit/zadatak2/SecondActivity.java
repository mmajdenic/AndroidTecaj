package biz.osvit.zadatak2;

import java.util.ArrayList;

import biz.osvit.zadatak2.R;
import biz.osvit.zadatak2.adapters.UserAdapter;
import biz.osvit.zadatak2.models.UserModel;
import biz.osvit.zadatak2.utils.C;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {

	private ArrayList<UserModel> mUserItems;

	private ListView mListView;

	private UserAdapter mUsersAdapter;

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
		initAdapters();

		mUsersAdapter = new UserAdapter(this, mUserItems);
		mListView.setAdapter(mUsersAdapter);

	}

	private void initAdapters() {

	}

	@Override
	public void initUi() {

		mListView = (ListView) findViewById(R.id.activity_second_listview);
	}

	private void initExtras(Intent intent) {
		mUserItems = intent
				.getParcelableArrayListExtra(C.MAIN_ACTIVITY_BUNDLE_KEY_USER);
	}

	@Override
	public void initListeners() {

	}

}