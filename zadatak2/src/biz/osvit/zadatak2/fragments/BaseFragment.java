package biz.osvit.zadatak2.fragments;

import android.app.Fragment;
import android.view.View;


public abstract class BaseFragment extends Fragment {

	public abstract void initUi(View parent);
	
	public abstract void initListeners();
	
}
