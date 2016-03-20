package biz.osvit.solarkalkulator.activities;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import biz.osvit.solarkalkulator.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class KartaActivity extends Activity implements OnTouchListener {

	// FrameLayout map;
	private static final int TOLERANCE = 25;

	public static final String KEY_CITY = "hr.algebra.fotonaponskikalkulator.city";

	ImageView myMap;
	ImageView myMapMask;
	TextView selectedCityNameView;

	private Map<String, Integer> cityMap = new HashMap<String, Integer>();

	private Intent resultIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_karta);

		myMap = (ImageView) findViewById(R.id.image_map);
		myMapMask = (ImageView) findViewById(R.id.image_map_mask);
		selectedCityNameView = (TextView) findViewById(R.id.izabraniGrad);

		myMap.setOnTouchListener(this);

		initCityList();
	}

	private void initCityList() {
		cityMap.put("Osijek", Color.rgb(255, 0, 255));
		cityMap.put("Zagreb", Color.rgb(128, 255, 128));
		cityMap.put("Varaždin", Color.rgb(255, 128, 128));
		cityMap.put("Koprivnica", Color.rgb(255, 255, 128));
		cityMap.put("Bjelovar", Color.rgb(0, 255, 128));
		cityMap.put("Virovitica", Color.rgb(128, 255, 255));
		cityMap.put("Rijeka", Color.rgb(0, 128, 255));
		cityMap.put("Umag", Color.rgb(255, 128, 255));
		cityMap.put("Karlovac", Color.rgb(255, 0, 0));
		cityMap.put("Sisak", Color.rgb(255, 255, 0));
		cityMap.put("Požega", Color.rgb(128, 255, 0));
		cityMap.put("Slavonski Brod", Color.rgb(0, 255, 255));
		cityMap.put("Vinkovci", Color.rgb(128, 128, 255));
		cityMap.put("Pula", Color.rgb(255, 0, 128));
		cityMap.put("Gospiæ", Color.rgb(128, 0, 0));
		cityMap.put("Šibenik", Color.rgb(0, 0, 255));
		cityMap.put("Zadar", Color.rgb(255, 128, 0));
		cityMap.put("Knin", Color.rgb(0, 128, 0));
		cityMap.put("Split", Color.rgb(128, 0, 128));
		cityMap.put("Korèula", Color.rgb(128, 0, 255));
		cityMap.put("Metkoviæ", Color.rgb(0, 0, 128));
		cityMap.put("Dubrovnik", Color.rgb(128, 128, 0));
		// ...
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.karta, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();

		Log.d("Main", "click");

		myMapMask.setDrawingCacheEnabled(true);
		Bitmap bitmap = Bitmap.createBitmap(myMapMask.getDrawingCache());
		myMapMask.setDrawingCacheEnabled(false);

		int color = bitmap.getPixel(x, y);

		String cityName = getCity(color);
		if (selectedCityNameView != null) {
			selectedCityNameView.setText(cityName);
			resultIntent = new Intent();
			resultIntent.putExtra(KEY_CITY, cityName);
			setResult(MainActivity.RESULT_OK, resultIntent);
			finish();

		} else {
			selectedCityNameView.setText("");
		}

		Log.d("Main", "City: " + cityName);

		return true;
	}

	public boolean closeMatch(int color1, int color2) {
		if ((int) Math.abs(Color.red(color1) - Color.red(color2)) > TOLERANCE)
			return false;
		if ((int) Math.abs(Color.green(color1) - Color.green(color2)) > TOLERANCE)
			return false;
		if ((int) Math.abs(Color.blue(color1) - Color.blue(color2)) > TOLERANCE)
			return false;
		return true;
	}

	private String getCity(int selectedColor) {
		for (Entry<String, Integer> item : cityMap.entrySet()) {
			if (closeMatch(item.getValue(), selectedColor)) {
				return item.getKey();
			}
		}
		return null;
	}

}