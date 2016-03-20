package com.comli.majdenic.jsonparser1;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
  ListView date_time;
  TextView vodostaj;

  Button Btngetdata;
  ArrayList<HashMap<String, String>> oslist = new ArrayList<HashMap<String, String>>();
  //URL to get JSON Array
  private static String url = "http://majdenic.comli.com/vodostaj.php";
  //JSON Node Names
  private static final String TAG_OS = "date_time";
  private static final String TAG_VER = "vodostaj";

  JSONArray android = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        oslist = new ArrayList<HashMap<String, String>>();
        Btngetdata = (Button)findViewById(R.id.getdata);
        Btngetdata.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
             new JSONParse().execute();
      }
    });
    }
    private class JSONParse extends AsyncTask<String, String, JSONObject> {
       private ProgressDialog pDialog;
      @Override
        protected void onPreExecute() {
            super.onPreExecute();
             vodostaj = (TextView)findViewById(R.id.vers);
       
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
      }
      @Override
        protected JSONObject doInBackground(String... args) {
        JSONParser jParser = new JSONParser();
        // Getting JSON from URL
        JSONObject json = jParser.getJSONFromUrl(url);
        return json;
      }
       @Override
         protected void onPostExecute(JSONObject json) {
         pDialog.dismiss();
         try {
            // Getting JSON Array from URL
            android = json.getJSONArray(TAG_OS);
            for(int i = 0; i < android.length(); i++){
            JSONObject c = android.getJSONObject(i);
            // Storing  JSON item in a Variable
            String ver = c.getString(TAG_OS);

            // Adding value HashMap key => value
            HashMap<String, String> map = new HashMap<String, String>();
            map.put(TAG_OS, ver);

            oslist.add(map);
            date_time=(ListView)findViewById(R.id.list);
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, oslist,
                R.layout.list_v,
                new String[] { TAG_OS }, new int[] {
                    R.id.vers,R.id.name});
            date_time.setAdapter(adapter);
            date_time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Toast.makeText(MainActivity.this, "You Clicked at "+oslist.get(+position).get("name"), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (JSONException e) {
          e.printStackTrace();
        }
       }
    }
}