package hr.algebra.fotonaponskikalkulator.helpers;

import hr.algebra.fotonaponskikalkulator.dao.Orijentacija;
import hr.algebra.fotonaponskikalkulator.dao.Panel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class XMLParser extends DefaultHandler {
	
	private static final String TAG = XMLParser.class.getSimpleName();
	
	private static final int FLOW_INDEX_PANELS = 1;
	private static final int FLOW_INDEX_ORIENTACION = 2;
	private static final int FLOW_INDEX_CYTIS = 3;
	
	private int flowIndex;
	private Panel panel;
	private Orijentacija orijentacija;
	private String activeCityName;
	
	private boolean startTag;
	private String value;
	private int panelCounter = 0;
	private int orijentacijaCounter = 0;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		
		startTag = true;
		
		if(localName.equals("vrstapanela")) {
			// Log.d(TAG, "Odabrali ste " + attributes.getValue("name"));
			flowIndex = FLOW_INDEX_PANELS;
		} else if(localName.equals("orijentacije")) {
			flowIndex = FLOW_INDEX_ORIENTACION;
		} else if(localName.equals("gradovi")) {
			flowIndex = FLOW_INDEX_CYTIS;
		} else if(localName.equals("item")) {
			switch (flowIndex) {
			case FLOW_INDEX_PANELS:
				panel = new Panel();
				panel.setName(attributes.getValue("name"));				
				break;
			case FLOW_INDEX_ORIENTACION:
				orijentacija = new Orijentacija();
				orijentacija.setName(attributes.getValue("name"));				
				break;
			case FLOW_INDEX_CYTIS:
				activeCityName = attributes.getValue("name");
//				orijentacija = new Orijentacija();
//				orijentacija.setName(attributes.getValue("name"));				
				break;					
			default:
				break;
			}
		}
		
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		startTag = false;
		
		switch (flowIndex) {
		case FLOW_INDEX_PANELS:
			panel.setValue(Double.parseDouble(value));
			Settings.getInstance().addPanel(panel);
			break;
		case FLOW_INDEX_ORIENTACION:
			orijentacija.setValue(Double.parseDouble(value));
			Settings.getInstance().addOrijentacija(orijentacija);
			break;
		case FLOW_INDEX_CYTIS:
			Settings.getInstance().setCity(activeCityName.toLowerCase(), Integer.parseInt(value));
			break;				
		default:
			break;
		}
	}
	


	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		
		if(startTag) {
			value = new String(ch,start, length);
		}
	}
	
}
