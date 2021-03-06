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

package com.decker.javaProgramming.homework.hw1;

public class BalanceCalculator {
    public static void main(String[] args) {
        Double source;
        Double target;
        try {
            source = Double.valueOf(args[0]);
            target = Double.valueOf(args[1]);
        } catch (Exception ex) {
            System.out.println("Correct format should be: amount(source) amount(target)");
            return;
        }

        Double current = source;
        Integer years = 0;
        System.out.printf("+----+--------------------+%n");
        System.out.printf("|year|      balance       +%n");
        System.out.printf("+----+--------------------+%n");
        while (true) {
            if (current >= target) {
                System.out.printf("You need %d year(s) to accumulate amount from %.2f to %.2f %n", years, source, target);
                return;
            }
            current *= 1.05;
            years++;
            System.out.printf("|%4d|%20.2f|%n", years, current);
            System.out.printf("+----+--------------------+%n");
        }
    }
}
