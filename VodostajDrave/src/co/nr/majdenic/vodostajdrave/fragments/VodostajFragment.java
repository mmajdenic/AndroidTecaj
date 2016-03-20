package co.nr.majdenic.vodostajdrave.fragments;


import co.nr.majdenic.vodostajdrave.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class VodostajFragment extends BaseFragment {

	private TextView mVodostajView;
	private Spinner mOdabirMjPostaje;
	private Button mDnevnaListaOcitanja;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View parentView = inflater.inflate(R.layout.fragment_vodostaj,
				container, false);
		initUi(parentView);
		initListeners();
		return parentView;
	}
	
	@Override
	public void initUi(View parent) {
		
		mVodostajView = (TextView) parent.findViewById(R.id.activity_main_vodostaj_ocitanje);
		mOdabirMjPostaje = (Spinner) parent.findViewById(R.id.activity_main_mjernapostaja_spinner);
		mDnevnaListaOcitanja = (Button) parent.findViewById(R.id.activity_main_vodostaj_lista_btn);
		
	}

	@Override
	public void initListeners() {
		
		mOdabirMjPostaje.setOnClickListener(mOnClickListener);
		mDnevnaListaOcitanja.setOnClickListener(mOnClickListener);
		
	}

	private OnClickListener mOnClickListener=new OnClickListener() {
	
		@Override
		public void onClick(View v) {
		
		}	
	};

}