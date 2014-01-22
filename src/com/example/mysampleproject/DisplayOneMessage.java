package com.example.mysampleproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DisplayOneMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_display_message);
		
		Intent intent = getIntent();
	    String subject = intent.getStringExtra(DisplayMessageActivity.name);
	    String body = intent.getStringExtra(DisplayMessageActivity.body);
	    
	    TextView t = (TextView) findViewById(R.id.textView1);
	    t.setText(subject);
	    
	    TextView t2 = (TextView) findViewById(R.id.textView2);
	    t2.setText(body);
	    
	    
	    
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_one_message, menu);
		return true;
	}

}
