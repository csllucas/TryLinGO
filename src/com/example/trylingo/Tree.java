package com.example.trylingo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

public class Tree extends Activity {
    float x1,x2;
    float y1, y2;
    
	// location
	//locations[0][0] = {latitude, longitude};
    double[][] locations = {{40.009768,-75.307251},//founder's
            {40.010586,-75.30285}, //Pond
            {40.012065,-75.299387},//intersection
            //{40.011459,-75.29818}, //street
            {40.011373,-75.298225} //coffee shop
    };
    String[] names = {"Founder's Hall", "a pond", "an intersection", "coffee shop"};
    // GPSTracker class
    GPSTracker gps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tree);
        gps = new GPSTracker(Tree.this);
	}
	
	private int getMinDist(double lat, double lon){
        double minDist = 100.0;
        int minIdx = 0;
        int upperbound = names.length < locations.length ? names.length : locations.length;
        for (int i = 0; i < upperbound; i++){
            double distance = (lat - locations[i][0]) * (lat - locations[i][0]) + (lon - locations[i][1]) * (lon - locations[i][1]);
            if (distance < minDist){
                minIdx = i;
                minDist = distance;
            }
        }
        return minIdx;
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tree, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		switch (item.getItemId()) {
        case R.id.action_save:
            save();
            return true;
        case R.id.action_settings:
            openSettings();
            return true;
        default:
            return super.onOptionsItemSelected(item);
    }
	}

	private void openSettings() {
		Intent intent = new Intent(Tree.this, Lang.class);
	    startActivity(intent);
		
	}

	private void save() {
		Intent intent = new Intent(Tree.this, Lib.class);
	    startActivity(intent);	
	}
	private void toPond() {
		Intent intent = new Intent(Tree.this, Pond.class);
		startActivity(intent);
	}	
	private void toCar() {
		Intent intent = new Intent(Tree.this, Car.class);
		startActivity(intent);
	}
	public boolean onTouchEvent(MotionEvent touchevent) 
	{
		switch (touchevent.getAction())
		{
		// when user first touches the screen we get x and y coordinate
		case MotionEvent.ACTION_DOWN: 
		{
			x1 = touchevent.getX();
			y1 = touchevent.getY();
			break;
		}
		case MotionEvent.ACTION_UP: 
		{
			x2 = touchevent.getX();
			y2 = touchevent.getY(); 

			//if left to right sweep event on screen
			if (x1 +20< x2) 
			{
				toPond();
			}

			// if right to left sweep event on screen
			if (x1 -20> x2)
			{
				toCar();
			}
			if (Math.abs(x1-x2)<=20){
				 if(gps.canGetLocation()){
                    
	                    double latitude = gps.getLatitude();
	                    double longitude = gps.getLongitude();
	                     
	                    int idx = getMinDist(latitude, longitude);
	                    Toast.makeText(getApplicationContext(), "Your Location is : \nLat: " + latitude + "\nLong: " + longitude +"\nYou are near " + names[idx], Toast.LENGTH_LONG ).show();   
	                }else{
	                    // can't get location
	                    // GPS or Network is not enabled
	                    // Ask user to enable GPS/network in settings
	                    gps.showSettingsAlert();
	                }
			}

			//			// if UP to Down sweep event on screen
			//			if (y1 < y2) 
			//			{
			//				Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
			//			}
			//
			//			//if Down to UP sweep event on screen
			//			if (y1 > y2)
			//			{
			//				Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
			//			}
			//			break;
		}
		}
		return false;
	}
}
