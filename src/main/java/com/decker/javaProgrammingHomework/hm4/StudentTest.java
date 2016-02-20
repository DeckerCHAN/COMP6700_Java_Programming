/*******************************************************************************
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
 *
 ******************************************************************************/

package com.decker.javaProgrammingHomework.hm4;

import java.util.ArrayList;

public class StudentTest {
    public static void executeTest()
    {
        ArrayList<Student> Students =new ArrayList<Student>();
        Students.add(0,new Student("Decker", "Canberra", "CSIT"));
        Students.add(1,new Student("Simon", "California", "CSIT"));
        Students.add(2,new Student("Mars", "Vladivostok", "CBE"));

        for (Student s:Students) {
            System.out.println(s.toString());
            System.out.println();
        }

    }
}
