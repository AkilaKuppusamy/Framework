package pages;

import org.springframework.stereotype.Component;

@Component
public class ViewLeadPage extends AutoWiredPages  {
	
	public ViewLeadPage verifyFirstName(String fname) {
		verifyPartialText(locateElement("id", "viewLead_firstName_sp"), fname);
		return this;
	}
	
	public FindLeadPage clickFindLead(){
		click(locateElement("link", prop.getProperty("viewlead_find")));
		return findLeadPage;
	}
	
	public ViewLeadPage verifyCompanyName(String CompanyName) {
		verifyPartialText(locateElement("id", "viewLead_companyName_sp"), CompanyName);
		return this;
	}
	
	public EditLeadPage clickEditLeadLink(){
		click(locateElement("link", prop.getProperty("viewlead_edit")));
		return editLeadPage;
	}
	
	public DuplicateLeadPage clickDuplicateLeadLink(){
		click(locateElement("link", prop.getProperty("viewlead_duplicate")));
		return duplicateLeadPage;
	}

	public MyLeadsPage clickDeleteLeadLink(){
		click(locateElement("link",prop.getProperty("viewlead_delete")));
		return myLeadsPage;
	}
	
	

	
	
}
