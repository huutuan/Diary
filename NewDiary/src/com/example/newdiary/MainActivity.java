package com.example.newdiary;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ListView list;
	ArrayList<Note> note_data = new ArrayList<Note>();
	Note_Adapter nAdapter;
	DatabaseHandler db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			list = (ListView) findViewById(R.id.list);
			list.setItemsCanFocus(false);
			registerForContextMenu(list);

			// note_data.clear();
			// db = new DatabaseHandler(this);
			// ArrayList<Note> note_array_from_db = db.Get_Notes();
			//
			// for (int i = 0; i < note_array_from_db.size(); i++) {
			//
			// int id = note_array_from_db.get(i).get_id();
			// String day_number = note_array_from_db.get(i).get_day_number();
			// String day_txt = note_array_from_db.get(i).get_day_txt();
			// String month_year = note_array_from_db.get(i).get_month_year();
			// String time = note_array_from_db.get(i).get_time();
			// String title = note_array_from_db.get(i).get_title();
			// String content = note_array_from_db.get(i).get_content();
			//
			// Note note = new Note();
			// note.set_id(id);
			// note.set_day_number(day_number);
			// note.set_day_txt(day_txt);
			// note.set_month_year(month_year);
			// note.set_time(time);
			// note.set_title(title);
			// note.set_content(content);
			//
			// note_data.add(note);
			// }
			// db.close();
			//
			// nAdapter = new Note_Adapter(MainActivity.this, R.layout.list_row,
			// note_data);
			// list.setAdapter(nAdapter);
			Set_refresh_data();

			list.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {

					Intent i = new Intent(MainActivity.this,
							ShowContentDiary.class);
					i.putExtra("DAY_NUMBER", note_data.get(position)
							.get_day_number());
					i.putExtra("DAY_TXT", note_data.get(position).get_day_txt());
					i.putExtra("MONTH_YEAR", note_data.get(position)
							.get_month_year());
					i.putExtra("TIME", note_data.get(position).get_time());
					i.putExtra("TITLE", note_data.get(position).get_title());
					i.putExtra("CONTENT", note_data.get(position).get_content());
					startActivity(i);

				}
			});

			// nAdapter.notifyDataSetChanged();
		} catch (Exception e) {
			Log.e("Some error", "" + e);
		}

	}

	public void Set_refresh_data() {
		note_data.clear();
		db = new DatabaseHandler(this);
		ArrayList<Note> note_array_from_db = db.Get_Notes();

		for (int i = 0; i < note_array_from_db.size(); i++) {

			int id = note_array_from_db.get(i).get_id();
			String day_number = note_array_from_db.get(i).get_day_number();
			String day_txt = note_array_from_db.get(i).get_day_txt();
			String month_year = note_array_from_db.get(i).get_month_year();
			String time = note_array_from_db.get(i).get_time();
			String title = note_array_from_db.get(i).get_title();
			String content = note_array_from_db.get(i).get_content();

			Note note = new Note();
			note.set_id(id);
			note.set_day_number(day_number);
			note.set_day_txt(day_txt);
			note.set_month_year(month_year);
			note.set_time(time);
			note.set_title(title);
			note.set_content(content);

			note_data.add(note);
		}
		db.close();

		nAdapter = new Note_Adapter(MainActivity.this, R.layout.list_row,
				note_data);
		list.setAdapter(nAdapter);
		nAdapter.notifyDataSetChanged();
	}

	public void onResume() {
		super.onResume();
		Set_refresh_data();
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenu.ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.list) {
			ListView list = (ListView) v;
			AdapterView.AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) menuInfo;

			menu.add("Delete");

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		final Context context = getApplicationContext();

		if (item.getTitle() == "Delete") {
			AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
			adb.setTitle("Delete?");
			adb.setMessage("Are you want to delete? ");

			// final int note_id = (int) info.id;
			final int note_id = (int) info.id;
			adb.setNegativeButton("Cancel", null);
			adb.setPositiveButton("OK", new AlertDialog.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					DatabaseHandler dBHandler = new DatabaseHandler(context);

					int id = nAdapter.getItem(note_id)._id;
					dBHandler.Delete_note(id);

					// Toast.makeText(getApplicationContext(), " " + id,
					// Toast.LENGTH_SHORT).show();

					MainActivity.this.onResume();

					Toast.makeText(getApplicationContext(), "Deleted",
							Toast.LENGTH_SHORT).show();
				}
			});
			adb.show();
			return true;
		}

		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
				.getActionView();
		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_search:
			return true;

		case R.id.action_add:
			Intent i = new Intent(MainActivity.this, AddNewDiary.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}
}
