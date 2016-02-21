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

package com.decker.javaProgramming.homework.hm4;


import java.time.Year;

public class Student {

    private static final String DEFAULT_NAME = "Unknown Name";
    private static final String DEFAULT_ADDRESS = "Unknown Address";
    private static final String DEFAULT_DEGREE_NAME = "Unknown Degree Name";
    private static final String DEFAULT_DEPARTMENT = "Unknown Department";
    private static final Integer DEFAULT_COMMENCED_YEAR = Integer.valueOf(Year.now().toString());

    private static final Long STUDENT_ID_BASE = 1000L;

    private static Long NEXT_ID = -1L;

    private Long studentId;
    private String name;
    private String address;
    private String degreeName;
    private String department;
    private Integer commencedYear;

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
        this.name = DEFAULT_NAME;
        this.address = DEFAULT_ADDRESS;
        this.department = DEFAULT_DEPARTMENT;
        this.degreeName = DEFAULT_DEGREE_NAME;
        this.commencedYear = DEFAULT_COMMENCED_YEAR;
        this.studentId = this.generateId(getNextId());
    }

    private static Long getNextId() {
        if (NEXT_ID == -1L) {
            NEXT_ID = STUDENT_ID_BASE;
        }
        return NEXT_ID++;
    }

    private Long generateId(Long baseId) {
        Integer decadedYears = this.commencedYear % 100;
        return baseId + decadedYears * 10000;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public Integer getCommencedYear() {
        return commencedYear;
    }

    public void setCommencedYear(Integer commencedYear) {
        this.commencedYear = commencedYear;
    }

    @Override
    public String toString() {
        return String.format("Student Information: \n ID:%s \n  Name:%s \n  Department:%s \n  Year Commenced:%s", this.getStudentId(), this.getName(), this.getDepartment(), this.getCommencedYear());
    }
}
