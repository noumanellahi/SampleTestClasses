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
public class Test {

    public static void main(String[] args) {
        String result = isPossible(12,6,18,42);
        System.out.println(result);
        
    }
    
    public static String isPossible (int a, int b, int c, int d) {
        if ( a < c ){
            a = a+b;
        } if ( b < d ) {
            b = a+b;
        } if (a<c || b<d) {
            return isPossible(a,b,c,d);
        } if (a ==c && b==d) {
            return "Yes";
        } if (a>c || b>d) {
            return "No";
        } else {
            return "Bad";
        }
    }
}