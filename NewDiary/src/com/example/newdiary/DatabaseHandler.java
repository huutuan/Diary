package com.example.newdiary;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	// All static variable

	// Notes table columns name
	private static final String KEY_DAY_NUMBER = "dayNumber";
	private static final String KEY_DAY_TXT = "dayText";
	private static final String KEY_MONTH_YEAR = "monthYear";
	private static final String KEY_TIME = "time";
	private static final String KEY_TITLE = "title";
	private static final String KEY_CONTENT = "content";
	private static final String KEY_ID = "id";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "diaryManager";

	// Table name
	private static final String TABLE_NAME = "notes";

	private final ArrayList<Note> note_list = new ArrayList<Note>();

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Table
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_DAY_NUMBER
				+ " TEXT NOT NULL," + KEY_DAY_TXT + " TEXT NOT NULL,"
				+ KEY_MONTH_YEAR + " TEXT NOT NULL," + KEY_TIME
				+ " TEXT NOT NULL," + KEY_TITLE + " TEXT NOT NULL,"
				+ KEY_CONTENT + " TEXT NOT NULL" + " )";
		db.execSQL(CREATE_NOTES_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop oldTable if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create table again
		onCreate(db);
	}

	// Adding new note
	public void Add_Note(Note note) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_DAY_NUMBER, note.get_day_number());
		values.put(KEY_DAY_TXT, note.get_day_txt());
		values.put(KEY_MONTH_YEAR, note.get_month_year());
		values.put(KEY_TIME, note.get_time());
		values.put(KEY_TITLE, note.get_title());
		values.put(KEY_CONTENT, note.get_content());

		db.insert(TABLE_NAME, null, values);
		db.close(); // Close database connection
	}

	// Getting a note
	public Note Get_Note(int id) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
				KEY_DAY_NUMBER, KEY_DAY_TXT, KEY_MONTH_YEAR, KEY_TIME,
				KEY_TITLE, KEY_CONTENT }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();
		Note note = new Note(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5), cursor.getString(6));
		cursor.close();
		db.close();

		return note;

	}

	// Getting all notes
	public ArrayList<Note> Get_Notes() {
		try {
			note_list.clear();

			// Select all query
			String selectQuery = "SELECT * FROM " + TABLE_NAME;
			SQLiteDatabase db = getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// Looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					Note note = new Note();
					note.set_id(Integer.parseInt(cursor.getString(0)));
					note.set_day_number(cursor.getString(1));
					note.set_day_txt(cursor.getString(2));
					note.set_month_year(cursor.getString(3));
					note.set_time(cursor.getString(4));
					note.set_title(cursor.getString(5));
					note.set_content(cursor.getString(6));

					// Adding note to list
					note_list.add(note);
				} while (cursor.moveToNext());
			}

			// Return note list
			cursor.close();
			db.close();
			return note_list;
		} catch (Exception e) {
			Log.e("all_contact", "" + e);
		}
		return note_list;
	}

	// Deleting a note
	public void Delete_note(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_NAME, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	// public boolean Delete_note(long id) {
	// SQLiteDatabase db = this.getWritableDatabase();
	// return db.delete(TABLE_NAME, KEY_ID + "=" + id, null) != 0;
	// }

}
