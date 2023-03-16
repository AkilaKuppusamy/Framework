package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hooks.PreHooks;

public class TC005_DeleteLead extends PreHooks{
	
	@BeforeTest
	public void setData() {
		testCaseName="TC005_DeleteLead";
		testDescription="Search by Phone Number and Delete the Lead";
		nodes = "Leads";
		dataSheetName="TC005";
		category="Sanity";
		authors="Babu";
	}

	@Test(dataProvider="fetchData")
	public void deleteLead(String phoneNum,String errorMsg){

		String firstResultingLead =
				homePage
				.clickCRMSFA()
				.clickLeadLink()		
				.clickFindLead()
				.clickPhoneTab()
				.enterPhoneNumberField(phoneNum)
				.clickFindleadsButton()
				.getFirstResultingLead();
		
				findLeadPage
				.clickFirstResultingLead()
				.clickDeleteLeadLink()
				.clickFindLead()
				.enterLeadId(firstResultingLead)
				.clickFindleadsButton()
				.verifyErrorMsg(errorMsg);
	}
}
