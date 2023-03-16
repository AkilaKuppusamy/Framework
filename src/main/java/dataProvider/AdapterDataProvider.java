package dataProvider;

import java.io.IOException;

import config.ConfigurationManager;

public class AdapterDataProvider {

	  static TestDataProvider dataProvider;
	public static Object[][] getDataSource(String dataSheetName) throws IOException {

		String dataSourceFormat = ConfigurationManager.configuration().dataSourceFormat();

		if (dataSourceFormat.equalsIgnoreCase("json")) {
			dataProvider =  new JsonDataProvider(dataSheetName);
		} else if (dataSourceFormat.equalsIgnoreCase("excel")) {
			dataProvider =  new ExcelDataProvider(dataSheetName);
		} else {
			throw new IllegalArgumentException("Invalid data source format: " + dataSourceFormat);
		}
		return dataProvider.getTestData();
	}
}
