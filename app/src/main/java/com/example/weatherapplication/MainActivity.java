package com.example.weatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private static final String MAP_AND_WEATHER = "map_and_weather";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void findWeather (View view)
    {
        //Check to see if location is entered
        TextView locationTextView = (TextView) findViewById(R.id.location_entry);
        String locationString = locationTextView.getText().toString();
        if ((locationString != null) && !(locationString.equals("")))
        {
            //Intent created to start second activity
            Intent intent = new Intent(this, MapAndWeatherActivity.class);
            intent.putExtra(MAP_AND_WEATHER, locationString);
            //Start new activity
            startActivity(intent);
        }
        else
        {
            Toast locationErrorToast = Toast.makeText(this, "Please Enter a Location", Toast.LENGTH_LONG);
            locationErrorToast.show();
        }
    }
}
