package biz.osvit.addbutton.adapters;

import java.util.ArrayList;

import biz.osvit.addbutton.R;
import biz.osvit.addbutton.models.UserModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class UsersAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<UserModel> mUsers;
	
	
	public UsersAdapter(Context context, ArrayList<UserModel> korisnici) throws IllegalArgumentException {
		
		if(context==null){
			throw new IllegalArgumentException("Majstore context ti je prazan");
		}
		if(korisnici==null){
			throw new IllegalArgumentException("Majstore napuni listu");
		}
	
	mContext = context;
	mUsers = korisnici;
	
	}
	
	@Override
	public int getCount() {
		return mUsers.size();
	}

	@Override
	public UserModel getItem(int position) {
		return mUsers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view = LayoutInflater.from(mContext).inflate(R.layout.item_activity_second, parent, false);
		TextView firstNameTextView = (TextView) view.findViewById(R.id.item_activity_second_ime);
		TextView lastNameTextView = (TextView) view.findViewById(R.id.item_activity_second_prezime);
		TextView adressTextView = (TextView) view.findViewById(R.id.item_activity_second_adresa);
		
		UserModel korisnik = getItem(position);
		
		if(korisnik!=null){
			
			String tempFirstName = korisnik.getIme();
			if (tempFirstName != null) {
				firstNameTextView.setText(tempFirstName);
			}else{
				firstNameTextView.setText("Prazno");
			}
			
			String tempLastName = korisnik.getPrezime();
			if (tempLastName != null) {
				lastNameTextView.setText(tempLastName);
			}else{
				lastNameTextView.setText("Prazno");
			}
			
			String tempAdress = korisnik.getAdresa();
			if (tempAdress != null) {
				adressTextView.setText(tempAdress);
			}else{
				adressTextView.setText("Prazno");
			}
		}
		
		return view;
		
		
	}

}
