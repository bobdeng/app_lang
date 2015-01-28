package com.palmwin.applang.bo;

import com.palmwin.applang.bo.impl.GitProject;

public class ProjectFactory {
	private static String rootPath;
	public static IProject getProject(String type){
		if(type.equalsIgnoreCase("git")){
			return new GitProject();
		}
		return null;
	}
	public static String getRootPath() {
		return rootPath;
	}
	public static void setRootPath(String rootPath) {
		ProjectFactory.rootPath = rootPath;
	}
	
	
}
