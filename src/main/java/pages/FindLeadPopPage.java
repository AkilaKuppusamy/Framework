package pages;

import org.springframework.stereotype.Component;

@Component
public class FindLeadPopPage extends AutoWiredPages {
	
	public FindLeadPopPage enterFirstName(String findfistname){
		clearAndType(locateElement("name", "firstName"), findfistname);
		return this;
	}

	public FindLeadPopPage clickFindleadsButton(){
		click(locateElement("xpath", prop.getProperty("findlead_search")));
		return this;
	}

	public String getFirstResultingLead(){	
		return getElementText(locateElement("xpath", "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]"));
	}

	public MergeLeadPage clickResultingLeads(){
		clickWithNoSnap(locateElement("xpath", "(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[1]"));
		switchToWindow(0);
		return mergeLeadPage;
	}
}