package com.palmwin.applang.bo;

import java.io.File;

public interface IProject {

	public void newProject(String resposityPath,String name,String path,String type) throws Exception;
	public void setProjectName(String name);
	public void setProjectPath(String path);
	public void commitProject();
	public File getProjectSourcePath();

}
