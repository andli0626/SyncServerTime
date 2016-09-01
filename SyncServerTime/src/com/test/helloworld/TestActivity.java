package com.test.helloworld;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestActivity extends Activity {

	Date serverTime;
	long C1;
	long C2;
	
	TextView serverTimeText;
	TextView C1Text;
	TextView C2Text;
	TextView showText;
	
	DateFormat df;
	
    @SuppressLint("SimpleDateFormat")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_layout);
        
        serverTimeText 	= (TextView)findViewById(R.id.servertime);
        C1Text 			= (TextView)findViewById(R.id.c1text);
        C2Text 			= (TextView)findViewById(R.id.c2text);
        showText 		= (TextView)findViewById(R.id.showText);
        
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 服务器时间
        serverTime = new Date();
        // 开机之后会保持一个时钟(绝对时间）
        C1 = SystemClock.elapsedRealtime();
        
        Log.i("andli",df.format(serverTime));
        Log.i("andli",C1+"");
        
        // 测试：固定值 小牛手机
        C1			= 1956047907;
        String temp = "2016-09-01 08:17:22";
        
        // 测试：固定值 锤子手机
        C1			= 163716116;
        temp 		= "2016-09-01 08:43:14";
        
        try {
			serverTime	= df.parse(temp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        
        serverTimeText.setText("之前时间："+df.format(serverTime));
        C1Text.setText("之前时钟C1："+C1+"");
        
        Button button = (Button)findViewById(R.id.testButton);
        button.setText("计算当前时间");
        button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				C2 = SystemClock.elapsedRealtime();
				C2Text.setText("当前时钟C2："+C2+"");
				
				// 当前时间=服务器时间+时间差
				Date curDate = new Date(serverTime.getTime()+(C2-C1));
				String s = df.format(curDate);
				showText.setText("最新当前时间:"+s);
			}
		});
        
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Log.i("andli","退出");
			finish();
			break;
		}
		return false;
	}
    
}
