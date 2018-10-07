package com.example.uiwidgettest;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private Button button;
	private EditText editText;
	private ImageView imageView;
	private ProgressBar progressBar;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button) findViewById(R.id.button);
		editText = (EditText) findViewById(R.id.edit_text);
		imageView = (ImageView) findViewById(R.id.image_view);
		progressBar = (ProgressBar) findViewById(R.id.progress_bar);
		button.setOnClickListener(this);
	}
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.button:
			String inputText = editText.getText().toString();
			int progress = progressBar.getProgress();
			progress = progress + 10;
			progressBar.setProgress(progress);
			/*if(progressBar.getVisibility() == View.GONE){
				progressBar.setVisibility(View.VISIBLE);
			}
			else
				progressBar.setVisibility(View.GONE);*/
			
			if(progress == 60){
				ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
				progressDialog.setTitle("This is ProgressDialog");
				progressDialog.setMessage("Loading...");
				progressDialog.show();
			}
			
			if(progress == 100){
				AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
				dialog.setTitle("This is Dialog");
				dialog.setMessage("Something important.");
				dialog.setCancelable(false);
				dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
					
					public void onClick(DialogInterface dialog, int which){
					}
				});
				dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
					
					public void onClick(DialogInterface dialog, int which){
					}
				});
				dialog.show();
			}
			
			imageView.setImageResource(R.drawable.jelly_bean);
			Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
			break;
		default:
				break;		
		}
	}
	
}
