package com.example.newdiary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText password;
	private Button login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.login);
		super.onCreate(savedInstanceState);

		getActionBar().hide();

		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login_btn);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (password.getText().toString().equals("facetuan")) {
					Intent i = new Intent(LoginActivity.this,
							MainActivity.class);
					startActivity(i);
				} else
					Toast.makeText(getApplicationContext(),
							"Password is not true. Please enter again!",
							Toast.LENGTH_SHORT).show();
			}
		});
	}
}
