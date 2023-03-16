package pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import factory.BaseDriver;

@Component
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AutoWiredPages extends BaseDriver {
		
	@Lazy
	@Autowired
	protected LoginPage loginPage;
	
	@Autowired
	protected HomePage homePage;
	
	@Autowired
	protected MyHomePage myHomePage;
	
	@Autowired
	protected MyLeadsPage myLeadsPage;
	
	@Autowired
	protected ViewLeadPage viewLeadPage;
	
	@Autowired
	protected MergeLeadPage mergeLeadPage;
	
	@Autowired
	protected FindLeadPopPage findLeadPopPage;
	
	@Autowired
	protected CreateLeadPage createLeadPage;
	
	@Autowired
	protected FindLeadPage findLeadPage;
	
	@Autowired
	protected EditLeadPage editLeadPage;
	
	@Autowired
	protected DuplicateLeadPage duplicateLeadPage;
	

}
