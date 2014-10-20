package com.example.sweet;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FirstFloorActivity extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{

	private TextView []seats=new TextView[108];
	
	private JSONArray jArray;
	private String result=null;
	private InputStream is=null;
	private StringBuilder sb=null;
	
	private Button btn;
	private TextView ttime;
	private Handler PHP_hd;
	
	private SwipeRefreshLayout swipeL;
	
	private ImageButton imb;
	
	private SharedPreferences sharedP;
	private SharedPreferences.Editor sharedE;
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_first_floor);
		
		swipeL=(SwipeRefreshLayout)findViewById(R.id.LinearLayout1);
		swipeL.setOnRefreshListener(this);
		swipeL.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
		

		seats[0]=(TextView)findViewById(R.id.TextView01);
		seats[1]=(TextView)findViewById(R.id.TextView02);
		seats[2]=(TextView)findViewById(R.id.TextView03);
		seats[3]=(TextView)findViewById(R.id.TextView04);
		seats[4]=(TextView)findViewById(R.id.TextView05);
		seats[5]=(TextView)findViewById(R.id.TextView06);
		seats[6]=(TextView)findViewById(R.id.TextView07);
		seats[7]=(TextView)findViewById(R.id.TextView08);
		seats[8]=(TextView)findViewById(R.id.TextView09);
		seats[9]=(TextView)findViewById(R.id.TextView10);
		seats[10]=(TextView)findViewById(R.id.TextView11);
		seats[11]=(TextView)findViewById(R.id.TextView12);
		seats[12]=(TextView)findViewById(R.id.TextView13);
		seats[13]=(TextView)findViewById(R.id.TextView14);
		seats[14]=(TextView)findViewById(R.id.TextView15);
		seats[15]=(TextView)findViewById(R.id.TextView16);
		seats[16]=(TextView)findViewById(R.id.TextView17);
		seats[17]=(TextView)findViewById(R.id.TextView18);
		seats[18]=(TextView)findViewById(R.id.TextView19);
		seats[19]=(TextView)findViewById(R.id.TextView20);
		seats[20]=(TextView)findViewById(R.id.TextView21);
		seats[21]=(TextView)findViewById(R.id.TextView22);
		seats[22]=(TextView)findViewById(R.id.TextView23);
		seats[23]=(TextView)findViewById(R.id.TextView24);
		seats[24]=(TextView)findViewById(R.id.TextView25);
		seats[25]=(TextView)findViewById(R.id.TextView26);
		seats[26]=(TextView)findViewById(R.id.TextView27);
		seats[27]=(TextView)findViewById(R.id.TextView28);
		seats[28]=(TextView)findViewById(R.id.TextView29);
		seats[29]=(TextView)findViewById(R.id.TextView30);
		seats[30]=(TextView)findViewById(R.id.TextView31);
		seats[31]=(TextView)findViewById(R.id.TextView32);
		seats[32]=(TextView)findViewById(R.id.TextView33);
		seats[33]=(TextView)findViewById(R.id.TextView34);
		seats[34]=(TextView)findViewById(R.id.TextView35);
		seats[35]=(TextView)findViewById(R.id.TextView36);
		seats[36]=(TextView)findViewById(R.id.TextView37);
		seats[37]=(TextView)findViewById(R.id.TextView38);
		seats[38]=(TextView)findViewById(R.id.TextView39);
		seats[39]=(TextView)findViewById(R.id.TextView40);
		seats[40]=(TextView)findViewById(R.id.TextView41);
		seats[41]=(TextView)findViewById(R.id.TextView42);
		seats[42]=(TextView)findViewById(R.id.TextView43);
		seats[43]=(TextView)findViewById(R.id.TextView44);
		seats[44]=(TextView)findViewById(R.id.TextView45);
		seats[45]=(TextView)findViewById(R.id.TextView46);
		seats[46]=(TextView)findViewById(R.id.TextView47);
		seats[47]=(TextView)findViewById(R.id.TextView48);
		seats[48]=(TextView)findViewById(R.id.TextView49);
		seats[49]=(TextView)findViewById(R.id.TextView50);
		seats[50]=(TextView)findViewById(R.id.TextView51);
		seats[51]=(TextView)findViewById(R.id.TextView52);
		seats[52]=(TextView)findViewById(R.id.TextView53);
		seats[53]=(TextView)findViewById(R.id.TextView54);
		seats[54]=(TextView)findViewById(R.id.TextView55);
		seats[55]=(TextView)findViewById(R.id.TextView56);
		seats[56]=(TextView)findViewById(R.id.TextView57);
		seats[57]=(TextView)findViewById(R.id.TextView58);
		seats[58]=(TextView)findViewById(R.id.TextView59);
		seats[59]=(TextView)findViewById(R.id.TextView60);
		seats[60]=(TextView)findViewById(R.id.TextView61);
		seats[61]=(TextView)findViewById(R.id.TextView62);
		seats[62]=(TextView)findViewById(R.id.TextView63);
		seats[63]=(TextView)findViewById(R.id.TextView64);
		seats[64]=(TextView)findViewById(R.id.TextView65);
		seats[65]=(TextView)findViewById(R.id.TextView66);
		seats[66]=(TextView)findViewById(R.id.TextView67);
		seats[67]=(TextView)findViewById(R.id.TextView68);
		seats[68]=(TextView)findViewById(R.id.TextView69);
		seats[69]=(TextView)findViewById(R.id.TextView70);
		seats[70]=(TextView)findViewById(R.id.TextView71);
		seats[71]=(TextView)findViewById(R.id.TextView72);
		seats[72]=(TextView)findViewById(R.id.TextView73);
		seats[73]=(TextView)findViewById(R.id.TextView74);
		seats[74]=(TextView)findViewById(R.id.TextView75);
		seats[75]=(TextView)findViewById(R.id.TextView76);
		seats[76]=(TextView)findViewById(R.id.TextView77);
		seats[77]=(TextView)findViewById(R.id.TextView78);
		seats[78]=(TextView)findViewById(R.id.TextView79);
		seats[79]=(TextView)findViewById(R.id.TextView80);
		seats[80]=(TextView)findViewById(R.id.TextView81);
		seats[81]=(TextView)findViewById(R.id.TextView82);
		seats[82]=(TextView)findViewById(R.id.TextView83);
		seats[83]=(TextView)findViewById(R.id.TextView84);
		seats[84]=(TextView)findViewById(R.id.TextView85);
		seats[85]=(TextView)findViewById(R.id.TextView86);
		seats[86]=(TextView)findViewById(R.id.TextView87);
		seats[87]=(TextView)findViewById(R.id.TextView88);
		seats[88]=(TextView)findViewById(R.id.TextView89);
		seats[89]=(TextView)findViewById(R.id.TextView90);
		seats[90]=(TextView)findViewById(R.id.TextView91);
		seats[91]=(TextView)findViewById(R.id.TextView92);
		seats[92]=(TextView)findViewById(R.id.TextView93);
		seats[93]=(TextView)findViewById(R.id.TextView94);
		seats[94]=(TextView)findViewById(R.id.TextView95);
		seats[95]=(TextView)findViewById(R.id.TextView96);
		seats[96]=(TextView)findViewById(R.id.TextView97);
		seats[97]=(TextView)findViewById(R.id.TextView98);
		seats[98]=(TextView)findViewById(R.id.TextView99);
		seats[99]=(TextView)findViewById(R.id.TextView100);
		seats[100]=(TextView)findViewById(R.id.TextView101);
		seats[101]=(TextView)findViewById(R.id.TextView102);
		seats[102]=(TextView)findViewById(R.id.TextView103);
		seats[103]=(TextView)findViewById(R.id.TextView104);
		seats[104]=(TextView)findViewById(R.id.TextView105);
		seats[105]=(TextView)findViewById(R.id.TextView106);
		seats[106]=(TextView)findViewById(R.id.TextView107);
		seats[107]=(TextView)findViewById(R.id.TextView108);
		
		ttime=(TextView)findViewById(R.id.textView2);
		
		
		//进入界面时载入一次
		

		
		PHP_hd=new PHPHandler();
		
		
	    sharedP=this.getSharedPreferences("com.library",MODE_PRIVATE);
	    sharedE=sharedP.edit();
		
		//对刷新按钮进行设置
		
		imb=(ImageButton)findViewById(R.id.imageButton1);
		
		if (isServiceRun()) {
			imb.setImageResource(R.drawable.button_on);
		} else {
			imb.setImageResource(R.drawable.button_off);
		}
		
		
		
		
		
		imb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if (isServiceRun()) {
					
					

					//关闭
					
					sharedE.putBoolean("isRun", false);
					
					sharedE.commit();
					
					PollingUtils.stopPollingService(FirstFloorActivity.this,  MyService.class, "OrderService");
					
					imb.setImageResource(R.drawable.button_off);
					
					
				} else {
					
					//开启
					
					sharedE.putBoolean("isRun", true);
					
					sharedE.commit();
					
					PollingUtils.startPollingService(FirstFloorActivity.this, 600, MyService.class, "OrderService");
					
					imb.setImageResource(R.drawable.button_on);
					
				}
				
			}
		});
		
	}
	

	
	class getPHP extends Thread {
		
		public void run() {
			
			Message msg=PHP_hd.obtainMessage();
			
			
			//获取网页信息
			try {
				
//				URL url=new URL("http://104.131.125.34/app/seats.php");
//				
//				HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
//				
//				String encoding=httpConn.getContentEncoding();
//				
//				Log.d("encoding",encoding);
//				
//				is=httpConn.getInputStream();
				
				HttpClient httpclient=new DefaultHttpClient();
//				httpclient.getParams().setIntParameter("http.socket.timeout", 10000);
				HttpGet httpget=new HttpGet("http://sweetll.me/app/seats.php");
				HttpResponse response=httpclient.execute(httpget);
				HttpEntity entity=response.getEntity();
				
				is=entity.getContent();
			} catch (Exception e) {
				Log.e("log_tag","Error in http connection "+e.toString());
				Toast.makeText(getApplicationContext(), "刷新失败！", Toast.LENGTH_SHORT).show();
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
				BufferedReader reader=new BufferedReader(new InputStreamReader(is, "iso-8859-1"),24);
				
				
				
				
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
			
			int [] temp=new int[120];
			

			
			try {
				
				jArray=new JSONArray(result);
				JSONObject json_data=null;
				
				for (int i=0;i<jArray.length();i++) {
					json_data=jArray.getJSONObject(i);
					temp[i]=json_data.getInt("state");
				}
				
			} catch (Exception e) {
				Log.e("log_tag","Error in JSON "+e.toString());
				Toast.makeText(getApplicationContext(), "刷新失败！", Toast.LENGTH_SHORT).show();
				return;
			}
			
			
			for (int i=0;i<jArray.length()-3;i++) {
				if (temp[i]==1)
					seats[i].setBackgroundResource(R.drawable.hasp);
				else
					seats[i].setBackgroundResource(R.drawable.empty);
				
			}
			Toast.makeText(getApplicationContext(), "刷新成功！", Toast.LENGTH_SHORT).show();

			Time time=new Time();
			ttime.setText(""+temp[108]+"时"+temp[109]+"分"+temp[110]+"秒");
			
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Thread t=new getPHP();
				t.start();
				swipeL.setRefreshing(false);
			}
		}, 5000);
		
	}
	
	public void back(View v)
	{
		this.finish();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	@Override
	public void onBackPressed()
	{
		back(null);
	}
	
	/**
	 * 判断服务是否后台运行
	 * 
	 * @return true 在运行 false 不在运行
	 */
	public boolean isServiceRun() {
		boolean isRun = false;
        
        isRun=sharedP.getBoolean("isRun", false);
        
		
		return isRun;
	}

}
