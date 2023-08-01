package com.mycompany.file;

public class MergePartOfFiles {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		File outPutFile = new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part\\test.zip");
//		try (OutputStream out = Files.newOutputStream(Paths.get("C:\\Users\\NomanAlahi\\Desktop\\Test\\test.zip"),
//				StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
//			for (File file : new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part").listFiles()) {
//				System.out.println(file.getAbsolutePath());
//				Files.copy(Paths.get(file.getAbsolutePath()), out);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		File outPutFile = new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part\\test.zip");
//		try (OutputStream out = Files.newOutputStream(Paths.get("C:\\Users\\NomanAlahi\\Desktop\\Test\\test.zip"),
//				StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
//			for (File file : new File("C:\\Users\\NomanAlahi\\Desktop\\Test_multi_part").listFiles()) {
//				System.out.println(file.getAbsolutePath());
//				Files.copy(Paths.get(file.getAbsolutePath()), out);
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		try {
//			long leninfile = 0, leng = 0;
//			int count = 1, data = 0;
//			try {
//				File filename = new File(FilePath);
//				RandomAccessFile outfile = new RandomAccessFile(filename, "rw");
//				while (true) {
//					filename = new File(FilePath + count + ".sp");
//					if (filename.exists()) {
//						RandomAccessFile infile = new RandomAccessFile(filename, "r");
//						data = infile.read();
//						while (data != -1) {
//							outfile.write(data);
//							data = infile.read();
//						}
//						leng++;
//						infile.close();
//						count++;
//					} else
//						break;
//				}
//				outfile.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}
}
