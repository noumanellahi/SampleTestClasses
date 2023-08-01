///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.testmavenproject;
//
//import org.pdfclown.files.File;
//import org.pdfclown.files.SerializationModeEnum;
//import org.pdfclown.tools.Optimizer;
//import org.pdfclown.files.XRefModeEnum;
//
///**
// *
// * @author Noman.Alahi
// */
//public class PDFClownCompression {
//
//    public static void main(String[] args) {
//        try {
////            File file = new File("C:\\Users\\Noman.Alahi\\Desktop\\version_pdf_Bulletin_Officiel_n°_19_05-_15_Juin_2.pdf");
//            
//            File file = new File("C:\\Users\\Noman.Alahi\\Desktop\\Compress\\B2_Classification_commune_des_actes_médicaux_descriptive_à_.pdf");
////            Optimizer.removeOrphanedObjects(file); 
//            
//            file.getConfiguration().setXRefMode(XRefModeEnum.Compressed); // Full object compression.
//            file.save(SerializationModeEnum.Standard);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}
