package com.uc4.ara.feature.rm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uc4.ara.feature.globalcodes.ErrorCodes;

public class RmSetPropertyTest extends RmAbstractTest {

	@Test
	public void setPropertyTest() throws Exception {
		SetProperty test = new SetProperty();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Application", "tokenizerApplication", "system_description",
				"myDescriptionFromJUnit Test" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

}
