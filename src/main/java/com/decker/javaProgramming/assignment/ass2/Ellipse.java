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

public class Ellipse extends ShapePath {

    public Ellipse(Double sceneWith, Double sceneHeight) {
        super();

        Double step = 2 * Math.PI / 160;  // see note 1
        Double centerX = sceneWith / 2;
        Double centerY = sceneHeight / 2;
        Double r = 250D;

        ArrayList<Point2D> ellipsePoints = new ArrayList<>();

        for (Double theta = 0D; theta < 2 * Math.PI; theta += step) {
            Double x = centerX + r * Math.cos(theta);
            Double y = centerY - 0.5 * r * Math.sin(theta);
            ellipsePoints.add(new Point2D(x, y));
        }

        for (int i = 0; i < ellipsePoints.size() - 1; i++) {
            this.addLinePoint(ellipsePoints.get(i));
        }

        this.addEndPoint(ellipsePoints.get(ellipsePoints.size() - 1));
    }
}
