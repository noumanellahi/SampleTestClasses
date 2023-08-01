package com.mycompany.gson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StringMappingInGson {
	public static void main(String[] args) {
		try {
			String Value = "{\"searchValue\":\"26/10/2021\",\"offset\":null}";
			Gson gson = new GsonBuilder().create();
			SearchFormCalendarDTO searchFormCalendarDTO = gson
					.fromJson(Value, SearchFormCalendarDTO.class);
			System.out.println(searchFormCalendarDTO.getOffset());
			
			new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
