package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hooks.TestNgHooks;

public class TC001_LoginLogOut extends TestNgHooks{	
	
	@BeforeTest
	public void setValues() {
		testCaseName = "Login and LoginOut";
		testDescription = "Login testCase and LogOut";
		nodes = "Leads";
		authors = "Babu";
		category = "Smoke";
		dataSheetName = "TC001";
	}

	@Test(dataProvider = "fetchData")
	public void login(String uName, String pwd) {
		loginPage
		.enterUserName(uName)
		.enterPassword(pwd)
		.clickLogin()
		.clickLogout();	
		
		loginPage
		.enterPassword(pwd)
		.clickLogInForFailure();
	}


}





