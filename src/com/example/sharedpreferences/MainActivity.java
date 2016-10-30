 package com.example.sharedpreferences;

import android.os.Bundle;


  
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private  DBSQliteHelper  dbHelper;
    private Button btnLogin,btnReset;
    private EditText editPsd,editUser;
    SharedPreferences loginPreferences, accessPreferences;
    SharedPreferences.Editor loginEditor, accessEditor;
    String  userName,userPsd ;
    boolean   isSavedPsd,isAutoLogin;
    private TextView userInfo;
    private CheckBox rememberPsd,AutoLogin;
	@Override
	protected void onCreate(Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loginPreferences=getSharedPreferences("login",Context.MODE_PRIVATE);
		accessPreferences=getSharedPreferences("access",Context.MODE_WORLD_READABLE);
		
		int count=accessPreferences.getInt("count", 1);
		Toast.makeText(MainActivity.this, "欢迎您，这是第"+count+"次访问", Toast.LENGTH_LONG).show();
		
		loginEditor=loginPreferences.edit();
		 accessEditor=accessPreferences.edit();
		 accessEditor.putInt("count", ++count);
		 accessEditor.commit();
		 userName=loginPreferences.getString("name", null);
		 userPsd = loginPreferences.getString("psd", null);
		 
		 
		 isSavedPsd = loginPreferences.getBoolean("isSavesPsd", false);
		    isAutoLogin=loginPreferences.getBoolean("isAutoLogin", false);
		    
		    if(isAutoLogin){
		    	this.setContentView(R.layout.activity_welcome);
		    	userInfo=(TextView) this.findViewById(R.id.userInfo);
		    }else{
		    	loadActivity();
		    }
		
		//数据库的创建和初始化
		/*dbHelper=new DBSQliteHelper(this,"sqlText",1);
		  initWidget();
		  addOnClickListener();*/
		
		 
		
	}

	private void loadActivity() {

		// TODO Auto-generated method stub
		this.setContentView(R.layout.activity_main);
		if(isSavedPsd){
			editPsd.setText(userPsd);
			editUser.setText(userName);
			rememberPsd.setChecked(true);
			
		}
		
	}

	private void addOnClickListener() {


		// TODO Auto-generated method stub
		/* BTNListener  btnsql=new BTNListener();
		 btnLogin.setOnClickListener(btnsql);
		 btnReset.setOnClickListener(btnsql);*/
		LoginListener login=new LoginListener();
		btnLogin.setOnClickListener(login);
		
	}

	private void initWidget() {


		// TODO Auto-generated method stub
		btnLogin=(Button) this.findViewById(R.id.btnLogin);
		//btnReset=(Button) this.findViewById(R.id.btnReset);
		editPsd=(EditText) this.findViewById(R.id.editPsd);
		editUser=(EditText) this.findViewById(R.id.editUser);
		userInfo=(TextView) this.findViewById(R.id.userInfo);
	}
    
	/*private class BTNListener implements OnClickListener{



		@Override
		public void onClick(View v) {

			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btnLogin:
				String StrUname=editUser.getText().toString().trim();
				String StrPsd=editPsd.getText().toString().trim();
				
				String sql="select * from userlogin where username=?";
				SQLiteDatabase db = dbHelper.getReadableDatabase();
				Cursor c=db.rawQuery(sql, new String[]{StrUname});
				while(c.moveToNext()){
					String pwd=c.getString(c.getColumnIndex("password"));
					if(pwd==StrPsd){
						System.out.println("登陆成功");
						break;
					}
				}
				c.close();
				break;
			case R.id.btnReset:
				editUser.setText("");
				editPsd.setText("");
				break;
				default:
				
			}
		}
		
	}*/
		
	private class LoginListener implements OnClickListener{


		@Override
		public void onClick(View v) {

			// TODO Auto-generated method stub
			 loginEditor.putString("name", editUser.getText().toString());
			 loginEditor.putString("psd", editPsd.getText().toString());
			 loginEditor.putBoolean("isSavePsd", rememberPsd.isChecked());
			 loginEditor.putBoolean("isAutoLogin", AutoLogin.isChecked());
			 loginEditor.commit();
			  MainActivity.this.setContentView(R.layout.activity_welcome);
			  
			  userInfo.setText("欢迎您："+editUser.getText().toString()+"登录成功");
		}
		
	}
	
	private Object getSharedPreferences(String string, String string2) {


		// TODO Auto-generated method stub
		return null;
	}
  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {


		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {


		switch (item.getItemId()) {
		case R.id.menu_settings:
			loginEditor.putBoolean("isAutoLogin", false);
			loginEditor.commit();
			onCreate(null);
			break;
		case R.id.edit:
			this.finish();
			break;
		default:
			break;
		}
		return true;
	}
	
	
    
  }


