/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.util.HashMap;

/**
 *
 * @author Noman.Alahi
 */
public class HashMapKeyTest {
     public static void main(String[] args) {
         HashMap<String,String> map = new HashMap<>();
         map.put("A", "Noman");
         map.put("a", "taha");
         
         System.out.println(map.get("b"));
    }
}
