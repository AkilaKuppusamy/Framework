package pages;


import org.springframework.stereotype.Component;

import utils.FakerDataFactory;

@Component
public class CreateLeadPage extends AutoWiredPages {
	
	public CreateLeadPage enterCompanyName(){
		clearAndType(locateElement("id", "createLeadForm_companyName"), FakerDataFactory.getCompanyName());
		return this;
	}

	public String enterFirstName(){
		String firstName = FakerDataFactory.getFirstName();
		clearAndType(locateElement("id", "createLeadForm_firstName"), firstName);
		return firstName;
	}

	public CreateLeadPage enterLastName(){
		clearAndType(locateElement("id", "createLeadForm_lastName"), FakerDataFactory.getLastName());
		return this;
	}

	public ViewLeadPage clickCreateLeadSubmit(){
		click(locateElement("class", "smallSubmit"));
		return viewLeadPage;
	}
	
	public CreateLeadPage enterEmail(){
		clearAndType(locateElement("id", "createLeadForm_primaryEmail"), FakerDataFactory.getEmailAddress());
		return this;
	}
}
