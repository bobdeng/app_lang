package com.palmwin.applang.bo.impl;

import java.io.File;

import com.palmwin.applang.bo.IProject;
import com.palmwin.applang.bo.ProjectFactory;

public class GitProject implements IProject {

	private String repositoryPath;
	private String projectPath;
	private String projectName;
	public GitProject() {
	}
	
	public String getRepositoryPath() {
		return repositoryPath;
	}

	public void setRepositoryPath(String repositoryPath) {
		this.repositoryPath = repositoryPath;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public void newProject(String resposityPath,String name, String path, String type)throws Exception {
		this.repositoryPath=resposityPath;
		if(ProjectFactory.getRootPath()==null){
			throw new Exception("RootPath not set");
		}
		File rootPath=new File(ProjectFactory.getRootPath());
		if(!rootPath.exists() && !rootPath.mkdirs()){
			throw new Exception("RootPath create fail:"+rootPath.getCanonicalPath());
		}
		File projectPath=new File(rootPath,name);
		if(projectPath.exists()){
			throw new Exception("Project exist");
		}
		if(!projectPath.mkdirs()){
			throw new Exception("Project create folder fail");
		}
		this.projectName=name;
		this.projectPath=path;
		String commandRet=CommandLine.execute(String.format("git clone %s %s", repositoryPath,new File(rootPath,name).getCanonicalPath()),null);
		if(commandRet==null || !commandRet.contains("Checking out files: 100%")){
			projectPath.delete();
			throw new Exception("Project checkout fail:\n"+commandRet);
		}
	}

	@Override
	public void commitProject() {
		String commandRet=CommandLine.execute("git add .",new File(new File(ProjectFactory.getRootPath(),projectName),projectPath));
	}

	@Override
	public File getProjectSourcePath() {
		return new File(new File(ProjectFactory.getRootPath(),projectName),projectPath);
	}

}
