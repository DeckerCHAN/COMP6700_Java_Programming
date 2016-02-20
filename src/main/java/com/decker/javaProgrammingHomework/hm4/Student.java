/*******************************************************************************
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 DeckerCHAN
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 ******************************************************************************/

package com.decker.javaProgrammingHomework.hm4;


public class Student {
    private static final String DEFAULT_NAME = "Unknown Name";
    private static final String DEFAULT_ADDRESS = "Unknown Address";
    private static final String DEFAULT_DEGREE_NAME = "Unknown Degree Name";
    private static final String DEFAULT_DEPARTMENT = "Unknown Department";
    private static final String DEFAULT_COMMENCED_YEAR = "Unknown Year";
    private String name;
    private String address;
    private String degreeName;
    private String department;
    private String commencedYear;

    public Student(String name, String address, String department) {
        this(name, address);
        this.department = department;
    }

    public Student(String name, String address) {
        this(name);
        this.address = address;
    }

    public Student(String name) {
        this();
        this.name = name;
    }

    public Student() {
        this.name = Student.DEFAULT_NAME;
        this.address = Student.DEFAULT_ADDRESS;
        this.department = Student.DEFAULT_DEPARTMENT;
        this.degreeName = Student.DEFAULT_DEGREE_NAME;
        this.commencedYear = Student.DEFAULT_COMMENCED_YEAR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCommencedYear() {
        return commencedYear;
    }

    public void setCommencedYear(String commencedYear) {
        this.commencedYear = commencedYear;
    }

    @Override
    public String toString() {
        return String.format("Student Information: \n  Name:%s \n  Department:%s \n  Year Commenced:%s", this.getName(), this.getDepartment(), this.getCommencedYear());
    }
}
