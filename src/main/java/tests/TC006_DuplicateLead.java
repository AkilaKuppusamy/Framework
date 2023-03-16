package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hooks.PreHooks;

public class TC006_DuplicateLead extends PreHooks{

	@BeforeTest
	public void setData() {
		testCaseName="TC006_DuplicateLead";
		testDescription="Search by Email and Duplicate the Lead";
		nodes = "Leads";
		dataSheetName="TC006";
		category="Regression";
		authors="Babu";
	}

	@Test(dataProvider="fetchData")
	public void duplicateLead(String emailAddress) throws InterruptedException{

		String fName=
				homePage
				.clickCRMSFA()		
				.clickLeadLink()		
				.clickFindLead()
				.clickEmailTab()
				.enterEmailAddress(emailAddress)
				.clickFindleadsButton()
				.getFirstResultingFirstName();
			
				findLeadPage
				.clickFirstResultingLead()
				.clickDuplicateLeadLink()
				.clickCreateLeadDuplicate()
				.verifyFirstName(fName);

	}

}
