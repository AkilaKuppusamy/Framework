package hooks;

import org.testng.annotations.BeforeMethod;

import config.ConfigurationManager;

public class PreHooks extends TestNgHooks {

	@BeforeMethod
	public void beforeMethod() {
		super.beforeMethod();
		loginPage.doLogin(ConfigurationManager.configuration().appUserName(), 
				ConfigurationManager.configuration().appPassword());
	}

}
