package com.mycompany.file;

import java.io.FileInputStream;
import java.io.InputStream;

import com.rtfparserkit.converter.text.StringTextConverter;
import com.rtfparserkit.parser.RtfStreamSource;

public class RtfFileCorrupt {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InputStream is = new FileInputStream("C:\\Users\\Noman.Alahi\\Desktop\\test_rtf.rtf");
			StringTextConverter converter = new StringTextConverter();
			converter.convert(new RtfStreamSource(is));
			String extractedText = converter.getText();
			System.out.println(extractedText);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
