package pages;

import org.springframework.stereotype.Component;

@Component
public class MyLeadsPage extends AutoWiredPages {
	
	public CreateLeadPage clickCreateLead(){
		click(locateElement("link", prop.getProperty("mylead_create")));
		return createLeadPage;
	}

	public FindLeadPage clickFindLead(){
		click(locateElement("link", prop.getProperty("mylead_find")));
		return findLeadPage;
	}
	
	public MergeLeadPage clickMergeLead(){
		click(locateElement("link", prop.getProperty("mylead_merge")));		
		return mergeLeadPage;
	}

}
