/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Derek.CHAN
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.decker.javaProgramming.lab.lab2.shapePrint;

public class TrianglePrint {
    public static void main(String[] args) {
        Integer height;
        try {
            height = Integer.valueOf(args[0]) + 1;

        } catch (Exception ex) {
            System.out.println("Request a valid integer!");
            return;
        }

        printPick(height);
        printBody(height);
        printBottom(height);
    }

    public static void printPick(Integer height) {
        for (int index = 0; index < height ; index++) {
            System.out.print(' ');
        }
        System.out.println('+');
    }

    public static void printBody(Integer height) {
        height--;
        for (Integer currentHeight = height; currentHeight > 0; currentHeight--) {
            for (Integer clum = 0; clum < height + 1; clum++) {
                if (clum.equals(currentHeight)) {
                    System.out.print('/');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println('|');
        }
    }

    public static void printBottom(Integer height) {
        System.out.print('+');
        for (int index = 0; index < height-1; index++) {
            System.out.print('-');
        }
        System.out.print('+');
    }

}
