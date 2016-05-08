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

package com.decker.javaProgramming.lab.lab3.probB;

import com.decker.javaProgramming.utils.DataParse;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataUtils {
    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.print("Input course grade or empty to return:");
            String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (StringUtils.isBlank(line) || !DataParse.tryParseInt(line)) {
                return;
            } else {
                System.out.printf("Year %s is %s leap year!%n", line, isLeapYear(Integer.valueOf(line)) ? "a" : "not");
            }
        }

    }

    public static boolean isLeapYear(int year) {
        return year >= 1582 && (((year % 4 == 0) && (year % 100 != 0)) || year % 400 == 0);
    }


}
