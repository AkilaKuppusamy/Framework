package pages;

import org.springframework.stereotype.Component;

@Component
public class MergeLeadPage extends AutoWiredPages {

	public FindLeadPopPage clickFromLeadLookup(){
		clickWithNoSnap(locateElement("xpath", "(//img[@alt='Lookup'])[1]"));
		switchToWindow(1);
		return findLeadPopPage;		
	}

	public FindLeadPopPage clickToLeadLookup(){
		clickWithNoSnap(locateElement("xpath", "(//img[@alt='Lookup'])[2]"));
		switchToWindow(1);
		return findLeadPopPage;		
	}

	public ViewLeadPage clickMergeButton(){
		clickWithNoSnap(locateElement("link",prop.getProperty("mergelead_merge")));
		acceptAlert();
		return viewLeadPage;
	}
}