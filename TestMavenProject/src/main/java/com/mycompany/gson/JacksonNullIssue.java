package com.mycompany.gson;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonNullIssue {

	public static void main(String[] args) {
		try {
			// TODO Auto-generated method stub
			Object Value = "{\"name\":\"John\", \"age\":30}";
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			Test orderDtoListArr = mapper.convertValue(Value, Test.class);
			System.out.println(orderDtoListArr.getCar());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
