package hr.algebra.fotonaponskikalkulator.helpers;

import hr.algebra.fotonaponskikalkulator.dao.Orijentacija;
import hr.algebra.fotonaponskikalkulator.dao.Panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class Settings {
	
	private static Settings INSTANCE;
	
	private List<Panel> panelList = new ArrayList<Panel>();
	private List<Orijentacija> orijentacijaList = new ArrayList<Orijentacija>();
	
	private Map<String, Integer> cityList = new HashMap<String, Integer>();
	
	private Settings() {		
	}
	
	public static Settings getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Settings();
		}
		return INSTANCE;
	}
	
	public void addPanel(Panel panel) {
		panelList.add(panel);
	}
	
	public Panel getPanel(int index) {
		if(index > panelList.size()) {
			return null;
		} else {
			return panelList.get(index);
		}
	}

	public void addOrijentacija(Orijentacija orijentacija) {
		orijentacijaList.add(orijentacija);
	}
	
	public Orijentacija getOrijentacija(int index) {
		if(index > orijentacijaList.size()) {
			return null;
		} else {
			return orijentacijaList.get(index);
		}
	}
	
	public void setCity(String name, int value) {
		cityList.put(name, value);
	}
	
	public int getCityValue(String name) {
		String cityName = name.toLowerCase();
		if(cityList.containsKey(cityName)) {
			return cityList.get(cityName);
		} else {
			Log.e("", "No city in collection", new Exception("Error geting city value for " + cityName));
			return 0;
		}
	}
}
