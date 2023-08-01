/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.amazontest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Noman.Alahi
 */
public class task2 {

    public static void main(String[] args) {
        int rows = 4;
        int columns = 5;
        List<List<Integer>> grid = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        one.add(0);
        one.add(1);
        one.add(1);
        one.add(0);
        one.add(1);
        grid.add(0, one);

        List<Integer> two = new ArrayList<>();
        two.add(0);
        two.add(1);
        two.add(0);
        two.add(1);
        two.add(0);
        grid.add(1, two);

        List<Integer> three = new ArrayList<>();
        three.add(0);
        three.add(0);
        three.add(0);
        three.add(0);
        three.add(1);
        grid.add(2, three);

        List<Integer> four = new ArrayList<>();
        four.add(0);
        four.add(1);
        four.add(0);
        four.add(0);
        four.add(0);
        grid.add(3, four);

        System.out.println(grid.get(0));

        int hours = 0;

        for (int i = 0; i < rows; i++) {
            boolean check = true;
            for (int j = 0; j < columns; j++) {
                if (grid.get(i).get(j) == 0) {
                    check = false;
                    if (j + 1 < columns) {
                        if (grid.get(i).get(j + 1) == 1) {
                            grid.get(i).set(j, 1);
                        }
                    }
                    if (j - 1 >= 0) {
                        if (grid.get(i).get(j - 1) == 1) {
                            grid.get(i).set(j, 1);
                        }
                    }
                    if (i + 1 < rows) {
                        if (grid.get(i + 1).get(j) == 1) {
                            grid.get(i).set(j, 1);
                        }
                    }
                    if (i - 1 >= 0) {
                        if (grid.get(i - 1).get(j) == 1) {
                            grid.get(i).set(j, 1);
                        }
                    }
                }
            }
            if (check) {
                break;
            } else {
                hours++;
            }
        }
        System.out.println(hours);
        System.out.println(grid.get(0));
        System.out.println(grid.get(1));
        System.out.println(grid.get(2));
        System.out.println(grid.get(3));
    }
}
