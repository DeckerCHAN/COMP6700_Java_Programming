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

import java.util.ArrayList;


public class Polygon extends ShapePath {

    private Point2D centerPoint;
    private Double radius;
    private Integer numberOfSides;
    private ArrayList<Point2D> polPoints;

    public Polygon(Point2D centerPoint, Double radius, Integer numberOfSides) {
        super();
        this.polPoints = new ArrayList<>();
        this.centerPoint = centerPoint;
        this.radius = radius;
        this.numberOfSides = numberOfSides;
        this.pushPolygonPoints();

        for (int i = 0; i < this.polPoints.size() - 1; i++) {
            this.addLinePoint(this.polPoints.get(i));
        }
        this.addEndPoint(this.polPoints.get(this.polPoints.size() - 1));
    }

    private void pushPolygonPoints() {
        Double ca = 0D;
        Double aiv = 360 / (double) this.numberOfSides;
        Double ata = Math.PI / 180;

        for (Double k = 0D; k < this.numberOfSides; k++) {
            Double x = Math.cos(ca * ata) * this.radius;
            Double y = Math.sin(ca * ata) * this.radius;

            this.polPoints.add(new Point2D(x + this.centerPoint.getX(), y + centerPoint.getY()));

            ca += aiv;
        }
    }
}
