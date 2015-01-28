package com.palmwin.applang.bo.impl.test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import com.palmwin.applang.bo.AppFactory;
import com.palmwin.applang.bo.IApp;
import com.palmwin.applang.bo.IProject;
import com.palmwin.applang.bo.ProjectFactory;
import com.palmwin.applang.model.StringRes;

public class TestReadAndroidProject extends TestCase {
	IProject project;

	@Override
	protected void setUp() throws Exception {
		ProjectFactory.setRootPath("/Users/zhiguodeng/app_lang/");
		project=ProjectFactory.getProject("git");
		project.setProjectName("chatgame_1.2");
		project.setProjectPath("ChatGame_1.2");
		super.setUp();
	}



	public void testRead() {
		IApp app;
		try {
			app = AppFactory.getApp(project.getProjectSourcePath()
					.getCanonicalPath(), AppFactory.APP_ANDROID);
			Set<String> keys = app.getKeys();
			assertTrue("keys is null", keys != null && keys.size() > 0);
			Map<String, StringRes> res = app.getLang(null);
			assertTrue("res is null", res != null && res.size() > 0);
			StringRes btn_answer_video = res.get("btn_answer_video");
			assertNotNull(btn_answer_video);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void testWrite() {
		IApp app;
		try {
			app = AppFactory.getApp(project.getProjectSourcePath()
					.getCanonicalPath(), AppFactory.APP_ANDROID);
			Set<String> keys = app.getKeys();
			assertTrue("keys is null", keys != null && keys.size() > 0);
			Map<String, StringRes> res = app.getLang(null);
			assertTrue("res is null", res != null && res.size() > 0);
			StringRes btn_answer_video = res.get("btn_answer_video");
			assertNotNull(btn_answer_video);
			btn_answer_video.setValue("AnswerTest");
			StringRes rate_chatgame=res.get("rate_chatgame");
			rate_chatgame.setValue("A||B||C");
			app.writeLang(res, null);
			res = app.getLang(null);
			assertEquals(res.get("btn_answer_video").getValue(), "AnswerTest");
			assertEquals(res.get("rate_chatgame").getValue(), "A||B||C");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	protected void tearDown() throws Exception {
		

		super.tearDown();
	}
}
