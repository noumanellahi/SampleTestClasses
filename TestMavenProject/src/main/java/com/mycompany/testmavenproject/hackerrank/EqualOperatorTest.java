/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.testmavenproject.hackerrank;

/**
 *
 * @author Noman.Alahi
 */
public class EqualOperatorTest {
    public static void main(String[] args) {
        String a = new String("Hello");
        String b = new String("Hello");
        
        String c = "Hello";
        String d = "Hello";
        
//        c.concat(" World");
        
//        System.out.println(c);
        
        System.out.println(c == d);
        System.out.println(c.equals(d));
        
        
    }
}
