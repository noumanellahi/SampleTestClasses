package com.mycompany.api.acq;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonPathFinder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			URL url = new URL("https://api.biorxiv.org/pub/2017-08-21/2017-08-28/30");
//			URL url = new URL(
//					"https://chemrxiv.org/engage/chemrxiv/public-api/v1/items?searchDateFrom=2022-03-07T00:00:00.000Z&searchDateTo=2022-03-08T00:00:00.000Z&limit=50&skip=0");

			URL url = new URL(
					"https://api.crossref.org/works?filter=from-created-date:2022-03-20,until-created-date:2022-03-31&rows=20&cursor=*");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			con.disconnect();

			DocumentContext jsonContext = JsonPath.parse(content.toString());

//			String jsonpathCreatorLocationPath = "$['collection'][*]['preprint_title']";
//			String jsonpathCreatorLocationPath = "$.collection.*";
//			String jsonpathCreatorLocationPath = "$.messages.*.total";
//			String jsonpathCreatorLocationPath = "$.itemHits.*.item.id";

			String jsonpathCreatorLocationPath = "$.message.next-cursor";

//			List<Object> jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);
			Object jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);

			if (jsonpathCreatorLocation instanceof List) {
				System.out.println("List");
				List<Object> parsedData = (List<Object>) jsonpathCreatorLocation;
				System.out.println(parsedData.get(0).toString());
			} else {
				System.out.println("Not List");
				System.out.println(jsonpathCreatorLocation.toString());
			}

//			System.out.println(jsonpathCreatorLocation.size());
//			System.out.println(Arrays.toString(jsonpathCreatorLocation.toArray()));
//			System.out.println(jsonpathCreatorLocation.get(0).toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
