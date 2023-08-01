package com.mycompany.files;

import java.io.File;
import java.util.Arrays;
import java.util.Optional;

public class CopyFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File localPath = new File("C:\\webacq\\28301\\2021-03-10\\test.pdf");
		File chromeDownloadDir = new File("C:\\webacq\\28301\\2021-03-10\\tmp");
		System.out.println(chromeDownloadDir.listFiles().length);
		File latestDownloadedFile;
//		if (chromeDownloadDir.listFiles().length == 1) {
//			latestDownloadedFile = chromeDownloadDir.listFiles()[0];
//		} else {
			latestDownloadedFile = getLatestFile(chromeDownloadDir);
//		}
			System.out.println();
		latestDownloadedFile.renameTo(localPath);
	}

	public static File getLatestFile(File dir) {
		if (dir.isDirectory()) {
			Optional<File> opFile = Arrays.stream(dir.listFiles(File::isFile))
					.max((f1, f2) -> Long.compare(f1.lastModified(), f2.lastModified()));

			if (opFile.isPresent()) {
				return opFile.get();
			}
		}

		return null;
	}
}
