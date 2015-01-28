package com.palmwin.applang.bo;

import com.palmwin.applang.bo.impl.AndroidApp;
import com.palmwin.applang.bo.impl.IOSApp;

public class AppFactory {

	public static final String APP_ANDROID="Android";
	public static final String APP_IOS="IOS";
	public static IApp getApp(String path,String type){
		if(type.equalsIgnoreCase(APP_ANDROID)){
			return new AndroidApp(path);
		}
		if(type.equalsIgnoreCase(APP_IOS)){
			return new IOSApp(path);
		}
		return null;
	}
}
