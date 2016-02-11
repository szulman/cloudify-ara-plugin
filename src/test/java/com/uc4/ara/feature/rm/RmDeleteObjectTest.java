package com.uc4.ara.feature.rm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uc4.ara.feature.globalcodes.ErrorCodes;

public class RmDeleteObjectTest extends RmAbstractTest {

	@Test
	public void deleteObjectNotFound1Test() throws Exception {
		DeleteObject test = new DeleteObject();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Package", "newPackageNotFound", "yes" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.PARAMSMISMATCH, ret);
	}

	@Test
	public void deleteObjectNotFound2Test() throws Exception {
		DeleteObject test = new DeleteObject();
		String[] args = new String[] { rmHost, rmUsername, rmPassword,
				"Package", "newPackageNotFound", "no" };
		int ret = test.run(args);
		assertEquals(ErrorCodes.OK, ret);
	}

	@Test
	public void deleteObjectTest() throws Exception {
		{
			CreatePackage test = new CreatePackage();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Generic", "newPackage", rmUsername, "Folder" };
			/* int ret = */test.run(args);
			// assertEquals(ErrorCodes.OK, ret);
			// OK or Error depending whether the type is available or not
		}
		{
			DeleteObject test = new DeleteObject();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Package", "newPackage", "no" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
	}

}
