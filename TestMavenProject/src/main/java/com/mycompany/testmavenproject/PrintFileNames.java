/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Noman.Alahi
 */
public class PrintFileNames {

    public static void main(String[] args) throws IOException {
        File[] files = new File("C:\\Users\\Noman.Alahi\\Desktop\\Done").listFiles();
        FileWriter fw = new FileWriter("C:\\Users\\Noman.Alahi\\Desktop\\a.txt");
        for (File file : files) {
            String html = "<p>%p: <a href=\"%s\">Download</a></p>\n";
            if (file.isFile()) {
                html = html.replace("%p", file.getName().toUpperCase()).replace("%s", file.getName());
                fw.write(html);
            }

        }
        fw.close();
    }

}
