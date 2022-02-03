package com.uzair.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeatherDetails(33.6617, 73.0568);
    }

    private void getWeatherDetails(double lat, double lng) {
        String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lng + "&appid=d23c66ea0f5b306ca7ca393069eeacc0";
        ImageView weatherIcon = findViewById(R.id.weatherIcon);
        TextView tempText = findViewById(R.id.tempText);

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest weatherJsonRequest =
                new JsonObjectRequest(Request.Method.GET, weatherUrl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                Gson gson = new Gson();
                                WeatherApi weatherApi = gson.fromJson(response.toString(), WeatherApi.class);

                                double temp = weatherApi.getMain().getTemp() - 273.15;
                                tempText.setText(new DecimalFormat("##.#").format(temp)+ "°C");
                                setWeatherImage(weatherIcon, weatherApi.getWeather().get(0).getIcon());

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "onErrorResponse: " + error.getMessage());
                    }
                });


        requestQueue.add(weatherJsonRequest);


    }

    private void setWeatherImage(ImageView weatherIcon, String icon) {
        switch (icon) {
            case "01d": {
                weatherIcon.setImageResource(R.drawable.day1);
                break;
            }
            case "02d": {
                weatherIcon.setImageResource(R.drawable.day2);
                break;
            }
            case "03d": {
                weatherIcon.setImageResource(R.drawable.day3);
                break;
            }
            case "04d": {
                weatherIcon.setImageResource(R.drawable.day4);
                break;
            }
            case "09d": {
                weatherIcon.setImageResource(R.drawable.day9);
                break;
            }
            case "10d": {
                weatherIcon.setImageResource(R.drawable.day10);
                break;
            }
            case "11d": {
                weatherIcon.setImageResource(R.drawable.day11);
                break;
            }
            case "13d": {
                weatherIcon.setImageResource(R.drawable.day13);
                break;
            }
            case "50d": {
                weatherIcon.setImageResource(R.drawable.day50);
                break;
            }

            case "01n": {
                weatherIcon.setImageResource(R.drawable.night1);
                break;
            }
            case "02n": {
                weatherIcon.setImageResource(R.drawable.night2);
                break;
            }
            case "03n": {
                weatherIcon.setImageResource(R.drawable.night3);
                break;
            }
            case "04n": {
                weatherIcon.setImageResource(R.drawable.night4);
                break;
            }
            case "09n": {
                weatherIcon.setImageResource(R.drawable.night9);
                break;
            }
            case "10n": {
                weatherIcon.setImageResource(R.drawable.night10);
                break;
            }
            case "11n": {
                weatherIcon.setImageResource(R.drawable.night11);
                break;
            }
            case "13n": {
                weatherIcon.setImageResource(R.drawable.night13);
                break;
            }
            case "50n": {
                weatherIcon.setImageResource(R.drawable.night50);
                break;
            }


        }
    }
}