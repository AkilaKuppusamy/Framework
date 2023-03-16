package hooks;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import config.ConfigurationManager;
import dataProvider.AdapterDataProvider;
import pages.AutoWiredPages;

public class TestNgHooks extends AutoWiredPages {

	public String dataSheetName;	

	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return AdapterDataProvider.getDataSource(dataSheetName);
	}	

	@BeforeMethod
	public void beforeMethod() {
		startApp("chrome", ConfigurationManager.configuration().baseUrl());
		setNode();
	}
	

	@AfterMethod
	public void afterMethod() {
		close();
	}













}
