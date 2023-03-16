package dataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonDataProvider implements TestDataProvider {



	private String fileName;


	public JsonDataProvider(String fileName) {
		this.fileName = fileName;

	}
	@Override
	public Object[][] getTestData() throws FileNotFoundException {
		FileReader reader = new FileReader("./data/"+fileName+".json");
		JSONTokener tokener = new JSONTokener(reader);
		JSONArray jsonArray = new JSONArray(tokener);
		Object[][] data = new Object[jsonArray.length()][jsonArray.getJSONObject(0).keySet().size()];
		int j=0;
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			for (String key : jsonObject.keySet())
				data[i][j++] = jsonObject.get(key);
		}
		return data;
	}

}
