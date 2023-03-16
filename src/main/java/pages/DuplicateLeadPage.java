package pages;

import org.springframework.stereotype.Component;

@Component
public class DuplicateLeadPage extends AutoWiredPages {

	public ViewLeadPage clickCreateLeadDuplicate(){
		click(locateElement("class", "smallSubmit"));
		return viewLeadPage;
	}
}
