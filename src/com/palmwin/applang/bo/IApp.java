package com.palmwin.applang.bo;

import java.util.Map;
import java.util.Set;

import com.palmwin.applang.model.StringRes;

public interface IApp {

	//获取指定语言的值，表示默认语言
	public Map<String,StringRes> getLang(String lang);
	//把语言写入磁盘
	public void writeLang(Map<String,StringRes> values,String lang) throws Exception;
}
