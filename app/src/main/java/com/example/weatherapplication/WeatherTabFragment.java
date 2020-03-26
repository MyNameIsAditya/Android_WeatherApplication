package com.example.weatherapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class WeatherTabFragment extends Fragment
{
    private static final String API = "https://api.darksky.net/forecast/977ebd98af468744b53e30c37a05d2c0/";
    private static View view;
    public static String coordinates = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.weather_tab_fragment, container, false);
        return view;
    }

    public static void updateWeatherInformation()
    {
        getJSON(view.getContext(), coordinates);
    }

    public static void getJSON (Context context, String coord)
    {
        //HTTP GET - Volley Request
        String urlAddress = API + coord;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, urlAddress, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        try
                        {
                            TextView locationTextView = (TextView) view.findViewById(R.id.locationTextView);
                            TextView summaryTextView = (TextView) view.findViewById(R.id.summaryTextView);
                            TextView temperatureTextView = (TextView) view.findViewById(R.id.temperatureTextView);
                            TextView humidityTextView = (TextView) view.findViewById(R.id.humidityTextView);
                            TextView windSpeedTextView = (TextView) view.findViewById(R.id.windSpeedTextView);
                            TextView precipitationTextView = (TextView) view.findViewById(R.id.precipitationTextView);
                            TextView uvIndexTextView = (TextView) view.findViewById(R.id.uvIndexTextView);
                            TextView pressureTextView = (TextView) view.findViewById(R.id.pressureTextView);
                            TextView cloudCoverTextView = (TextView) view.findViewById(R.id.cloudCoverTextView);
                            TextView latitudeTextView = (TextView) view.findViewById(R.id.latitudeTextView);
                            TextView longitudeTextView = (TextView) view.findViewById(R.id.longitudeTextView);

                            locationTextView.setText(MapAndWeatherActivity.locationString);
                            temperatureTextView.setText(response.getJSONObject("currently").getString("temperature") + "°");
                            summaryTextView.setText(response.getJSONObject("currently").getString("summary"));
                            humidityTextView.setText("Humidity: " + response.getJSONObject("currently").getString("humidity"));
                            windSpeedTextView.setText("Wind Speed: " + response.getJSONObject("currently").getString("windSpeed"));
                            precipitationTextView.setText("Precipitation: " + response.getJSONObject("currently").getString("precipType"));
                            uvIndexTextView.setText("UV Index: " + response.getJSONObject("currently").getString("uvIndex"));
                            pressureTextView.setText("Pressure: " + response.getJSONObject("currently").getString("pressure"));
                            cloudCoverTextView.setText("Cloud Cover: " + response.getJSONObject("currently").getString("cloudCover"));
                            latitudeTextView.setText("Latitude: " + response.getString("latitude").substring(0, 6));
                            longitudeTextView.setText("Longitude: " + response.getString("longitude").substring(0, 6));
                        }
                        catch(Exception e)
                        {
                            //ERROR
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        //ERROR
                    }
                }
        );

        //Add to RequestQueue
        MapAndWeatherActivity.queue.add(getRequest);
    }
}
