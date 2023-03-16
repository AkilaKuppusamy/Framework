package pages;

import org.springframework.stereotype.Component;

@Component
public class LoginPage extends AutoWiredPages{
	
	public LoginPage() {
		System.out.println("The Login object created");
	}
	
	public LoginPage enterUserName(String data) {
		clearAndType(locateElement("id", "username"), data);
		return this;
	}	

	public LoginPage enterPassword(String data) {
		clearAndType(locateElement("id", "password"), data);
		return this;
	}	
	
	public HomePage clickLogin() {
		click(locateElement("class", "decorativeSubmit"));
		return homePage;		
	}
	
	public LoginPage clickLogInForFailure() {
		click(locateElement("class", "decorativeSubmit"));
		return this;		
	}
	
	public LoginPage verifyErrorMsg(String data) {
		verifyPartialText(locateElement("id", "errorDiv"), data);
		return this;
	}
	
	public HomePage doLogin(String username, String password) {
		return enterUserName(username).enterPassword(password).clickLogin();
	}
	
	
}
