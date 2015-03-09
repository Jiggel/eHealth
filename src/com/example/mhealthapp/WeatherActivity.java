package com.example.mhealthapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mhealthapp.R;

public class WeatherActivity extends ActionBarActivity {
	
	private EditText textweather;
	private Button btnweathersearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		textweather = (EditText) findViewById(R.id.weatheredit);
		textweather.setText(getLocation());
		btnweathersearch = (Button) findViewById(R.id.weathersearchbtn);
        btnweathersearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String _location = textweather.getText().toString();
				if (!TextUtils.isEmpty(_location)) {
					InputMethodManager imm = (InputMethodManager)getSystemService(
	              	      Context.INPUT_METHOD_SERVICE);
	              	imm.hideSoftInputFromWindow(textweather.getWindowToken(), 0);
	                setLocation(_location);
	                Toast.makeText(getApplicationContext(), "Settings saved", 3).show();
				} else {
					Toast.makeText(getApplicationContext(), "location is not inputted", 1).show();
				}
			}
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	          Intent parentIntent1 = new Intent(this,MainActivity.class);
	          startActivity(parentIntent1);
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	public String getLocation() {
        SharedPreferences pref = getSharedPreferences("WEATHER", 0);
        return pref.getString("LOCATION", "Emmen");
    }
	
	public void setLocation(String location) {
        SharedPreferences pref = getSharedPreferences("WEATHER", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("LOCATION", location);
        editor.commit();
    }

}
