package com.mycompany.api.acq;

import java.util.List;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.filter.ValueNode.JsonNode;

public class JsonPathFinderElsiver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			URL url = new URL("https://api.biorxiv.org/pub/2017-08-21/2017-08-28/30");
//			URL url = new URL(
//					"https://chemrxiv.org/engage/chemrxiv/public-api/v1/items?searchDateFrom=2022-03-07T00:00:00.000Z&searchDateTo=2022-03-08T00:00:00.000Z&limit=50&skip=0");
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestProperty("User-Agent",
//					"Mozilla/5.0 (X11; Ubuntu; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36 RuxitSynthetic/1.0 v4818569895 t38550");
//			con.setRequestMethod("GET");
//
//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			String inputLine;
//			StringBuffer content = new StringBuffer();
//			while ((inputLine = in.readLine()) != null) {
//				content.append(inputLine);
//			}
//			in.close();
//			con.disconnect();

			String testJson = "{\r\n" + "    \"date\": \"2022-03-18\",\r\n" + "    \"noOfFiles\": 9,\r\n"
					+ "    \"presignedUrl\": \"https://uat-apollo-staging-bucket.s3.eu-west-1.amazonaws.com/clarivateFeed/2022-03-18%2012%3A00%3A15.zip?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20220318T120020Z&X-Amz-SignedHeaders=host&X-Amz-Expires=259199&X-Amz-Credential=AKIAQ3JZKOVFPPYMGC5M%2F20220318%2Feu-west-1%2Fs3%2Faws4_request&X-Amz-Signature=51ba0d8591ddc85ac2ac7aaa3cdd1043a018a2b50f3c6a3cfb5f6e97de4c8fb6\",\r\n"
					+ "    \"recordCreationTime\": \"2022-03-18T12:00:15.759Z\",\r\n"
					+ "    \"zipFileName\": \"2022-03-18 12:00:15.zip\"\r\n" + "}";

			DocumentContext jsonContext = JsonPath.parse(testJson.toString());

//			String jsonpathCreatorLocationPath = "$['collection'][*]['preprint_title']";
//			String jsonpathCreatorLocationPath = "$.collection.*";
//			String jsonpathCreatorLocationPath = "$.messages.*.total";
//			String jsonpathCreatorLocationPath = "$.itemHits.*.item.id";
			String jsonpathCreatorLocationPath = "$.presignedUrl";

			Object jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);
			if(jsonpathCreatorLocation instanceof List) {
				System.out.println("List");
			} else {
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
