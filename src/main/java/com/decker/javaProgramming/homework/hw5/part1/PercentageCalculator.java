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

import java.util.LinkedList;
import java.util.Scanner;

import static java.lang.System.out;

public class PercentageCalculator {

    private LinkedList<Integer> inputList;

    public PercentageCalculator() {
        this.inputList = new LinkedList<>();
    }

    public static void main(String[] args) {
        PercentageCalculator calculator = new PercentageCalculator();
        calculator.receiveUserInputs();
        calculator.calculate();
    }

    public LinkedList<Integer> getInputList() {
        return inputList;
    }

    public void setInputList(LinkedList<Integer> inputList) {
        this.inputList = inputList;
    }


    private Boolean isEmptyString(String content) {
        return (content == null || content.length() == 0);

    }

    public void receiveUserInputs() {
        try (Scanner sc = new Scanner(System.in)) {
            String content;
            while (!this.isEmptyString(content = sc.nextLine())) {
                try {
                    this.inputList.add(this.getInteger(content));
                } catch (BadValueException e) {
                    out.println("Non-negative integers only, try again:");
                }
            }
        }

    }

    private Integer getInteger(String content) throws BadValueException {

        try {
            Integer i = Integer.valueOf(content);
            if (i < 0) {
                throw new Exception();
            }
            return i;
        } catch (Exception ex) {
            throw new BadValueException(ex.getMessage());
        }
    }


    public void calculate() {
        if (this.inputList.size() == 0) {
            out.println("Empty input list");
            return;
        }
        Integer sum = 0;
        for (Integer i :
                this.inputList) {
            sum += i;
        }

        if (sum == 0) {
            out.println("Zero summary!");
            return;
        }

        out.println("The numbers and percentage:");
        for (Integer i :
                this.inputList) {
            out.printf("%4d  %6.1f%% %n", i, ((float) (i) * 100F / (float) sum));
        }
        out.printf("%4d  %6.1f%% %n", sum, ((float) (sum) * 100F / (float) sum));

    }
}
