package com.luyaozhou.recognizethisforglass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class DisplayInfo extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Map<String,String> result = (HashMap) intent.getSerializableExtra("result");
        Log.i("Display Info", ": "+ result.size());
        setContentView(R.layout.activity_display_info);
        TextView idNumber = (TextView)findViewById(R.id.idNumber);
        TextView fn = (TextView)findViewById(R.id.fn);
        TextView ln = (TextView)findViewById(R.id.ln);
        TextView dob = (TextView)findViewById(R.id.dob);

        if(result.size() == 0){
            idNumber.setText( "ID Number:   "+"00000000");
            fn.setText("First Name:   "+"Detect image failed");
            ln.setText("Last Name:   "+"Detect image failed");
            dob.setText("Date of Birth:   "+"Detect image failed");
        }
        else{
            ln.setText("Last Name:    " + result.get("Address Recipient Last Name(0)") );
            fn.setText("First Name:    " + result.get("Address Recipient First Name(0)") );
            idNumber.setText("ID Number:    " + result.get("ID(0)") );
            dob.setText("Date of Birth:    " + result.get("DoB(0)") );

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
