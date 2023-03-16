package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import hooks.PreHooks;


public class TC007_MergeLead extends PreHooks {

	@BeforeTest
	public void setValues(){
		testCaseName = "TC007_MergeLead";
		testDescription = "Merge two Lead on LeafTaps";
		nodes = "Leads";
		dataSheetName = "TC007";
		category = "Regression";
		authors = "Babu";		
	}

	@Test(dataProvider = "fetchData")
	public void mergeLead(String firstName,String leadName, String errorMsg){
		String fromLeadId = 
				homePage
				.clickCRMSFA()
				.clickLeadLink()
				.clickMergeLead()
				.clickFromLeadLookup()
				.enterFirstName(firstName)
				.clickFindleadsButton()
				.getFirstResultingLead();
				
				findLeadPopPage
				.clickResultingLeads()
				.clickToLeadLookup()
				.enterFirstName(leadName)
				.clickFindleadsButton()
				.clickResultingLeads()
				.clickMergeButton()
				.clickFindLead()
				.enterLeadId(fromLeadId)
				.clickFindleadsButton()
				.verifyErrorMsg(errorMsg);

	}


}










