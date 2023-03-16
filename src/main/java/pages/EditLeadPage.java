package pages;

import org.springframework.stereotype.Component;

@Component
public class EditLeadPage extends AutoWiredPages {
	
	public EditLeadPage updateCompanyName(String companyName){
		clearAndType(locateElement("id", "updateLeadForm_companyName"), companyName);
		return this;
	}

	public ViewLeadPage clickUpdateSubmit(){
		click(locateElement("class", "smallSubmit"));
		return viewLeadPage;
	}

}

