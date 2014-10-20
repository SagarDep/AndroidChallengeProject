package com.example.sweet;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author yangyu
 *	����������ʵ�ֶ���Ч�������activity
 */
public class GuideDoorActivity extends Activity {
	
	//������������ͼƬ����
	private ImageView mLeft,mRight;

	//����һ���ı�����
	private TextView mText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.guide_door);
        
        //ʵ��������
        mLeft = (ImageView)findViewById(R.id.imageLeft);
        mRight = (ImageView)findViewById(R.id.imageRight);
        mText = (TextView)findViewById(R.id.anim_text);
        
        //ʵ������������
        AnimationSet anim = new AnimationSet(true);
        //ʵ����λ�ƶ�������
		TranslateAnimation mytranslateanim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
	    //���ö�������ʱ��
		mytranslateanim.setDuration(2000);
		//��������ʱ��
		anim.setStartOffset(800);
		//��λ�ƶ�����ӽ�����Ч����
		anim.addAnimation(mytranslateanim);
		//���������󣬱�������ֹλ
		anim.setFillAfter(true);
		//���ͼ�����ö���Ч��
		mLeft.startAnimation(anim);
		
		AnimationSet anim1 = new AnimationSet(true);
		TranslateAnimation mytranslateanim1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+1f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
		mytranslateanim1.setDuration(1500);
		anim1.addAnimation(mytranslateanim1);
		anim1.setStartOffset(800);
		anim1.setFillAfter(true);
		mRight.startAnimation(anim1);
		
		AnimationSet anim2 = new AnimationSet(true);
		ScaleAnimation myscaleanim = new ScaleAnimation(1f,3f,1f,3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		myscaleanim.setDuration(1000);
		AlphaAnimation myalphaanim = new AlphaAnimation(1,0.0001f);
		myalphaanim.setDuration(1500);
		anim2.addAnimation(myscaleanim);
		anim2.addAnimation(myalphaanim);
		anim2.setFillAfter(true);
		mText.startAnimation(anim2);
		
		new Handler().postDelayed(new Runnable(){
			@Override
			public void run(){
				Intent intent = new Intent (GuideDoorActivity.this,MainFloorActivity.class);			
				startActivity(intent);		
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				GuideDoorActivity.this.finish();
			}
		}, 2300);
    }

    
}
