package com.mycompany.extension;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class GetFileNameFromStringPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String filePath = "C:\\Users\\NomanAlahi\\Desktop\\grant_yb2_20220215.tar";
		String fileName = "/efs_api/108869/2023-09-12/052654/1083.xml";

		System.out.println(FilenameUtils.getBaseName(fileName));
		System.out.println(FilenameUtils.getExtension(fileName));
		System.out.println(FilenameUtils.getFullPath(fileName));

		String[] parts = FilenameUtils.getFullPath(fileName).split("/");
		String tmpPath = Arrays.stream(parts).filter(part -> !part.isEmpty() && !part.contains("efs"))
				.collect(Collectors.joining(File.separator));

		tmpPath = File.separator + tmpPath + File.separator;

		String root = Paths.get(FilenameUtils.getFullPath(tmpPath)).getName(0).toString();
		System.out.println(root);

//		File latestDownloadedFile = new File(filePath);

//		System.out.println(FilenameUtils.getBaseName(latestDownloadedFile.getPath()).replace(".tar", ""));
//		System.out.println(FilenameUtils.getExtension(filePath));
//		System.out.println(FilenameUtils.getFullPath(filePath));
//		
//		System.out.println(latestDownloadedFile.getAbsolutePath());
//		System.out.println(latestDownloadedFile.getPath());

	}

}
