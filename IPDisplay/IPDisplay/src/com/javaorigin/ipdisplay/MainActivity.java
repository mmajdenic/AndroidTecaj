package com.javaorigin.ipdisplay;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	             NetworkInterface intf = en.nextElement();
	             for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                 InetAddress inetAddress = enumIpAddr.nextElement();
	                 if (!inetAddress.isLoopbackAddress()) {
	                	TextView ipView= (TextView) findViewById(R.string.ip);
	                	ipView.setText(inetAddress.getHostAddress());
	                 }
	             }
	         }
		} catch (Exception e) {
			Log.e("------------", e.toString());
		}
		 
	}


}
