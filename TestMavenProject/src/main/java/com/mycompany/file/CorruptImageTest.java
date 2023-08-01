package com.mycompany.file;

import java.io.File;

import javax.imageio.ImageIO;

public class CorruptImageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ImageIO.read(new File("C:\\Users\\Noman.Alahi\\Desktop\\598441_418514128217104_608409655_n.jpg")).flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
