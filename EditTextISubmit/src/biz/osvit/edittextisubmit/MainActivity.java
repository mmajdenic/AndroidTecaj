package biz.osvit.edittextisubmit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText mInputEditText;
	private Button mSubmitBtn;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		initUi();
		
		initListeners();
		
	}

	private void initUi() {
		mInputEditText = (EditText) findViewById(R.id.editText_unos);
		mSubmitBtn = (Button) findViewById(R.id.activity_main_submit_button); 
	}

	private void initListeners(){
		mSubmitBtn.setOnClickListener(mClickListener);
		mInputEditText.setOnClickListener(mClickListener);
	}
	
	private OnClickListener mClickListener = new OnClickListener() {
				
		@Override
		public void onClick(View v) {
			
			switch (v.getId()) {
				case R.id.activity_main_submit_button:
					//napravi nešto
					Toast.makeText(MainActivity.this, "Stisnio!", Toast.LENGTH_LONG).show();
										
					break;
					
					default:
						break;
						
					}
			
			}
	};
		
	private void initData(){
	}
	
}
