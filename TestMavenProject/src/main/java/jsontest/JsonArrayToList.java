package jsontest;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonArrayToList {
	public static void main(String[] args ) {
		try {
			String test = "[\"asa,sa\",\"asasas\",\"saasas\"]";
//			String test = "[\"[-EEA-]\"]";
			ObjectMapper mapper = new ObjectMapper();
			List<String> strings = mapper.readValue(test, List.class);
			System.out.println(strings);
			System.out.println(strings.get(0));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
