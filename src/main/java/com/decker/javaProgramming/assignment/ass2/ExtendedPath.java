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
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineJoin;

import java.util.ArrayList;
import java.util.List;

public class ExtendedPath extends Path {

    private Boolean isSelected = false;
    private ArrayList<Point2D> points;
    private Point2D initialPoint;

    public ExtendedPath(List<? extends Point2D> points) {
        this(points.get(0));
        for (int index = 1; index < points.size() - 2; index++) {
            this.addLinePoint(points.get(index));
        }
        this.addEndPoint(points.get(points.size() - 1));

    }

    public ExtendedPath() {

        this.points = new ArrayList<>();
        this.setStrokeWidth(Variables.STROKE_WIDTH);

        this.setStroke(Variables.SHAPE_BORDER_COLOR);

        this.setStrokeLineJoin(StrokeLineJoin.ROUND);
    }


    public ExtendedPath(Point2D initialPoint) {
        this();
        this.addLinePoint(initialPoint);
    }

    public Morph getMorph() {
        return new Morph(this.getPoints());
    }

    public void addLinePoint(Point2D point) {
        this.points.add(point);
        if (this.initialPoint == null) {
            this.initialPoint = point;
            this.getElements().add(new MoveTo(point.getX(), point.getY()));
        } else {
            this.getElements().add(new LineTo(point.getX(), point.getY()));
        }
    }

    public void addEndPoint(Point2D endPoint) {
        this.addLinePoint(endPoint);
        this.getElements().add(new LineTo(this.initialPoint.getX(), this.initialPoint.getY()));

        this.setFill(Variables.SHAPE_FILL_COLOR);
    }

    public ArrayList<Point2D> getPoints() {
        return points;
    }

    private void setPoints(ArrayList<Point2D> points) {
        this.points = points;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    private void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public void select() {
        if (this.getSelected()) {
            this.setSelected(false);
            this.setStrokeWidth(Variables.STROKE_WIDTH);
            this.setStrokeWidth(Variables.STROKE_WIDTH);
        } else {
            this.setSelected(true);
            this.setStrokeWidth(Variables.SELECTED_STROKE_WIDTH);
        }
    }

}
