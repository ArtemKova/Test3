package com.ka.test3;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.views.overlay.ItemizedOverlay;
import org.osmdroid.LocationListenerProxy;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay.OnItemGestureListener;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.OverlayManager;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private MapController mapController;
    // private ItemizedOverlay<OverlayItem> myLocationOverlay;
    //  private LocationListenerProxy mResourceProxy;


    public static String TAG = "JSON";
    public int bgn;
    public int ennd;
    //public String[]a;
//public int d;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bgn = getIntent().getExtras().getInt("bgn");
        ennd = getIntent().getExtras().getInt("ennd");
        new ParseTask().execute();
    }

    public class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        JSONArray jsonArrayMessages = new JSONArray();
        String jsonResult = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с сервера
            try {
                URL url = new URL("***.json");//Вставить ссылку на координаты в

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder builder = new StringBuilder();


                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                jsonResult = builder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonResult;
        }
        @Override
        public void onPostExecute(String json) {
            super.onPostExecute(json);


            // выводим целиком полученную json-строку
            Log.d(TAG, json);
            // int x = 0;
            JSONArray dataJsonObject;

            try {


//            MapView mapView = (MapView) findViewById(R.id.mapView);
                int d;
                int d1;
                int d2;
                dataJsonObject = new JSONArray(json);
                d = dataJsonObject.length();
                JSONArray cats = dataJsonObject.getJSONArray(0);
//            double[]b;
//             double[]c;
//             String[]a;


//
                org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants
                        .setUserAgentValue(BuildConfig.APPLICATION_ID);
                MapView mapView = findViewById(R.id.mapView);
//        здесь задается поставщик картинки карты
                // можно выбрать подходящий вариант, подробнее см. в документации
                // mapView.setUseSafeCanvas(false);
                // GeoPoint startPoint = new GeoPoint(68.9800, 33.0504);
                mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                mapView.setBuiltInZoomControls(true);
                mapView.setMultiTouchControls(true);
                //  Marker startMarker = new Marker(mapView);
                String []a= new String[d];
                double[]b = new double[d];
                double[]c =new double[d];
//            String a;
//            double b;
//            double c;
                if (bgn>=d){d1=d;}else{d1=bgn;}
                if (ennd>=d){d2=d;}else{d2=ennd;}

                for (int i = d1; i <=d2; ) {
//
                    JSONArray ar = dataJsonObject.getJSONArray(i);

                    a[i] = ar.getString(0);
//                x=x++;
                    b[i] = ar.getDouble(1);
//                x=x++;

                    c[i] = ar.getDouble(2);
                    Marker startMarker = new Marker(mapView);
                    startMarker.setPosition(new GeoPoint(b[i],c[i]));
                    startMarker.setIcon(getResources().getDrawable(R.drawable.marker_default));
                    startMarker.setTitle(a[i]);
                    startMarker.setAnchor(Marker.ANCHOR_CENTER, 1.0f);
                    MarkerInfoWindow infoWindow = new  MarkerInfoWindow(R.layout.bonuspack_bubble,
                            mapView);
                    startMarker.setInfoWindow(infoWindow);


                    mapView.getOverlays().add(startMarker);
                    mapView.invalidate();
                    i=i+1;


                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

}
