package com.mycompany.hash;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;

public class HashCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
//			String checksumSHA1 = DigestUtils.sha1Hex(new FileInputStream(
//					"C:\\Users\\NomanAlahi\\Desktop\\HasH_Test\\Journal_Articles_JA_D0494J_AAA34_J_Issue-Web_20221206__0.pdf"));
//			System.out.println(checksumSHA1);

//			System.out.println(new Date());
			String hash1 = DigestUtils.sha1Hex(new FileInputStream(
					"C:\\Users\\NomanAlahi\\Desktop\\Hash Issues\\88822\\new\\legal_code_descriptions_202335.xlsx"));

			String hash2 = DigestUtils.sha1Hex(new FileInputStream(
					"C:\\Users\\NomanAlahi\\Desktop\\Hash Issues\\88822\\old_1\\legal_code_descriptions_202335.xlsx"));

			System.out.println(hash1);
			System.out.println(hash2);
//			System.out.println(new Date());

//			String checksumSHA3 = DigestUtils.sha1Hex(new FileInputStream(
//					"C:\\Users\\NomanAlahi\\Desktop\\HasH_Test\\Journal_Articles_JA_D0494J_AAA34_J_Issue-Web_20221207__0.pdf"));
//			System.out.println(checksumSHA3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
