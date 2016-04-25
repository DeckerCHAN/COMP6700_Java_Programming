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

package com.decker.javaProgramming.homework.hw5.part1;


import com.decker.javaProgramming.utils.InputType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

import static java.lang.System.out;

public class PercentageCalculator {

    private LinkedList<Integer> inputList;

    public PercentageCalculator() {
        this.inputList = new LinkedList<Integer>();
    }

    public static void main(String[] args) {
        PercentageCalculator calculator = new PercentageCalculator();
        calculator.receiveUserInput();
        calculator.calculate();
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
            try {
                switch (result) {
                    case Normal:
                        this.inputList.add(i.intValue());
                        break;
                    case Error:
                        throw new BadValueException("Non-negative integers only, try again:");
                    case Determined:
                        return;
                }
            } catch (BadValueException ex) {
                out.println(ex);
            }

        }
    }

    public void calculate() {
        Integer sum = 0;
        for (Integer i :
                this.inputList) {
            sum += i;
        }

        out.println("The numbers and percentage:");
        for (Integer i :
                this.inputList) {
            out.printf("%4d  %6.1f%% %n", i, ((float) (i) * 100F / (float) sum));
        }
        out.printf("%4d  %6.1f%% %n", sum, ((float) (sum) * 100F / (float) sum));

    }
}
