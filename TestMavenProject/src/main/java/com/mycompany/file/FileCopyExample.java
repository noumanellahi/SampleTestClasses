package com.mycompany.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopyExample {

	public static void main(String[] args) {
		String sourceFilePath = "/path/to/source/file.txt";
		String destinationFilePath = "/path/to/destination/file.txt";

		try {
			// Copy the file
			Path sourcePath = Paths.get(sourceFilePath);
			Path destinationPath = Paths.get(destinationFilePath);
			Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File copied successfully.");
		} catch (IOException e) {
			System.err.println("Error copying the file: " + e.getMessage());
		}
	}
}
