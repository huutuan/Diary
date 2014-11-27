package com.example.newdiary;

public class Note {
	public int _id;
	public String _day_number;
	public String _day_txt;
	public String _month_year;
	public String _time;

	public String get_day_number() {
		return _day_number;
	}

	public void set_day_number(String _day_number) {
		this._day_number = _day_number;
	}

	public String get_day_txt() {
		return _day_txt;
	}

	public void set_day_txt(String _day_txt) {
		this._day_txt = _day_txt;
	}

	public String get_month_year() {
		return _month_year;
	}

	public void set_month_year(String _month_year) {
		this._month_year = _month_year;
	}

	public String get_time() {
		return _time;
	}

	public void set_time(String _time) {
		this._time = _time;
	}

	// Title and content
	public String _title;
	public String _content;

	public Note() {
	}

	// Constructor
	public Note(int id, String day_number, String day_txt, String month_year,
			String time, String title, String content) {
		_id = id;
		_day_number = day_number;
		_day_txt = day_txt;
		_month_year = month_year;
		_time = time;
		_title = title;
		_content = content;
	}

	// Constructor
	public Note(String day_number, String day_txt, String month_year,
			String time, String title, String content) {
		_day_number = day_number;
		_day_txt = day_txt;
		_month_year = month_year;
		_time = time;
		_title = title;
		_content = content;
	}

	// get id
	public int get_id() {
		return _id;
	}

	// set id
	public void set_id(int _id) {
		this._id = _id;
	}

	// get title
	public String get_title() {
		return _title;
	}

	// set title
	public void set_title(String _title) {
		this._title = _title;
	}

	// get content
	public String get_content() {
		return _content;
	}

	// set content
	public void set_content(String _content) {
		this._content = _content;
	}

}
