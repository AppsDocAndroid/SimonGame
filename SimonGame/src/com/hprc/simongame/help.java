package com.hprc.simongame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		
		Button ok2 = (Button) findViewById(R.id.ok2);
		ok2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		
		Button tut = (Button) findViewById(R.id.tutorial);
		tut.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MainActivitySimon.tutorialmode = true;
				Intent intent1 = new Intent(help.this, MainActivitySimon.class);
				startActivity(intent1);
			}
			
		});
	}
}