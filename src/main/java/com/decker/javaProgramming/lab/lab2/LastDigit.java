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

package com.decker.javaProgramming.lab.lab2;

import com.decker.javaProgramming.utils.InputType;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LastDigit {
    public static void main(String[] args) throws Exception {

        LastDigit lastDigit=new LastDigit();
        lastDigit.loop();
        System.out.println("Exit!");
    }

    public void loop()
    {

        while (!this.receive().equals(InputType.Determined))
        {
            Integer sevenTimes= this.numberReceived*7;
            Integer lastdigit = sevenTimes%10;
            System.out.printf("The number you inputted is %d, then multiply 7 to %d. The last digit is %d %n",this.numberReceived,sevenTimes,lastdigit);
        }
    }

    private Integer numberReceived;

    public InputType receive() {

        try {
            System.out.print("Input a number or empty to return:");
            String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (StringUtils.isBlank(line)) {
                return InputType.Determined;
            }

            numberReceived= Integer.valueOf(line);
            return InputType.Normal;
        } catch (Exception ex) {
            return InputType.Error;
        }

    }
}
