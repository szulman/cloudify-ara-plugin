package com.uc4.ara.feature.rm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uc4.ara.feature.globalcodes.ErrorCodes;

public class RmActivityTest extends RmAbstractTest {

	@Test
	public void createActivityTest() throws Exception {
		{
			DeleteObject test = new DeleteObject();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Activity", "newActivity", "no" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
		{
			CreateDeployActivity test = new CreateDeployActivity();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Standard Activity", "newActivity", rmUsername, "BUILD",
					"2012-07-01T01:02:00Z", "PT10H" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
		{
			DeleteObject test = new DeleteObject();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Activity", "newActivity", "no" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
	}

	@Test
	public void createActivityFromTemplateTest() throws Exception {
		CreateActivityFromTemplate test = new CreateActivityFromTemplate();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Deployment Activity Template", rmUsername, "DEV_FOLDER",
				"2012-10-21 08:09:12" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test
	public void getActivityState() throws Exception {
		GetActivityState test = new GetActivityState();
		String[] args = new String[] { rmHost, rmUsername, rmPassword, "2537" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}
}
