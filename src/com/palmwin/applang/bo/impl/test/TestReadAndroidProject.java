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
			Map<String, StringRes> res = app.getLang("zh");
			assertTrue("res is null", res != null && res.size() > 0);
			StringRes btn_answer_video = res.get("btn_answer_video");
			assertNotNull(btn_answer_video);
			assertEquals(btn_answer_video.getValue(), "Answer");
			assertEquals(btn_answer_video.getLang(), "接听");
			StringRes photo_select = res.get("photo_select");
			assertNotNull(photo_select);
			assertEquals(photo_select.getValue(),"Camera||Gallery");
			assertEquals(photo_select.getLang(), "相机||相册");
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
			Map<String, StringRes> res = app.getLang("zh");
			assertTrue("res is null", res != null && res.size() > 0);
			StringRes btn_answer_video = res.get("btn_answer_video");
			assertNotNull(btn_answer_video);
			btn_answer_video.setValue("AnswerTest");
			btn_answer_video.setLang("测试");
			StringRes photo_select=res.get("photo_select");
			photo_select.setValue("Test1||Test2");
			photo_select.setLang("测试1||测试2");
			app.writeLang(res, null);
			app.writeLang(res, "zh");
			res = app.getLang("zh");
			assertEquals(res.get("btn_answer_video").getValue(), "AnswerTest");
			assertEquals(res.get("photo_select").getValue(), "Test1||Test2");
			assertEquals(res.get("btn_answer_video").getLang(), "测试");
			assertEquals(res.get("photo_select").getLang(), "测试1||测试2");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//project.resetProject();
		}

	}
	@Override
	protected void tearDown() throws Exception {
		

		super.tearDown();
	}
}
