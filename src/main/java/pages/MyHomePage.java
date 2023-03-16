package pages;

import org.springframework.stereotype.Component;

@Component
public class MyHomePage extends AutoWiredPages {

	public MyLeadsPage clickLeadLink(){
		click(locateElement("link",prop.getProperty("myhome_leads")));
		return myLeadsPage;
	}


}
