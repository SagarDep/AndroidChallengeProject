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

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class MyService extends Service{
	
	public static final String TAG = "MyService";  
	
	private JSONArray jArray;
	private String result=null;
	private InputStream is=null;
	private StringBuilder sb=null;
	private Handler PHP_hd;
	
	private PendingIntent pd;
	
	private boolean started;
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreate() executed");  
	}
	
	@Override
	public int onStartCommand(Intent intent,int flags,int startId) {
		
		Log.d(TAG, "onStartCommand() executed");  
		
		PHP_hd=new PHPHandler();
		
		
//		started=true;
		
		Thread t=new getPHP();
		t.start();
			
			

		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override 
	public void onDestroy() {
		super.onDestroy();
		
//		started=false;
		
		Log.d(TAG, "onDestroy() executed");  
		
	}
	
	

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
    private void showDefaultNotification(String msg) {
    	
    	
    	
    	NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    	
    	Notification baseNF=new Notification();
    	
    	baseNF.icon=R.drawable.ic_ic;
    	
    	baseNF.tickerText="空座位Get!";
    	
    	baseNF.defaults=Notification.DEFAULT_VIBRATE;
    	
    	baseNF.flags=Notification.FLAG_AUTO_CANCEL;
    	
    	Intent in1=new Intent(this,FirstFloorActivity.class);
    	
    	pd=PendingIntent.getActivity(getApplicationContext(), 0, in1, 0);
    	
    	baseNF.setLatestEventInfo(getApplicationContext(), "找到空座位拉~", msg, pd);
    	
    	
    	nm.notify(2, baseNF);
    	
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
				
				if (num>0) {
				showDefaultNotification("在1楼发现空座位，点击查看");
			}
				
			} catch (Exception e) {
				Log.e("log_tag","Error in JSON "+e.toString());
				return;
			}
			
		}
	}
	
	class getPHP extends Thread {
		
		public void run() {
			
			Message msg=PHP_hd.obtainMessage();
			
					
					try {
						
						HttpClient httpclient=new DefaultHttpClient();
						httpclient.getParams().setIntParameter("http.socket.timeout", 5000);
						HttpGet httpget=new HttpGet("http://104.131.125.34/app/empty.php");
						HttpResponse response=httpclient.execute(httpget);
						HttpEntity entity=response.getEntity();
						
						is=entity.getContent();
					} catch (Exception e) {
						Log.e("log_tag","Error in http connection "+e.toString());
						
					}
					
					PHP_hd.sendMessage(msg);
					
		}
	}
	
	
}