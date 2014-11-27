package com.example.newdiary;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewDiary extends Activity {
	EditText diary_title, diary_content;
	TextView day_number, day_txt, month_year, time;
	String valid_title = null, valid_content = null, valid_id = "",
			valid_day_number = null, valid_day_txt = null,
			valid_month_year = null, valid_time = null;
	Button save, picture_add;
	DatabaseHandler dbHandler = new DatabaseHandler(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.add_new_diary);
		super.onCreate(savedInstanceState);

		getActionBar().hide();

		Calendar c = Calendar.getInstance();

		day_number = (TextView) findViewById(R.id.txt_number_day_new_diary);
		day_txt = (TextView) findViewById(R.id.txt_day_new_diary);
		month_year = (TextView) findViewById(R.id.txt_month_year_new_diary);
		time = (TextView) findViewById(R.id.txt_time_new_diary);

		int i = c.get(Calendar.DAY_OF_MONTH);
		valid_day_number = i + "";
		day_number.setText(valid_day_number);

		SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
		valid_day_txt = dayFormat.format(new Date());
		day_txt.setText(valid_day_txt);

		SimpleDateFormat monthAndYearFormat = new SimpleDateFormat("MMM yyyy");
		valid_month_year = monthAndYearFormat.format(new Date());
		month_year.setText(valid_month_year);

		SimpleDateFormat timeFormat = new SimpleDateFormat("KK:mm a");
		valid_time = timeFormat.format(new Date());
		time.setText(valid_time);

		diary_title = (EditText) findViewById(R.id.diary_title);
		diary_content = (EditText) findViewById(R.id.diary_content);
		save = (Button) findViewById(R.id.save_btn);
		picture_add = (Button) findViewById(R.id.add_picture);

		diary_title.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				if (diary_title.getText().toString().length() <= 0)
					valid_title = null;
				else
					valid_title = diary_title.getText().toString();

			}
		});

		diary_content.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (diary_content.getText().toString().length() <= 0)
					valid_content = null;
				else
					valid_content = diary_content.getText().toString();

			}
		});

		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (valid_title != null && valid_content != null) {
					dbHandler.Add_Note(new Note(valid_day_number,
							valid_day_txt, valid_month_year, valid_time,
							valid_title, valid_content));

					Toast.makeText(getApplicationContext(), "Saved!",
							Toast.LENGTH_SHORT).show();
					Intent i = new Intent(AddNewDiary.this, MainActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				} else
					Toast.makeText(
							getApplicationContext(),
							"Sorry, Some fields are missing. Please fill up all!",
							Toast.LENGTH_SHORT).show();
			}
		});

		picture_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Add new picture",
						Toast.LENGTH_SHORT).show();
			}
		});

	}
}
