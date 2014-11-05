package com.liananse.uapp.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.widget.BaseAdapter;

public abstract class UBaseAdapter<T> extends BaseAdapter {

	protected Context ctx;
	protected List<T> data;

	public UBaseAdapter(Context ctx, List<T> data) {
		this.ctx = ctx;
		this.data = data == null ? new ArrayList<T>() : new ArrayList<T>(data);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		if (position >= data.size()) {
			return null;
		}
		
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	
}
