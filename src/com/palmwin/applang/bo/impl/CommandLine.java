package com.palmwin.applang.bo.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandLine {

	public static String execute(String command,File workdir){
		try {
			Process process=Runtime.getRuntime().exec(command,null,workdir);
			InputStream input=process.getErrorStream();
			BufferedReader reader=new BufferedReader(new InputStreamReader(input));
			StringBuffer ret=new StringBuffer();
			String line=null;
			while((line=reader.readLine())!=null){
				ret.append(line);
				ret.append("\n");
				System.out.println(line);
			}
			return ret.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
