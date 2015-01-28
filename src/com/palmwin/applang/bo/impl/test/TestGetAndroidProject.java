package com.palmwin.applang.bo.impl.test;

import com.palmwin.applang.bo.AppFactory;
import com.palmwin.applang.bo.IProject;
import com.palmwin.applang.bo.ProjectFactory;

public class TestGetAndroidProject {

	public static void main(String[] args) throws Exception{
		ProjectFactory.setRootPath("/Users/zhiguodeng/app_lang/");
		IProject project=ProjectFactory.getProject("git");
		project.newProject("git@192.168.1.248:gongyingjun/hw_face_channel_android.git","chatgame_1.2", "ChatGame_1.2", AppFactory.APP_ANDROID);
	}
}
