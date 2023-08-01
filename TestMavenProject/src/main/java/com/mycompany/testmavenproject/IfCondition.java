package com.mycompany.testmavenproject;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.mycompany.models.Test1;

public class IfCondition {

	public static void main(String[] args) {
		try {
			String tagsInclude = "123";
			String obj = "{\"str1\":\"12\",\"str12\":\"12e3\"}";

			Test1 otherData = new Gson().fromJson(obj, Test1.class);

			if (StringUtils.isNotBlank(tagsInclude) && ((StringUtils.isBlank(otherData.getComparison())
					|| otherData.getComparison().toLowerCase().equals("xml")))) {
				System.out.println("Remove header and footer");
			} else {
				System.out.println("return full page");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
