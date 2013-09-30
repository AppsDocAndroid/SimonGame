package com.hprc.simongame;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SimonMenu extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);
		
		Button start;
		start = (Button) findViewById(R.id.btn_start);
		start.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				MainActivitySimon.tutorialmode = false;
				Intent intent1 = new Intent(SimonMenu.this, MainActivitySimon.class);
				startActivity(intent1);
			}
			
		});
		
		Button exit = (Button) findViewById(R.id.btn_exit);
		exit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(Intent.ACTION_MAIN);
				intent2.addCategory(Intent.CATEGORY_HOME);
				intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent2);
			}
			
		});
		
		Button option = (Button) findViewById(R.id.btn_options);
		option.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent3 = new Intent(SimonMenu.this, options.class);
				startActivity(intent3);
			}
			
		});
		
		Button help = (Button) findViewById(R.id.btn_help);
		help.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent4 = new Intent(SimonMenu.this, help.class);
				startActivity(intent4);
			}
			
		});
		
	}
	

}