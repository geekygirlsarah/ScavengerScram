package com.terrorbytes.scavengerscram;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.terrorbytes.scavengerscram.model.Game;

public class GameListAdapter extends BaseAdapters {
	private static ArrayList<Game> games;
	private LayoutInflater mInflater;
	
	public GameListAdapter(Context context, ArrayList<Game> games)
	{
		this.games = games;
		mInflater = LayoutInflater.from(context);
	}

	 public int getCount() {
	  return games.size();
	 }

	 public Object getItem(int position) {
	  return games.get(position);
	 }

	 public long getItemId(int position) {
	  return position;
	 }

	 public View getView(int position, View convertView, ViewGroup parent) {
	  ViewHolder holder;
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.custom_row_view, null);
	   holder = new ViewHolder();
	   holder.gameName = (TextView) convertView.findViewById(R.id.gameName);
	   holder.gameDescription = (TextView) convertView.findViewById(R.id.gameDescription);
	  

	   convertView.setTag(holder);
	  } else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  holder.gameName.setText(games.get(position).getName());
	  holder.gameDescription.setText(games.get(position).getDescription());
	

	  return convertView;
	 }

	 static class ViewHolder {
	  TextView gameName;
	  TextView gameDescription;
	  
	 }
	}