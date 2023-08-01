/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testmavenproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Noman.Alahi
 */
public class AWSDemo1 {

    public static void main(String[] args) {
//        int[] states = {1, 0, 0, 0, 0, 1, 0, 0};
        int[] states = {1, 1, 1, 0, 1, 1, 0, 0};
        int days = 2;
        int unoccupiedSpace = 0;
        int[] newStates = new int[8];
        for (int i = 0; i < days; i++) {
            if (i > 0) {
                states = newStates;
                for (int s = 0; s < states.length; s++) {
                    System.out.print(states[s]);
                    System.out.print(",");
                }
            }
            for (int j = 0; j < states.length; j++) {
                if (j == 0) {
                    if (unoccupiedSpace == states[j + 1]) {
                        newStates[j] = 0;
                    } else {
                        newStates[j] = 1;
                    }
                } else if (j == states.length - 1) {
                    if (unoccupiedSpace == states[j - 1]) {
                        newStates[j] = 0;
                    } else {
                        newStates[j] = 1;
                    }
                } else {
                    if (states[j - 1] == states[j + 1]) {
                        newStates[j] = 0;
                    } else {
                        newStates[j] = 1;
                    }
                }
            }
        }
        System.out.println();
        for (int i = 0; i < newStates.length; i++) {
            System.out.print(newStates[i]);
            System.out.print(",");
        }
    }

}
