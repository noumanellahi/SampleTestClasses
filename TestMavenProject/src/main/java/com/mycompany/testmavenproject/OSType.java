/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

/**
 *
 * @author Noman.Alahi
 */
public class OSType {
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));
        System.getProperties().list(System.out);
    }
    
}
