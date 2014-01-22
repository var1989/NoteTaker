package com.example.mysampleproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DisplayMessageActivity extends Activity implements OnItemClickListener {

	private BufferedReader br;
	ListView listView ;
	public static final String name = "com.example.mysampleproject.NAME";
	public static final String body = "com.example.mysampleproject.BODY";

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showall);

		
		File dir = getFilesDir(); 
        File[] subFiles = dir.listFiles();
        Log.d("MM_Log", "Size: "+ subFiles.length);
        
        String[] values = new String[subFiles.length];
        
        if (subFiles != null)
        {
        	int i = 0;
            for (File file : subFiles)
            {
                // Here is each file
            	values[i] = readItem(file, "HEADER");
            	i++;
            }
            
        }
		
		// Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView1);

        
        // Defined Array values to show in ListView
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
        listView.setAdapter(adapter); 
        
        listView.setOnItemClickListener(this);
      

	}
        public void onItemClick(AdapterView<?> parent, View view,
           int position, long id) {
          
         // ListView Clicked item value
         String  itemValue    = (String) listView.getItemAtPosition(position);
            
         Intent intent =  new Intent(this, DisplayOneMessage.class);
     	
     	intent.putExtra(name, readItem(new File(getFilesDir()+"/"+itemValue+".txt"), "HEADER"));
     	intent.putExtra(body, readItem(new File(getFilesDir()+"/"+itemValue+".txt"), "BODY"));
     	
     	startActivity(intent);
     	
        }

	private String readItem(File file, String item) {
		
		String finalOut = "";
		
		try {
			br = new BufferedReader(new FileReader(file));
			String thisLine = "";
			
			while ((thisLine = br.readLine()) != null) {
				if( item.equals("HEADER"))
				{
					if(thisLine.startsWith("HEADER :"))
					{
						finalOut = thisLine.substring(thisLine.indexOf("HEADER :")+8);
						break;
					}
				}
				else if (item.equals("TIME"))
				{
					if(thisLine.startsWith("TIME :"))
					{
						finalOut = thisLine.substring(thisLine.indexOf("TIME :")+8);
						break;
					}
				}
				else
				{
					if(!thisLine.startsWith("HEADER :") && !(thisLine.startsWith("TIME :")))
					finalOut += "\n" + thisLine;
				}
			}
			
			br.close();
			
			
		} catch (FileNotFoundException e) {
			Log.i("MM_Log",e.getMessage());
		} catch (IOException e) {
			Log.i("MM_Log",e.getMessage());
		}
		if( item.equals("BODY"))
			finalOut = finalOut.substring(7);
		
		return finalOut;
	}
	
	

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
