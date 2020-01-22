package com.driven.testcases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.driven.base.TestBase;
import com.driven.utilities.Log;

public class BankManagerLoginTest extends TestBase
{
	@Test
	public void demoTest1()
	{
		
		Assert.assertEquals("abc", "abc");
		Log.info("Passed");
		Reporter.log("Action Successful");
	}
	

}
