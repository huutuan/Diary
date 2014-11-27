package com.example.newdiary;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Note_Adapter extends ArrayAdapter<Note> {
	Activity context;
	int layoutResourceId;
	Note note;
	ArrayList<Note> data = new ArrayList<Note>();

	public Note_Adapter(Activity ctx, int layoutResourceId, ArrayList<Note> data) {
		super(ctx, layoutResourceId, data);
		this.context = ctx;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.list_row, null, true);

		TextView txtDayNumber = (TextView) rowView
				.findViewById(R.id.txt_number_day);
		TextView txtDayTxt = (TextView) rowView.findViewById(R.id.txt_day);
		TextView txtMonthYear = (TextView) rowView
				.findViewById(R.id.txt_month_year);
		TextView txtTime = (TextView) rowView.findViewById(R.id.txt_time);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt_title);
		TextView txtContent = (TextView) rowView.findViewById(R.id.txt_content);

		note = data.get(position);
		txtDayNumber.setText(note.get_day_number());
		txtDayTxt.setText(note.get_day_txt());
		txtMonthYear.setText(note.get_month_year());
		txtTime.setText(note.get_time());
		txtTitle.setText(note.get_title());
		txtContent.setText(note.get_content());

		return rowView;
	}

}
