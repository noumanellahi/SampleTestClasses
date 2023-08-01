/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject.pdfBox;

import java.io.File;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

/**
 *
 * @author Noman.Alahi
 */
public class MergePdfDocuments {

    public static void main(String[] args) {
        try{
        PDFMergerUtility ut = new PDFMergerUtility();
        
        String directory = "C:\\Users\\Noman.Alahi\\Desktop\\VU\\MGT-301\\PPTs MGT301 Theme 3\\";

        for (int i=1; i<=9; i++) {
            if (new File(directory + i + " Organization and Marketing Strategy.pdf").isFile()) {
                ut.addSource(directory + i + " Organization and Marketing Strategy.pdf");
            }
        }

        ut.setDestinationFileName(directory + "PPTs MGT301 Theme 3" + ".pdf");
        ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
