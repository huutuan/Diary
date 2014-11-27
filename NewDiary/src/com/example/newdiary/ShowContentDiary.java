package com.example.newdiary;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ShowContentDiary extends Activity {
	private DatabaseHandler dbHandle;
	private SQLiteDatabase db;
	private TextView day_number;
	private TextView day_txt;
	private TextView month_year;
	private TextView time;
	private TextView content_diary;
	private TextView title_diary;
	private Long rowId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.show_content_diary);
		super.onCreate(savedInstanceState);

		getActionBar().hide();

		day_number = (TextView) findViewById(R.id.txt_number_day_show);
		day_txt = (TextView) findViewById(R.id.txt_day_show);
		month_year = (TextView) findViewById(R.id.txt_month_year_show);
		time = (TextView) findViewById(R.id.txt_time_show);
		content_diary = (TextView) findViewById(R.id.show_content);
		title_diary = (TextView) findViewById(R.id.show_title);

		dbHandle = new DatabaseHandler(this);
		db = dbHandle.getWritableDatabase();

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String day_number = (String) extras.get("DAY_NUMBER");
			String day_txt = (String) extras.get("DAY_TXT");
			String month_year = (String) extras.get("MONTH_YEAR");
			String time = (String) extras.get("TIME");
			String title = (String) extras.get("TITLE");
			String content = (String) extras.get("CONTENT");
			if (day_number != null)
				this.day_number.setText(day_number);
			if (day_txt != null)
				this.day_txt.setText(day_txt);
			if (month_year != null)
				this.month_year.setText(month_year);
			if (time != null)
				this.time.setText(time);
			if (title != null)
				title_diary.setText(title);
			if (content != null)
				content_diary.setText(content);
		}

	}

}
