package com.terrorbytes.scavengerscram;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.terrorbytes.scavengerscram.model.Clue;

public class ClueListAdapter extends BaseAdapter {
	private List<Clue> clues;
	private LayoutInflater mInflater;
	
	public ClueListAdapter(Context context, List<Clue> clues)
	{
		this.clues = clues;
		mInflater = LayoutInflater.from(context);
	}

	 public int getCount() {
	  return clues.size();
	 }

	 public Object getItem(int position) {
	  return clues.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.clue_custom_row_view, null);
	   holder = new ViewHolder();
	   holder.ClueTitle = (TextView) convertView.findViewById(R.id.clueTitle);
	   holder.ClueDescription = (TextView) convertView.findViewById(R.id.clueDescription);
	  

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.ClueTitle.setText(clues.get(position).getTitle());
	  holder.ClueDescription.setText(clues.get(position).getDescription());
	

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView ClueTitle;
	  TextView ClueDescription;
	  
	 }
	}