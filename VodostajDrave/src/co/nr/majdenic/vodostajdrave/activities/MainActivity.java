package co.nr.majdenic.vodostajdrave.activities;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.nr.majdenic.vodostajdrave.R;
import co.nr.majdenic.vodostajdrave.handler.ServiceHandler;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends BaseActivity{

	
	
	private ProgressDialog pDialog;

	// URL to get contacts JSON
	private static String url = "http://majdenic.comli.com/vodostaj.php";

	// JSON Node names
	private static final String TAG_DATUM_SAT = "datum_sat";
	private static final String TAG_VODOSTAJ = "vodostaj";
	

	// contacts JSONArray
	JSONArray vodostaj = null;

	// Hashmap for ListView
	ArrayList<HashMap<String, String>> vodostajList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		vodostajList = new ArrayList<HashMap<String, String>>();

//		ListView lv = getListView();

//		// Listview on item click listener
//		lv.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//				// getting values from selected ListItem
//				String name = ((TextView) view.findViewById(R.id.activity_list_datum_sat_label))
//						.getText().toString();
//				String cost = ((TextView) view.findViewById(R.id.activity_list_vodostaj_label))
//						.getText().toString();
//				
//				// Starting single contact activity
//				Intent in = new Intent(getApplicationContext(),
//						VodostajActivity.class);
//				in.putExtra(TAG_DATUM_SAT, name);
//				in.putExtra(TAG_VODOSTAJ, cost);
//				startActivity(in);
//
//			}
//
//		});

		// Calling async task to get json
		new GetVodostaj().execute();
	}

	private ListView getListView() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Async task class to get json by making HTTP call
	 * */
	private class GetVodostaj extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service handler class instance
			ServiceHandler sh = new ServiceHandler();

			// Making a request to url and getting response
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + jsonStr);

			if (jsonStr != null) {
				try {
					JSONObject jsonObj = new JSONObject(jsonStr);
					
					// Getting JSON Array node
					vodostaj = jsonObj.getJSONArray(TAG_DATUM_SAT);

					// looping through All Contacts
					for (int i = 0; i < vodostaj.length(); i++) {
						JSONObject c = vodostaj.getJSONObject(i);
						
						String vrijeme = c.getString(TAG_DATUM_SAT);
						String voda = c.getString(TAG_VODOSTAJ);


						// tmp hashmap for single contact
						HashMap<String, String> vodostaj = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						vodostaj.put(TAG_DATUM_SAT, vrijeme);
						vodostaj.put(TAG_VODOSTAJ, voda);


						// adding contact to contact list
						vodostajList.add(vodostaj);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(
					MainActivity.this, vodostajList,
					R.layout.activity_list, new String[] { TAG_DATUM_SAT, TAG_VODOSTAJ },
							new int[] { R.id.activity_list_datum_sat_label,
							R.id.activity_list_vodostaj_label });

			setListAdapter(adapter);
		}

		private void setListAdapter(ListAdapter adapter) {
			// TODO Auto-generated method stub

		}

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

