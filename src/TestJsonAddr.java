import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONArray;
import org.json.JSONException;

public class TestJsonAddr {

	public static void main(String[] args) {

		File file = new File("WebContent/file/addr.json");
		File fileUpate = new File("WebContent/file/addrNoEn.json");
		BufferedReader bfr = null;
		StringBuilder sb = new StringBuilder();
		JSONArray areas = null, roads = null;
		PrintWriter pw = null;

		try {
			bfr = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = bfr.readLine()) != null) {
				sb.append(temp);
			}
			JSONArray addrJson = new JSONArray(sb.toString());
			for (int i = 0; i < addrJson.length(); i++) {
				addrJson.getJSONObject(i).remove("CityEngName");
				areas = addrJson.getJSONObject(i).getJSONArray("AreaList");
				for (int j = 0; j < areas.length(); j++) {
					areas.getJSONObject(j).remove("AreaEngName");
					roads = areas.getJSONObject(j).getJSONArray("RoadList");
					for (int k = 0; k < roads.length(); k++) {
						roads.getJSONObject(k).remove("RoadEngName");
					}

				}
			}
			if(!fileUpate.exists()) {
				fileUpate.createNewFile();
			}
			pw = new PrintWriter(new FileWriter(fileUpate));
			pw.print(addrJson);

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (JSONException e) {
			
			e.printStackTrace();
		}finally {
			if(bfr != null) {
				try {
					bfr.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
			if(pw != null) {
				pw.close();
			}
		}

	}

}
