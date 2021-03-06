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

package com.decker.javaProgramming.homework.hw2;

import java.math.BigInteger;

public class EulerBernoulliTriangle {
    private int height;
    private BigInteger[][] elems;

    public EulerBernoulliTriangle(int h) {
        this.height = h;
        initialize();
        //printTriangle();
        //System.out.println("The Euler numbers:\n" + getEulerNumbers());
        this.printEulerTriangle();
        //System.out.println("The Bernoulli numbers:\n" + getBernoulliNumbers());
    }

    public static void main(String[] args) {
        assert args.length > 0;
        int i = 0;
        try {
            i = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.toString() + " must be odd integer");
        } finally {
            if (i % 2 != 1 || i == 1) {
                System.out.println("Enter an odd number greater than 2 only");
                System.exit(1);
            }
            System.out.println("The height of the triangle is " + i);
        }

        EulerBernoulliTriangle triangle = new EulerBernoulliTriangle(i);
    }

    private void initialize() {
        elems = new BigInteger[height][];
        elems[0] = new BigInteger[1];
        elems[0][0] = BigInteger.valueOf(1);
        int lodd, leven;
        for (int i = 1; i < height; i += 2) {
            lodd = i + 1;
            leven = i + 2;
            elems[i] = new BigInteger[lodd];
            elems[i + 1] = new BigInteger[leven];
            elems[i][0] = BigInteger.valueOf(0);
            elems[i + 1][leven - 1] = BigInteger.valueOf(0);
            for (int j = 1; j < lodd; j++) {
                elems[i][j] = elems[i - 1][j - 1].add(elems[i][j - 1]);
            }
            for (int j = leven - 2; j >= 0; j--) {
                elems[i + 1][j] = elems[i + 1][j + 1].add(elems[i][j]);
            }
        }
    }

    public void printTriangle() {
        for (int i = 0; i < elems.length; i++) {
            for (int j = 0; j < elems[i].length; j++)
                System.out.print(elems[i][j] + " ");
            System.out.println();
        }
    }

    public BigInteger[] euler() {
        BigInteger[] left = new BigInteger[(height - 1) / 2];
        left[0] = BigInteger.valueOf(1);
        for (int i = 1; i < left.length; i++)
            left[i] = elems[2 * i][0];
        return left;
    }

    public String getEulerNumbers() {
        String s = "";
        for (BigInteger eul : euler())
            s += eul + "\n";
        return s + "That\'s " + euler().length + " Euler numbers\n";
    }

    public void printEulerTriangle() {
        System.out.println(" n                                                               Ex");
        System.out.println("-------------------------------------------------------------------");

        BigInteger[] euler = euler();
        for (int i = 1, eulerLength = euler.length; i < eulerLength; i++) {
            BigInteger eul = euler[i];
            System.out.printf("%2d%65s%n",i,eul.toString());

        }

    }

    public BigInteger[] bernoulli() {
        BigInteger[] right = new BigInteger[height / 2];
        right[0] = BigInteger.valueOf(1);
        for (int i = 1; i < right.length; i++)
            right[i] = elems[2 * i - 1][2 * i - 1];
        return right;
    }

    public String getBernoulliNumbers() {
        String s = "";
        for (BigInteger ber : bernoulli())
            s += ber + "\n";
        return s + "That\'s " + bernoulli().length + " Bernoulli numbers\n";
    }
}
