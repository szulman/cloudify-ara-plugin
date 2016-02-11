package com.uc4.ara.feature.rm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uc4.ara.feature.globalcodes.ErrorCodes;

public class RmOwnerTest extends RmAbstractTest {

	@Test
	public void setOwnerTest() throws Exception {
		SetOwner test = new SetOwner();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Application", "tokenizerApplication", "1/extmis/sbb01" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test
	public void getOwnerTest() throws Exception {
		GetOwner test = new GetOwner();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Application", "Tokenizer" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}
}
