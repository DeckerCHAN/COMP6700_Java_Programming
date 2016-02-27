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

public class RowsPrint {
    public static void main(String[] args) {
        Integer rowLength;
        try {
            rowLength = Integer.valueOf(args[0]) + 1;

        } catch (Exception ex) {
            System.out.println("Request a valid integer!");
            return;
        }
        printRows(rowLength);
        System.out.println();
        System.out.println();


    }

    public static void printRows(Integer n) {
        for (int i = 0; i < n - 1; i++) {
            printEvenRow(n);
            printOddRow(n);
        }
        printEvenRow(n);
    }

    public static void printEvenRow(Integer n) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            row.append("+--");
        }
        row.append("+");
        System.out.println(row);
    }

    public static void printOddRow(Integer n) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < n - 1; i++) {
            row.append("|  ");
        }
        row.append("|");
        System.out.println(row);

    }
}
