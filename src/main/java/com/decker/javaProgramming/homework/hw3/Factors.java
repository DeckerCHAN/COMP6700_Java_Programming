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
 */

package com.decker.javaProgramming.homework.hw3;

public class Factors {
    public static void main(String[] args) {
        Integer orig;
        try {
            orig = Integer.valueOf(args[0]);
        } catch (Exception ex) {
            System.out.println("Single number allowed!");
            return;
        }

        System.out.printf("%d = 1 ", orig);
        Boolean isPrimeNumber = true;
        for (Integer factor = 2; factor < orig / factor; factor++) {
            while (orig % factor == 0) {
                isPrimeNumber = false;
                System.out.printf("* %d ", factor);
                orig = orig / factor;
            }
        }
        if (orig > 1) {
            System.out.printf("* %d", orig);
        }
        if (isPrimeNumber) {
            System.out.printf("%n(%d is a prime number)", orig);
        }
    }
}
