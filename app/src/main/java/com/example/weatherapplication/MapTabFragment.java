package com.example.weatherapplication;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapTabFragment extends Fragment implements OnMapReadyCallback
{
    private View view;
    private MapView mapView;
    private GoogleMap mapGoogle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.map_tab_fragment, container, false);

        //Instantiates MapView
        Bundle mapViewBundle = null;
        if (savedInstanceState != null)
        {
            mapViewBundle = savedInstanceState.getBundle(MapAndWeatherActivity.MAPVIEW_BUNDLE_KEY);
        }
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mapGoogle = googleMap;

        //mapGoogle.getUiSettings().setMyLocationButtonEnabled(false);
        //mapGoogle.setMyLocationEnabled(true);

        // Updates the location and zoom of the MapView
        /*CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);*/

        // Add a Marker for Searched Location
        Geocoder geocoder = new Geocoder(view.getContext());
        List<Address> list = new ArrayList<Address>();
        try
        {
            list = geocoder.getFromLocationName(MapAndWeatherActivity.locationString, 1);
        }
        catch (IOException e)
        {
        }
        if (list.size() > 0)
        {
            MapAndWeatherActivity.address = list.get(0);

            //Update Weather Information
            WeatherTabFragment.coordinates = MapAndWeatherActivity.address.getLatitude() + "," + MapAndWeatherActivity.address.getLongitude();
            WeatherTabFragment.updateWeatherInformation();

            //Move MapView to Location with Marker
            LatLng locationLatLng = new LatLng(MapAndWeatherActivity.address.getLatitude(), MapAndWeatherActivity.address.getLongitude());
            mapGoogle.addMarker(new MarkerOptions().position(locationLatLng).title("Marker in " + MapAndWeatherActivity.locationString));
            //mapGoogle.moveCamera(CameraUpdateFactory.newLatLng(locationLatLng));
            mapGoogle.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, 10));
        }
        else                                                        //If the location does not exist
        {
            Toast locationErrorToast = Toast.makeText(view.getContext(), "Invalid Location", Toast.LENGTH_LONG);
            locationErrorToast.show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MapAndWeatherActivity.MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null)
        {
            mapViewBundle = new Bundle();
            outState.putBundle(MapAndWeatherActivity.MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mapView.onResume();
        //super.onResume();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause()
    {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy()
    {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
