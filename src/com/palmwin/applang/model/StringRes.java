package com.palmwin.applang.model;

public class StringRes {

	private String key;
	private boolean array;
	private boolean comment;
	private String value;
	private String lang;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isArray() {
		return array;
	}
	public void setArray(boolean array) {
		this.array = array;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean isComment() {
		return comment;
	}
	public void setComment(boolean comment) {
		this.comment = comment;
	}
	public String getLang() {
		if(lang==null){
			return value;
		}
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	@Override
	public String toString() {
		return "StringRes [key=" + key + ", array=" + array + ", comment="
				+ comment + ", value=" + value + ", lang=" + lang + "]\n";
	}
	
}
