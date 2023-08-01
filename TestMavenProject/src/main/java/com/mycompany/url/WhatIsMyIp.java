package com.mycompany.url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class WhatIsMyIp {
	public static void main(String args[]) {

		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");

			inputStream = whatismyip.openStream();
			inputStreamReader = new InputStreamReader(inputStream);
			bufferedReader = new BufferedReader(inputStreamReader);

			String ip = bufferedReader.readLine(); // you get the IP as a String
			System.out.println(ip);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (inputStreamReader != null) {
					inputStreamReader.close();
				}
				if (bufferedReader != null) {
					bufferedReader.close();
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}
}
