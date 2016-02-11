package com.uc4.ara.feature.rm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uc4.ara.feature.globalcodes.ErrorCodes;

public class RMGetPropertyTest extends RmAbstractTest {

	@Test
	public void getPropertyWrongPW() throws Exception {
		GetProperty test = new GetProperty();
		String[] args = new String[] { rmHost, rmUsername, "wrongPW",
				"Application", "IIS_APP", "repo_password", "yes" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test
	public void getPropertyOneItemTest() throws Exception {
		GetProperty test = new GetProperty();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Application", "IIS_APP", "repo_password", "yes" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test
	public void getPropertyAllItemsTest() throws Exception {
		GetProperty test = new GetProperty();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Activity", "tokenizerActivity", "", "yes" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test
	public void getPropertyByIDTest() throws Exception {
		GetProperty test = new GetProperty();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Application", "1883", "repo_password", "yes" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test(expected = RuntimeException.class)
	public void failIfMissingTest() throws Exception {
		GetProperty test = new GetProperty();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Application", "1883", "missingProperty", "yes" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test
	public void ignoreIfMissingTest() throws Exception {
		GetProperty test = new GetProperty();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Application", "1883", "missingProperty", "no" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

}
