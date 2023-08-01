package com.mycompany.file;

import java.io.File;

import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.SlideShowFactory;

public class PPTFileValidation {
	public static void main(String args[]) {
		try {
//			FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Noman.Alahi\\Desktop\\test_2.pptx"));
//			XMLSlideShow ppt = new XMLSlideShow(inputStream);
//			System.out.println(ppt.getSlides().size());
			
			SlideShow ppt = SlideShowFactory.create(new File("C:\\Users\\Noman.Alahi\\Desktop\\test_2.pptx"));
			System.out.println(ppt.getSlides().size());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
