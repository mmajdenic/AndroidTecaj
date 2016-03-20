package biz.osvit.zadatak2.adapters;

import java.util.ArrayList;

import biz.osvit.zadatak2.R;
import biz.osvit.zadatak2.models.UserModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<UserModel> mUsers;
	
	private ViewHolder mViewHolder;
	
	public UserAdapter(Context context, ArrayList<UserModel> korisnici) throws IllegalArgumentException {
		
		if(context==null){
			throw new IllegalArgumentException("Prazno");
		}
		if(korisnici==null){
			throw new IllegalArgumentException("prazna lista");
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
	
		
// Stari naèin
//		View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
//		TextView firstNameTextView = (TextView) view.findViewById(R.id.item_layout_ime);
//		TextView lastNameTextView = (TextView) view.findViewById(R.id.item_layout_prezime);
//		TextView telbrojTextView = (TextView) view.findViewById(R.id.item_layout_telbroj);
//		TextView spolTextView = (TextView) view.findViewById(R.id.item_layout_spol);
//		
//		UserModel korisnik = getItem(position);
//		
//		if(korisnik!=null){
//			
//			String tempFirstName = korisnik.getIme();
//			if (tempFirstName != null) {
//				firstNameTextView.setText(tempFirstName);
//			}else{
//				firstNameTextView.setText("Prazno");
//			}
//			
//			String tempLastName = korisnik.getPrezime();
//			if (tempLastName != null) {
//				lastNameTextView.setText(tempLastName);
//			}else{
//				lastNameTextView.setText("Prazno");
//			}
//			
//			String tempTelBroj = korisnik.getTelBroj();
//			if (tempTelBroj != null) {
//				telbrojTextView.setText(tempTelBroj);
//			}else{
//				telbrojTextView.setText("Prazno");
//			}
//			String tempSpol = korisnik.getSpol();
//			if (tempSpol != null) {
//				spolTextView.setText(tempSpol);
//			}else{
//				spolTextView.setText("Prazno");
//			}
//		}
//		
//		return View;
		
		if (convertView==null){
		convertView=LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
		
		mViewHolder = new ViewHolder();		
		initViewHolderChildWidgets(convertView);
		convertView.setTag(mViewHolder);
			
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		
		}
		
		UserModel user=getItem(position);
		if(user!=null) {
			initViewHolderChilds(user);
		}
		
		return convertView;
		
		
	}

	private void initViewHolderChilds(UserModel user) {
		String firstName = user.getIme();
		if (firstName != null) {
		mViewHolder.firstNameTextView.setText(firstName);
		}
		String lastName = user.getPrezime();
		if (lastName != null) {
		mViewHolder.lastNameTextView.setText(lastName);
		}
		String telbroj = user.getTelBroj();
		if (telbroj != null) {
		mViewHolder.telbrojTextView.setText(telbroj);
		}
		String spol = user.getSpol();
		if (spol != null) {
		mViewHolder.spolTextView.setText(spol);
		}
}
	
	private void initViewHolderChildWidgets(View convertView) {
		mViewHolder.firstNameTextView=(TextView) convertView.findViewById(R.id.item_layout_ime);
		mViewHolder.lastNameTextView=(TextView) convertView.findViewById(R.id.item_layout_prezime);
		mViewHolder.slikaImageView=(ImageView) convertView.findViewById(R.id.imageView1);
		mViewHolder.spolTextView=(TextView) convertView.findViewById(R.id.item_layout_spol);
		mViewHolder.telbrojTextView=(TextView) convertView.findViewById(R.id.item_layout_telbroj);
	}

	private static class ViewHolder {
		private TextView firstNameTextView;
		private TextView lastNameTextView;
		private TextView telbrojTextView;
		private TextView spolTextView;
		private ImageView slikaImageView;
	}
}
