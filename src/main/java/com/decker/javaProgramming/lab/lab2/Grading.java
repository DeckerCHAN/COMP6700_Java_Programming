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

public class Grading {
    private Integer grade;

    public static void main(String[] args) {
        Grading grading = new Grading();
        grading.loop();
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public void loop() {
        while (!this.receive().equals(InputType.Determined)) {
            this.judge();
        }
    }

    public InputType receive() {

        try {
            System.out.print("Input course grade or empty to return:");
            String line = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if (StringUtils.isBlank(line)) {
                return InputType.Determined;
            }

            grade = Integer.valueOf(line);
            return InputType.Normal;
        } catch (Exception ex) {
            return InputType.Error;
        }

    }

    public void judge() {
        StringBuilder out = new StringBuilder("Grade level");
        if (this.getGrade() <= 50) {
            out.append("F");
        } else if (this.getGrade() <= 60) {
            out.append("E");
        } else if (this.getGrade() <= 70) {
            out.append("D");
        } else if (this.getGrade() <= 80) {
            out.append("C");
        } else if (this.getGrade() <= 90) {
            out.append("B");
        } else {
            out.append("A");
        }

        Integer single = this.getGrade() % 10;
        if (single >= 8 ||single.equals(0)) {
            out.append("+");
        } else if (single >= 4) {
            out.append(" ");
        } else {
            out.append("-");
        }
        System.out.println(out);
    }
}
