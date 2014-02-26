package biz.osvit.addbutton.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

	private String ime;
	private String prezime;
	private String adresa;
	
	public UserModel() {
		
	}
	
	// model sa stanjima koji se prenosi, redosljed mora biti jedank kao što smo definirali i gore
	private UserModel(Parcel in) {
		ime=in.readString();
		prezime=in.readString();
		adresa=in.readString();
	}
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	@Override
	public int describeContents() {
		// tu ne radim ništa
		return 0;
	}
	@Override
	public void writeToParcel(Parcel destination, int flags) {
		destination.writeString(ime);
		destination.writeString(prezime);
		destination.writeString(adresa);
				
	}
	
	public static final Parcelable.Creator<UserModel> CREATOR = new Creator<UserModel>() {

		@Override
		public UserModel createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new UserModel(source);
		}

		@Override
		public UserModel[] newArray(int size) {
			// TODO Auto-generated method stub
		return new UserModel[size];
		}
		
	};
	
}
