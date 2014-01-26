package com.example.trylingo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Lib extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lib);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lib, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
		case R.id.action_settings:
			openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	private void openSettings() {
		Intent intent = new Intent(Lib.this, Lang.class);
		startActivity(intent);
	}
	public void clickCard(View view) {
		Intent intent = new Intent(Lib.this, MainActivity.class);
	    startActivity(intent);		
	}
	public void clickCard1(View view) {
		Intent intent = new Intent(Lib.this, Store.class);
	    startActivity(intent);		
	}
	public void clickCard2(View view) {
		Intent intent = new Intent(Lib.this, Ssign.class);
	    startActivity(intent);		
	}
	public void clickCard3(View view) {
		Intent intent = new Intent(Lib.this, Tree.class);
	    startActivity(intent);		
	}
	public void clickCard4(View view) {
		Intent intent = new Intent(Lib.this, Pond.class);
	    startActivity(intent);		
	}
	public void clickCard5(View view) {
		Intent intent = new Intent(Lib.this, Hack.class);
	    startActivity(intent);		
	}
}
