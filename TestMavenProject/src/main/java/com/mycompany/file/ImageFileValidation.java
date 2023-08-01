package com.mycompany.file;

import java.io.File;

import javax.imageio.ImageIO;

public class ImageFileValidation {

	public static void main(String[] args) {
		try {

			ImageIO.read(new File("C:\\Users\\NomanAlahi\\Desktop\\b93be9f5-59e9-4485-92b1-2277fe9e87d5.jpg")).flush();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
