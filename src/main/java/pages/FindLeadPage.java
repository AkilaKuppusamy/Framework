package pages;

import org.springframework.stereotype.Component;

@Component
public class FindLeadPage extends AutoWiredPages {

	public FindLeadPage enterFirstName(String findfistname){
		clearAndType(locateElement("xpath", "(//input[@name='firstName'])[3]"), findfistname);
		return this;
	}
	
	public FindLeadPage enterLeadId(String findfistname){
		clearAndType(locateElement("name", "id"), findfistname);
		return this;
	}
	
	public FindLeadPage clickFindleadsButton(){
		click(locateElement("xpath", prop.getProperty("findlead_search")));
		return this;
	}
	
	public String getFirstResultingLead(){	
		return 	getElementText(locateElement("xpath", "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]"));
	}

	public String getFirstResultingFirstName() throws InterruptedException{	
		Thread.sleep(1000);
		return 	getElementText(locateElement("xpath", "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]"));
	}

	public ViewLeadPage clickFirstResultingLead(){
		click(locateElement("xpath", "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]"));
		return viewLeadPage;
	}

	public FindLeadPage clickPhoneTab(){
		click(locateElement("xpath",prop.getProperty("findlead_phone")));
		return this;
	}

	public FindLeadPage clickEmailTab(){
		click(locateElement("xpath", prop.getProperty("findlead_email")));
		return this;
	}

	public FindLeadPage enterPhoneNumberField(String PhoneNumber){
		clearAndType(locateElement("name", "phoneNumber"), PhoneNumber);
		return this;
	}

	public FindLeadPage enterEmailAddress(String emailAddress){
		clearAndType(locateElement("name", "emailAddress"), emailAddress);
		return this;
	}

	public FindLeadPage verifyErrorMsg(String eleErrorMsgValue){
		verifyPartialText(locateElement("class", "x-paging-info"), eleErrorMsgValue);
		return this;
	}
	
}