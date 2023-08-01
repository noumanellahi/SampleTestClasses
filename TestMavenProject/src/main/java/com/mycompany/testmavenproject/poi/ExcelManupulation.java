/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Noman.Alahi
 */
public class ExcelManupulation {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        InputStream fis = new FileInputStream(new File("C:\\Users\\Noman.Alahi\\Desktop\\Template_for_RMS_change.xlsx"));
        Workbook wb = WorkbookFactory.create(fis);

        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            PrintSetup ps = sheet.getPrintSetup();
            sheet.setFitToPage(true);
            sheet.setAutobreaks(true);
            ps.setFitWidth((short) 1);
            ps.setFitHeight((short) 1);
            ps.setLandscape(true);
        }

        OutputStream fileOut = new FileOutputStream("C:\\Users\\Noman.Alahi\\Desktop\\Template_for_RMS_change.xlsx");

        wb.write(fileOut);
        wb.close();
        fis.close();
        fileOut.close();

    }
}
