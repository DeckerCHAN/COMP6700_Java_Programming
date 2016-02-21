/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 DeckerCHAN
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

package com.decker.javaProgrammingLab.lab5.probA;


import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.mutable.MutableInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class PercentageCalculator {

    private LinkedList<Integer> inputList;

    public PercentageCalculator() {
        this.inputList = new LinkedList<Integer>();
    }

    public LinkedList<Integer> getInputList() {
        return inputList;
    }

    public void setInputList(LinkedList<Integer> inputList) {
        this.inputList = inputList;
    }

    private InputType receiveInputInterger(MutableInt number) {
        try {
            String line = new BufferedReader(new InputStreamReader(System.in)).readLine();

            if (StringUtils.isBlank(line)) {
                return InputType.Determined;
            }
            Integer value = Integer.valueOf(line);
            if (value <= 0) {
                return InputType.Error;
            }
            number.setValue(value);
            return InputType.Normal;
        } catch (Exception ex) {
            return InputType.Error;
        }

    }

    public void receiveUserInput() {
        while (true) {
            MutableInt i = new MutableInt();
            InputType result = this.receiveInputInterger(i);
            switch (result) {
                case Normal:
                    this.inputList.add(i.intValue());
                    break;
                case Error:
                    System.out.println("Non-negative integers only, try again:");
                    break;
                case Determined:
                    return;
            }
        }
    }

    public void calculate() {
        Integer sum = 0;
        for (Integer i :
                this.inputList) {
            sum += i;
        }

        System.out.println("The numbers and percentage:");
        for (Integer i :
                this.inputList) {
            System.out.printf("%4d  %6.1f%% %n", i, ((float) (i) * 100F / (float) sum));
        }
        System.out.printf("%4d  %6.1f%% %n", sum, ((float) (sum) * 100F / (float) sum));

    }
}
