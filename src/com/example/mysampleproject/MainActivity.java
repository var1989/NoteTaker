package com.example.mysampleproject;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Savepoint;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	public static final String EXTRA_MESSAGE = "com.example.mysampleproject.MESSAGE";
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void addNote(View view)
    {
    	Log.i("myApp", "calling Send Message");
    	Boolean status = false;
    	//Intent intent =  new Intent(this,DisplayMessageActivity.class);
    	EditText editText_header  = (EditText) findViewById(R.id.edit_header);
    	EditText editText_body = (EditText) findViewById(R.id.edit_body);
    	String message_header = editText_header.getText().toString();
    	String message_body = editText_body.getText().toString();
    	//intent.putExtra(EXTRA_MESSAGE,message);
    	Log.i("myApp", "before calling SaveFile()");
    	status =  SaveFile(message_header,message_body );
    	if(status== true)
    	{
    		Log.i(EXTRA_MESSAGE, "File was created successfully");
    		Toast.makeText(getApplicationContext(), "File was written successfully", Toast.LENGTH_SHORT).show();
    	}
    	else
    	{
    		Log.i(EXTRA_MESSAGE, "There was some error");
    		Toast.makeText(getApplicationContext(), "There was some error. Check logs", Toast.LENGTH_SHORT).show();
    	}
    	//startActivity(intent);
    	
    }
    
    public Boolean SaveFile(String subject, String body)
    {
    	Boolean status = false;
    	Calendar timeStamp = Calendar.getInstance();
    	String file_name = subject +""+ timeStamp.getTime().toString()+".txt";
    	
    	Log.i(EXTRA_MESSAGE,"Inside SaveFile() before opening the outputstream with filename -->"+file_name);
    	StringBuilder sb = new StringBuilder();
    	
    	sb.append("HEADER :"+subject);
    	sb.append("\nTIME :"+timeStamp);
    	sb.append("\nBODY :"+body);	
    	try
    	{
	    	OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(file_name,Context.MODE_PRIVATE));
	    	outputStreamWriter.write(sb.toString());
	    	outputStreamWriter.close();
	    	status = true;
    	}
    	catch (IOException ioe) {
    		Log.e(EXTRA_MESSAGE,"File Write Failed" +ioe.toString());
		}
    	
    	return status;
    }
}
