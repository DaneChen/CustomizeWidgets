package com.example.gitwaveview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.gitwaveview.animation.ShakeAnimation;

public class MainActivity extends Activity {

	private Button mBtStart;
	private ErrorView mErrorView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initListener();
		
	}

	private void initView() {
		mBtStart = (Button)findViewById(R.id.id_bt_start_animation); 
		mErrorView = (ErrorView)findViewById(R.id.id_error_view); 
	}
	
	private void initListener(){
		mBtStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		 mErrorView.setOnStopListener(new ErrorView.OnStopListener() {  
	            @Override  
	            public void onStop(View v) {  
	                ShakeAnimation sa = new ShakeAnimation();  
	                sa.setDuration(1000);  
	                v.startAnimation(sa);  
	            }  
	        }); 
		 
		 mErrorView.setOnClickListener(new View.OnClickListener() {  
	            @Override  
	            public void onClick(View v) {  
	                mErrorView.reset();  
	            }  
	        });
	}
	
	
	
}

