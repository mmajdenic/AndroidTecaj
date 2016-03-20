// primjer Asyntask liba..

package com.comli.majdenic.jsonparser1;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.ImageView;

public class AskyntaskActivity {




public class LoadajSlikuSaBackenda extends AsyncTask<Void, Void, String> {

	private ImageView mImageView;
	private String mUrl;
	
	public LoadajSlikuSaBackenda (ImageView imageView, String url){
		
		mImageView = imageView;
		mUrl = url;
		
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	
	@Override
	protected String doInBackground(Void... params) {
		
		// init konekcije
		// skini podatke
		
		// ako je podatak !=null vrati podatak		
		
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		
	if (result != null){
		mImageView.setImageBitmap(result);
	}
	}
}
}