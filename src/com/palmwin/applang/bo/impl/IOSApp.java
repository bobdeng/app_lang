package com.palmwin.applang.bo.impl;

import java.util.Map;
import java.util.Set;

import com.palmwin.applang.bo.IApp;
import com.palmwin.applang.model.StringRes;

public class IOSApp implements IApp {
	private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public IOSApp(String path) {
		super();
		this.path = path;
	}


	@Override
	public Map<String, StringRes> getLang(String lang) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void writeLang(Map<String, StringRes> values, String lang) {
		// TODO Auto-generated method stub
		
	}
	
}
