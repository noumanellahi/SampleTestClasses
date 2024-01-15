package com.mycompany.string;

public class ReplacementTestForEpOPSApiPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String[] values = { "ch", "719243", "B1" };
			String identifier = "{value_%d}";
			int count = 3;

			String fixedPath = "http://ops.epo.org/3.2/rest-services/published-data/images/{value_1}/{value_2}/{value_3}/fullimage.tiff?Range=1";

			for (int i = 0; i < values.length; i++) {
				String valueIentifier = String.format(identifier, i + 1);

				System.out.println(valueIentifier);

				fixedPath = fixedPath.replace(valueIentifier, values[i]);
			}
			System.out.println(fixedPath);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
