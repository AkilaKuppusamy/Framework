package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hooks.TestNgHooks;

public class TC002_LoginForFailure extends TestNgHooks{

	@BeforeTest
	public void setValues() {

		testCaseName = "Login(Negative)";
		testDescription = "Login for Failure(Negative testCase)";
		nodes = "Leads";
		authors = "Babu";
		category = "Smoke";
		dataSheetName = "TC002";

	}

	@Test(dataProvider = "fetchData")
	public void login(String uName, String pwd, String errMsg) {
		loginPage
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickLogInForFailure()
		.verifyErrorMsg(errMsg);
	}


}





