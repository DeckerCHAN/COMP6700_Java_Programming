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

package com.decker.javaProgramming.assignment.ass2;

import javafx.geometry.Point2D;


public class Polygon extends ExtendedPath {

    private Point2D startPoint;
    private Double radius;// 外接圆的半径

    public Polygon(Point2D start, Double radius, Integer numberOfSides) {
        super(start);
        this.startPoint = start;
        this.radius = radius;
        this.posOfPoint(numberOfSides);
    }

    private void posOfPoint(int sideNumber) {

        for (int i = 1; i < sideNumber -1 ; i++) {
            Point2D p = nextPoint(((2 * Math.PI) / sideNumber) * i);
            this.addLinePoint(p);
        }
        this.addEndPoint(nextPoint(((2 * Math.PI) / sideNumber) * sideNumber -1));
    }

    private Point2D nextPoint(double arc) {// arc为弧度，在顶点处建立直角坐标系，用r和arc确定下一个点的坐标
        return new Point2D((startPoint.getX() - this.radius * Math.sin(arc)), (startPoint.getY() + this.radius - this.radius * Math.cos(arc)));
    }
}
