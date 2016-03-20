package biz.osvit.solarkalkulator.activities;

import biz.osvit.solarkalkulator.R;
import biz.osvit.solarkalkulator.models.Orijentacija;
import biz.osvit.solarkalkulator.models.Panel;
import biz.osvit.solarkalkulator.utils.Settings;
import biz.osvit.solarkalkulator.utils.XMLParser;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXResult;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private static final String TAG = MainActivity.class.getSimpleName();

	protected static final int REQUEST_CODE_GET_CITY_ACTIVITY = 1;

	TextView snagaPanela;
	TextView kutNagiba;
	Button izracunBtn;
	Button regijaBtn;
	Spinner spinnerTipPanela;
	Spinner spinnerOrijentacija;
	String imeGrada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		 * Set views
		 */
		snagaPanela = (TextView) findViewById(R.id.activity_main_snaga_panela_input);
		kutNagiba = (TextView) findViewById(R.id.activity_main_kut_nagiba_unos);
		izracunBtn = (Button) findViewById(R.id.activity_main_izracun_btn);
		spinnerTipPanela = (Spinner) findViewById(R.id.activity_main_tip_panela_spinner);
		spinnerOrijentacija = (Spinner) findViewById(R.id.activity_main_orijentacija_spinner);
		regijaBtn = (Button) findViewById(R.id.activity_main_odaberite_regiju);

		/*
		 * Set listiners
		 */
		izracunBtn.setOnClickListener(this);
		spinnerTipPanela.setOnItemSelectedListener(this);
		spinnerOrijentacija.setOnItemSelectedListener(this);

		regijaBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (v.getId() == R.id.activity_main_odaberite_regiju) {
					Log.i("clicks", "You Clicked B1");
					Intent resultIntent = new Intent(MainActivity.this,
							KartaActivity.class);
					// resultIntent.putExtra(PUBLIC_STATIC_STRING_IDENTIFIER,
					// cityName);
					startActivityForResult(resultIntent,
							REQUEST_CODE_GET_CITY_ACTIVITY);
				}

			}
		});

		try {
			parseXmlTest();
		} catch (Exception e) {
			Log.e(TAG, "Error parsing XML file", e);

		}
	}

	@Override
	public void onClick(View arg0) {
		try {
			calculate();
		} catch (Exception ex) {
			Toast toast = Toast.makeText(getApplicationContext(),
					"Niste izvršili odabir!", Toast.LENGTH_SHORT);
			toast.show();
			toast.setGravity(Gravity.CENTER, 0, 0);
			Log.e(TAG, "Error in Calculate", ex);

		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
	}

	private void calculate() {
		Panel panel;
		Orijentacija orijentacija;

		panel = Settings.getInstance().getPanel(
				(int) spinnerTipPanela.getSelectedItemId());
		orijentacija = Settings.getInstance().getOrijentacija(
				(int) spinnerOrijentacija.getSelectedItemId());

		double panelKoef = panel.getValue();
		double orijent = orijentacija.getValue();
		EditText snaga = (EditText) findViewById(R.id.activity_main_snaga_panela_input);
		double snagad = Double.valueOf(snaga.getText().toString());
		EditText kutic = (EditText) findViewById(R.id.activity_main_kut_nagiba_unos);
		double kuticd = Double.valueOf(kutic.getText().toString());
		// int cityValue = getCityValue();
		int cityValue = Settings.getInstance().getCityValue(imeGrada);

		double beta = Math.abs(kuticd - 36);

		Log.d(TAG, "beta " + beta);

		double radovkut = cityValue * (1 - (0.000119 * beta * beta));

		Log.d(TAG, "radijacija ovisna o kutu " + radovkut);

		double izracun = panelKoef * orijent * snagad * radovkut;
		Log.d(TAG, String.format(
				"panelKoef=%s orijent=%s snagad=%s radovkut=%s", panelKoef,
				orijent, snagad, radovkut));

		DecimalFormat df = new DecimalFormat("#.##");
		Log.d(TAG, "" + snagad);
		Log.d(TAG, "" + kuticd);
		Log.d(TAG, "" + izracun);
		Log.d(TAG, "" + cityValue);

		TextView izracunView = (TextView) findViewById(R.id.activity_main_izracun);
		izracunView.setText(" " + df.format(izracun) + "  kWh");
		// Log.d(TAG, "Ovo je je panel: " + panel.getValue());
		// Log.d(TAG, "Ovo je je orijentacija: " + orijentacija.getValue());

		// Intent intent = new Intent(getBaseContext(), Karta.class);
		// startActivity(intent);
	}

	private void parseXmlTest() throws ParserConfigurationException,
			SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		XMLReader reader = parser.getXMLReader();

		XMLParser myXmlParser = new XMLParser();

		reader.setContentHandler(myXmlParser);

		InputStream stream = getAssets().open("vrstapanela.xml");

		reader.parse(new InputSource(stream));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case REQUEST_CODE_GET_CITY_ACTIVITY:
			Bundle bundle = data.getExtras();

			imeGrada = bundle.getString(KartaActivity.KEY_CITY, "");
			int cityValue = Settings.getInstance().getCityValue(imeGrada);
			TextView prikazPoruke = (TextView) findViewById(R.id.activity_main_odabrana_regija);
			prikazPoruke.setText(imeGrada);

			Log.d(TAG, "grad: " + imeGrada + " value: " + cityValue);
			break;
		default:
			break;
		}
	}

	private int getCityValue() {
		return Settings.getInstance().getCityValue(imeGrada);
	}

}
