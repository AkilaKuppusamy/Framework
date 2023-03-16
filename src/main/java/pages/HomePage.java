package pages;

import org.springframework.stereotype.Component;

@Component
public class HomePage extends AutoWiredPages{
	
	public HomePage verifyLoggedName(String data) {
		verifyPartialText(locateElement("xpath", "//h2[text()[contains(.,'Welcome')]]"), data);
		return this;
	}

	public MyHomePage clickCRMSFA(){
		click(locateElement("link", "CRM/SFA"));
		return myHomePage;
	}

	public LoginPage clickLogout() {
		click(locateElement("class", "decorativeSubmit"));
		return loginPage;
	}
	
	

}










