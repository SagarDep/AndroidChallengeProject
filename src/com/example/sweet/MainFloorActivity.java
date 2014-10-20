package com.example.sweet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainFloorActivity extends ActionBarActivity {

	private FButton fb1;
	private FButton fb2;
	private FButton fb3;
	private FButton fb4;
	private FButton fb5;
	private FButton fb6;
	private FButton fb7;
	private FButton fb8;
	private FButton fb9;
	private FButton fb10;
	private FButton fb11;
	
	private JSONArray jArray;
	private String result=null;
	private InputStream is=null;
	private StringBuilder sb=null;
	private Handler PHP_hd;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_main_floor);
		
        fb1=(FButton)findViewById(R.id.button1);
        
        
        
        PHP_hd=new PHPHandler();
        
        Thread t=new getPHP();
        t.start();
        
        
		fb1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent in1=new Intent();
				
				in1.setClass(MainFloorActivity.this, FirstFloorActivity.class);
				
				startActivity(in1);
				

				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				
			}
		});
		
		
        
        
		
	}
	
	class getPHP extends Thread {
		
		public void run() {
			
			Message msg=PHP_hd.obtainMessage();
			
			
			//获取网页信息
			try {
				
				HttpClient httpclient=new DefaultHttpClient();
				httpclient.getParams().setIntParameter("http.socket.timeout", 5000);
				HttpGet httpget=new HttpGet("http://sweetll.me/app/empty.php");
				HttpResponse response=httpclient.execute(httpget);
				HttpEntity entity=response.getEntity();
				
				is=entity.getContent();
			} catch (Exception e) {
				Log.e("log_tag","Error in http connection "+e.toString());
				return;
			}
			
			PHP_hd.sendMessage(msg);
			
		}
		
	}
	
	class PHPHandler extends Handler {
		
		@Override
		public void handleMessage(Message msg) {

			

			
			//转换为字符串信息
			try {
				BufferedReader reader=new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
				
				sb=new StringBuilder();
				sb.append(reader.readLine()+"\n");
				
				String line="";
				
				while ((line=reader.readLine()) != null) {
					sb.append(line+"\n");
				}
				
				is.close();
				result=sb.toString();
				
			} catch (Exception e) {
				Log.e("log_tag","Error converting result "+e.toString());
			}
			
			
			int num=-1;
			
			
			try {
				
				jArray=new JSONArray(result);
				
				num=jArray.length();
				
				fb1.append("\n("+num+")");
				
			} catch (Exception e) {
				Log.e("log_tag","Error in JSON "+e.toString());
				return;
			}
			
			

			
		}
	}


}
