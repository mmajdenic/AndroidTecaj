package co.nr.majdenic.vodostajdrave.activities;



import co.nr.majdenic.vodostajdrave.R;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class VodostajActivity extends BaseActivity {
	
	// JSON node keys
	private static final String TAG_DATUM_SAT = "datum_sat";
	private static final String TAG_VODOSTAJ = "vodostaj";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        String datumIsat = in.getStringExtra(TAG_DATUM_SAT);
        String vodostaj = in.getStringExtra(TAG_VODOSTAJ);

        
        // Displaying all values on the screen
        TextView lblDatumSat = (TextView) findViewById(R.id.activity_list_datum_sat_label);
        TextView lblVodostaj = (TextView) findViewById(R.id.activity_list_vodostaj_label);
        
        lblDatumSat.setText(datumIsat);
        lblVodostaj.setText(vodostaj);

    }

	@Override
	protected void initUi() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initListeners() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}
}
