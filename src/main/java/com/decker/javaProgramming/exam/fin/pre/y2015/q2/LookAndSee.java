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

package com.decker.javaProgramming.exam.fin.pre.y2015.q2;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.System.out;

public class LookAndSee {
    public static void main(String[] args) {
        if (args.length != 2) {
            out.println("This programme request 2 arguments!");
            System.exit(0);
        }
        String lookingNumber = args[0];
        Integer loop = null;
        try {
            loop = Integer.valueOf(args[1]);
        } catch (NumberFormatException ex) {
            out.println("The second argument should be digital-string!");
            System.exit(0);
        }
        if (loop <= 0) {
            out.println("The second argument should be positive number!");
            System.exit(0);
        }

        String lastOneLkkingNumber = null;

        for (; loop > 0; loop--) {
            out.println(lookingNumber);
            try {
                if (loop == 1) {
                    lastOneLkkingNumber = lookingNumber;
                }
                lookingNumber = last(lookingNumber);
            } catch (NotLookAndSeeException e) {
                out.println(e.getMessage());
                System.exit(0);
            }

        }

        out.printf("The look and say sequence seeded by %s has the growth rate %s%n",args[0], new BigDecimal(lookingNumber).divide(new BigDecimal(lastOneLkkingNumber),15, RoundingMode.CEILING).toString());


    }

    public static String last(String s) throws NotLookAndSeeException {
        try {
            new BigDecimal(s);
        } catch (Exception ex) {
            throw new NotLookAndSeeException("Error, the looking number should be digital-string");
        }

        StringBuilder result = new StringBuilder();

        Character currentCharacter = null;
        Integer currentCount = 0;
        char[] seq = s.toCharArray();
        Integer index = 0;
        for (; index < s.length() && currentCharacter == null; index++) {
            if (seq[index] != '0') {
                currentCharacter = seq[index];
                currentCount = 1;
            }
        }

        for (; index < s.length(); index++) {
            if (seq[index] == '0') {
                throw new NotLookAndSeeException("Error, extra '0' in string!");
            }
            if (seq[index] == currentCharacter) {
                currentCount++;
            } else {
                result.append(String.format("%d%s", currentCount, currentCharacter));

                currentCharacter = seq[index];
                currentCount = 1;
            }
        }
        result.append(String.format("%d%s", currentCount, currentCharacter));
        return result.toString();

    }
}
