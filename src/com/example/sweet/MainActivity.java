package com.example.sweet;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	private SharedPreferences sharedP;
	private SharedPreferences.Editor sharedE;
	
	private boolean isFirst;
	
	private ShimmerTextView tv;
	private Shimmer shimmer;
	
	private PendingIntent pd;

	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main);
        
        tv=(ShimmerTextView)findViewById(R.id.my_text_view);
        
        shimmer=new Shimmer();
        shimmer.start(tv);
        
        sharedP=this.getSharedPreferences("com.library",MODE_PRIVATE);
        sharedE=sharedP.edit();
      
      
      new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				Intent in1=new Intent();
				
				isFirst=sharedP.getBoolean("isFirst", true);
					
				if (isFirst) {
						
					sharedE.putBoolean("isFirst", false);
					
					sharedE.commit();
						
					in1.setClass(MainActivity.this,GuideActivity.class);
					
						
				} else {
						
					in1.setClass(MainActivity.this, MainFloorActivity.class);
						
						
				}
				
				
				startActivity(in1);
					
				MainActivity.this.finish();
				
			}
		}, 3000);
        
    }
    

}
