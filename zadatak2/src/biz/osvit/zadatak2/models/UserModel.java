package biz.osvit.zadatak2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

	private String ime;
	private String prezime;
	private String telbroj;
	private String spol;

	public UserModel() {

	}

	private UserModel(Parcel in) {
		ime = in.readString();
		prezime = in.readString();
		telbroj = in.readString();
		spol = in.readString();
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

	public String getTelBroj() {
		return telbroj;
	}

	public void setTelBroj(String telbroj) {
		this.telbroj = telbroj;
		
	}

	
	
	public String getSpol() {
		return spol;
	}

	public void setSpol(String spol) {
		this.spol = spol;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel destination, int flags) {
		destination.writeString(ime);
		destination.writeString(prezime);
		destination.writeString(telbroj);
		destination.writeString(spol);

	}

	public static final Parcelable.Creator<UserModel> CREATOR = new Creator<UserModel>() {

		@Override
		public UserModel createFromParcel(Parcel source) {
			return new UserModel(source);
		}

		@Override
		public UserModel[] newArray(int size) {
			return new UserModel[size];
		}

	};

}
