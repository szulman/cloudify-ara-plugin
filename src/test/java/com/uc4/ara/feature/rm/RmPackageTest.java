package com.uc4.ara.feature.rm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uc4.ara.feature.globalcodes.ErrorCodes;

public class RmPackageTest extends RmAbstractTest {

	@Test
	public void setPackageStateTest() throws Exception {
		SetPackageState test = new SetPackageState();
		String[] args = new String[] { rmHost, rmUsername, rmPassword, "2644",
				"Inactive", "Active", "FAIL" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

}
