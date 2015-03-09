package com.example.mhealthapp;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;
import zh.wang.android.apis.yweathergetter4a.YahooWeather;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherExceptionListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeather.SEARCH_MODE;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener, YahooWeatherInfoListener,
YahooWeatherExceptionListener {

	private Button btnWeather;
	private TextView textviewweather;
	private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000, true);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textviewweather = (TextView) findViewById(R.id.weathertextview);
		btnWeather = (Button) findViewById(R.id.weatherbtn);
		btnWeather.setOnClickListener(this);
		mYahooWeather.setNeedDownloadIcons(true);
		mYahooWeather.setSearchMode(SEARCH_MODE.PLACE_NAME);
		mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), getLocation(), MainActivity.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.weatherbtn:
            Intent newWeather= new Intent(this, WeatherActivity.class);
            startActivity(newWeather);
            this.finish();
            break;
        }
		
	}
	
	public String getLocation() {
        SharedPreferences pref = getSharedPreferences("WEATHER", 0);
        return pref.getString("LOCATION", "Emmen");
    }
	

	@Override
	public void onFailConnection(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailParsing(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFailFindLocation(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gotWeatherInfo(WeatherInfo weatherInfo) {
		// TODO Auto-generated method stub
		 if (weatherInfo != null) {
				textviewweather.setText(weatherInfo.getTitle() + "\n"
						+ weatherInfo.getWOEIDneighborhood() + ", "
						+ weatherInfo.getWOEIDCounty() + ", "
						+ weatherInfo.getWOEIDState() + ", " 
						+ weatherInfo.getWOEIDCountry()+ "\n" + "\n" +
						"====== CURRENT ======" + "\n" +
			           "date: " + weatherInfo.getCurrentConditionDate() + "\n" +
			           "weather: " + weatherInfo.getCurrentText() + "\n" +
				       "temperature in ºC: " + weatherInfo.getCurrentTempC() + "\n" +
			           "temperature in ºF: " + weatherInfo.getCurrentTempF() + "\n" +
				       "wind chill in ºF: " + weatherInfo.getWindChill() + "\n" +
			           "wind direction: " + weatherInfo.getWindDirection() + "\n" +
				       "wind speed: " + weatherInfo.getWindSpeed() + "\n" +
			           "Humidity: " + weatherInfo.getAtmosphereHumidity() + "\n" +
				       "Pressure: " + weatherInfo.getAtmospherePressure() + "\n" +
			           "Visibility: " + weatherInfo.getAtmosphereVisibility()
				);
		 }
		 else{
			 textviewweather.setText("Please put a valid location in the weather settings or check your " +
			 		"internet connection.");
		 }
		
	}
	

}
