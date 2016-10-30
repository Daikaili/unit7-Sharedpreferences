package com.example.sharedpreferences;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQliteHelper extends SQLiteOpenHelper {
	
	//构造函数：类的默认入口
	//构造函数：四个参数
	public DBSQliteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		
		// TODO Auto-generated constructor stub
	}
	//构造函数：三个参数
		public  DBSQliteHelper(Context context, String name,int version){
			super(context,name,null,version);
		}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.print("Creating Database..");
		String CreateTablesql="create table userlogin("
				+"_id integer primary key autoincrement"
				+"username varchar"
				+"password varchar"
				+"loginNum interger,"+"loginDatatime text)";
					
  db.execSQL(CreateTablesql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
