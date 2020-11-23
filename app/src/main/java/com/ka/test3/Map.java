package com.ka.test3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

import java.io.File;


public class Map extends AppCompatActivity {

  private MapView mapView;
  private MapController mapController;

  int b;
  int c;
  int a;
  int d;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.question);




  }
  public void onClick (View view) {
    EditText infoEditView = (EditText) findViewById(R.id.editText);
    // infoEditView.getText();
    EditText infoEditView1 = (EditText) findViewById(R.id.editText1);
    // infoEditView1.getText();
    a = Integer.parseInt(infoEditView.getText().toString());
    b = Integer.parseInt(infoEditView1.getText().toString());
    if (infoEditView.getText().length() == 0) {
      Toast.makeText(getApplicationContext(), R.string.Answer,
              Toast.LENGTH_LONG).show();
      return;}
    if (infoEditView.getText().length() == 0) {
      Toast.makeText(getApplicationContext(), R.string.Answer1,
              Toast.LENGTH_LONG).show();
      return;}
    c=b-a;
    if (c < 5001) {
      Intent intent = new Intent(Map.this, MainActivity.class);
      intent.putExtra("bgn", a);
      intent.putExtra("ennd", b);
      startActivity(intent);
    } else {
      Toast.makeText(getApplicationContext(), R.string.Answer3,
              Toast.LENGTH_LONG).show();
      return;
    }
  }
  public void Task(){
    EditText infoEditView = (EditText) findViewById(R.id.editText);
    // infoEditView.getText();
    EditText infoEditView1 = (EditText) findViewById(R.id.editText1);
    // infoEditView1.getText();

  }
}

