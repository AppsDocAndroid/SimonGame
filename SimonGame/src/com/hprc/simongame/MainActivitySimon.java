package com.hprc.simongame;

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.os.CountDownTimer;
import android.widget.TextView;


public class MainActivitySimon extends Activity {
	public static int s = 3;
	public static int l = 1;
	public static boolean tutorialmode = false;
	int tutorialcount = 0;
	Random generator = new Random();
	int count = 0;
	int currentlevel= l-1;
	int inputcount = 0;
	int highscore = 0;
	boolean firstdelay = true;
	int [] correctInput = new int[500];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_simon);
		
		if (tutorialmode){
			l = 1;
			currentlevel = 0;
			correctInput[0] = 1;
			correctInput[1] = 3;
			correctInput[2] = 1;
			correctInput[3] = 4;
			Toast.makeText(getApplicationContext(), "Copy the button simon pressed.",
	    	    	Toast.LENGTH_LONG).show();
		}
		Button simonbutton =(Button) findViewById(R.id.Simon);
		simonbutton.performClick();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_activity_simon, menu);
		return true;
	}
	

	public void lightupred(){
		((Button) findViewById(R.id.Red)).setPressed(true);
		Handler h = new Handler();
		h.postDelayed(new Runnable(){
			public void run(){
				((Button) findViewById(R.id.Red)).setPressed(false);
			}
		}, 1400/s);
	}
	
	public void lightupyellow(){
		((Button) findViewById(R.id.Yellow)).setPressed(true);
		Handler h = new Handler();
		h.postDelayed(new Runnable(){
			public void run(){
				((Button) findViewById(R.id.Yellow)).setPressed(false);
			}
		}, 1400/s);
	}
	
	public void lightupgreen(){
		((Button) findViewById(R.id.Green)).setPressed(true);
		Handler h = new Handler();
		h.postDelayed(new Runnable(){
			public void run(){
				((Button) findViewById(R.id.Green)).setPressed(false);
			}
		}, 1400/s);
	}
	
	public void lightupblue(){
		((Button) findViewById(R.id.Blue)).setPressed(true);
		Handler h = new Handler();
		h.postDelayed(new Runnable(){
			public void run(){
				((Button) findViewById(R.id.Blue)).setPressed(false);
			}
		}, 1400/s);
	}
	
	
	public void allthelights(){
		new CountDownTimer( (((1400/s)+1000/s)*(currentlevel+2)), ((1400/s)+1000/s) ){
			public void onTick(long millis){
				if (firstdelay){
					firstdelay = false;
				}
				else if(correctInput[count] == 1){
					lightupred();
					count++;
				}
				else if(correctInput[count] == 2) {
					lightupgreen();
					count++;
				}
				else if(correctInput[count] == 3) {
					lightupblue();
					count++;
				}
				else if(correctInput[count] == 4) {
					lightupyellow();
					count++;
				}
			}
			
			public void onFinish(){
				count = 0;
				inputcount = 0;
				firstdelay = true;
				((Button) findViewById(R.id.Red)).setEnabled(true);
            	((Button) findViewById(R.id.Blue)).setEnabled(true);
            	((Button) findViewById(R.id.Green)).setEnabled(true);
            	((Button) findViewById(R.id.Yellow)).setEnabled(true);
            	TextView info = (TextView) findViewById(R.id.info);
            	info.setText("Your Turn");
			}
		}.start();
	}


		

	public void levelup(){
		if (!tutorialmode){
			correctInput[currentlevel] = generator.nextInt(4) +1;
		}
		currentlevel++;
		count = 0;
		TextView info = (TextView) findViewById(R.id.info);
		info.setText("Simon's Turn");
		((Button) findViewById(R.id.Red)).setEnabled(false);
    	((Button) findViewById(R.id.Blue)).setEnabled(false);
    	((Button) findViewById(R.id.Green)).setEnabled(false);
    	((Button) findViewById(R.id.Yellow)).setEnabled(false);
		allthelights();
		highscore++;
		TextView scorebox = (TextView) findViewById(R.id.Scorebox);
		scorebox.setText("Current Score: " + (highscore-1));
	}

	public void gameover(){
		if (!tutorialmode){
		Toast.makeText(getApplicationContext(), "your score was: " + (highscore-1),
    	    	Toast.LENGTH_LONG).show();
		}
		tutorialmode = false;
		inputcount = 0;
		for (int i =0; i<currentlevel;i++){
			correctInput[i] = 0;
		}
		currentlevel = l-1;
		highscore = 0;

		((Button) findViewById(R.id.Red)).setEnabled(false);
    	((Button) findViewById(R.id.Blue)).setEnabled(false);
    	((Button) findViewById(R.id.Green)).setEnabled(false);
    	((Button) findViewById(R.id.Yellow)).setEnabled(false);
    	findViewById(R.id.Simon).setEnabled(true);
    	TextView info = (TextView) findViewById(R.id.info);
    	info.setText("Hit Simon to Begin");
    	TextView scorebox = (TextView) findViewById(R.id.Scorebox);
		scorebox.setText("Current Score: ");
	}
	
	public void Simonsays(View view){
		findViewById(R.id.Simon).setEnabled(false);
		
		for (int i=0; i< l-1; i++){
			correctInput[i] = generator.nextInt(4) +1;
		}
		
		levelup();
		
		
		 Button red = ((Button) findViewById(R.id.Red));
         Button blue = ((Button) findViewById(R.id.Blue));
         Button green = ((Button) findViewById(R.id.Green));
         Button yellow = ((Button) findViewById(R.id.Yellow));
         

            red.setOnClickListener(new OnClickListener(){
            	public void onClick(View v){
            		if (1 == correctInput[inputcount]){
            	    	inputcount++;
            	    	if (inputcount == currentlevel){
            	    		if (tutorialmode && tutorialcount == 0){
            	    			Toast.makeText(getApplicationContext(), "Good Job! Simon will now add another button to the sequnce.",
                    	    	    	Toast.LENGTH_LONG).show();
            	    			tutorialcount++;
            	    		} 
            	    		if (tutorialmode && tutorialcount == 2){
            	    		Toast.makeText(getApplicationContext(), "You're getting the hang of it!",
                	    	    	Toast.LENGTH_LONG).show();
            	    		}
            	    		levelup();
            	    	}
            		}
            		else {
            			if (tutorialmode){
            				Toast.makeText(getApplicationContext(), "That is not what Simon pressed. Try Again.",
                	    	    	Toast.LENGTH_LONG).show();
            			} else {
            			Toast.makeText(getApplicationContext(), "you lose",
            	    	    	Toast.LENGTH_LONG).show();
            			gameover();
            			}
            		}
            	}
            });
            
            blue.setOnClickListener(new OnClickListener(){
            	public void onClick(View v){
            		if (3 == correctInput[inputcount]){
            	    	inputcount++;
            	    	if (inputcount == currentlevel){
            	    		if (tutorialmode){
            	    			Toast.makeText(getApplicationContext(), "Nice Job!",
                    	    	    	Toast.LENGTH_LONG).show();
            	    			tutorialcount++;
            	    		}
            	    		levelup();
            	    	}
            		} 
            		else {
            			if (tutorialmode){
            				Toast.makeText(getApplicationContext(), "That is not what Simon pressed. Try Again.",
                	    	    	Toast.LENGTH_LONG).show();
            			} else {
            			Toast.makeText(getApplicationContext(), "you lose",
            	    	    	Toast.LENGTH_LONG).show();
            			gameover();
            			}
            		}
            	}
            });
            
            yellow.setOnClickListener(new OnClickListener(){
            	public void onClick(View v){
            		if (4 == correctInput[inputcount]){
            	    	inputcount++;
            	    	if (inputcount == currentlevel){
            	    		if (tutorialmode){
            	    			Toast.makeText(getApplicationContext(), "Nice! You've completed the tutorial! Hit simon to play.",
                    	    	    	Toast.LENGTH_LONG).show();
            	    			gameover();
            	    		} else {
            	    		levelup();
            	    		}
            	    	}
            		} 
            		else {
            			if (tutorialmode){
            				Toast.makeText(getApplicationContext(), "That is not what Simon pressed. Try Again.",
                	    	    	Toast.LENGTH_LONG).show();
            			} else {
            			Toast.makeText(getApplicationContext(), "you lose",
            	    	    	Toast.LENGTH_LONG).show();
            			gameover();
            			}
            		}
            	}
            });
            
            green.setOnClickListener(new OnClickListener(){
            	public void onClick(View v){
            		if (tutorialmode){
            			Toast.makeText(getApplicationContext(), "Recall what Simon did. Try Again.",
            	    	    	Toast.LENGTH_LONG).show();
            		} else {
            			
            		if (2 == correctInput[inputcount]){
            	    	inputcount++;
            	    	if (inputcount == currentlevel){
            	    		levelup();
            	    	}
            		} 
            		else {
            			Toast.makeText(getApplicationContext(), "you lose",
            	    	    	Toast.LENGTH_LONG).show();
            			gameover();
            		}
            	}
            	}
            });
            
        	
        
	}
        	

}


        	
   



