package com.example.weatherapplication;

import android.location.Address;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

public class MapAndWeatherActivity extends AppCompatActivity
{
    private static final String MAP_AND_WEATHER = "map_and_weather";
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    public static String locationString;
    public static Address address = null;
    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_and_weather);

        locationString = getIntent().getStringExtra(MAP_AND_WEATHER);
        //address = new Address(Locale.US);///////////////////////////////////////////////////////////////////////////////

        //Volley - Setup Request Queue
        queue = Volley.newRequestQueue(this);

        //Android UI - Tab Layout-----------------------------------------------------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapTabFragment(), "Map");
        adapter.addFragment(new WeatherTabFragment(), "Weather");
        final ViewPager viewPager =(ViewPager)findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        //----------------------------------------------------------------------------------
    }
}
