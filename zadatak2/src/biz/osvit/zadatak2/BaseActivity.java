package biz.osvit.zadatak2;

import android.app.Activity;

public abstract class BaseActivity extends Activity {

	protected abstract void initUi();

	protected abstract void initListeners();

}