package com.uc4.ara.feature.rm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.uc4.ara.feature.globalcodes.ErrorCodes;

public class RmCreatePackageTest extends RmAbstractTest {

	@Test
	public void createPackageTest() throws Exception {
		{
			DeleteObject test = new DeleteObject();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Package", "newPackage", "no" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
		{
			CreatePackage test = new CreatePackage();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Generic", "newPackage", rmUsername, "Folder" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
	}

	@Test
	public void createDeployPackageTest() throws Exception {
		{
			DeleteObject test = new DeleteObject();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"Package", "newDeployPackage", "no" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
		{
			CreateDeployPackage test = new CreateDeployPackage();
			String[] args = new String[] { rmHost, rmUsername, rmPassword,
					"newDeployPackage", "tokenizerApplication", "V1.00", "0.2", "Generic",
					rmUsername, "Folder" };
			int ret = test.run(args);
			assertEquals(ErrorCodes.OK, ret);
		}
	}

}
