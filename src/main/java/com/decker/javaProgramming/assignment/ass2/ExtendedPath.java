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
import javafx.scene.shape.*;

import java.util.ArrayList;

public class ExtendedPath extends Path {

    private Boolean isSelected = false;
    private ArrayList<Point2D> points;
    private Point2D initialPoint;

    public ExtendedPath(Point2D initialPoint) {
        this.initialPoint = initialPoint;
        this.points = new ArrayList<>();
        this.points.add(initialPoint);
        this.setStrokeWidth(Variables.STROKE_WIDTH);
        this.setStrokeDashOffset(Variables.STROKE_DASH_OFFSET);
        this.setStroke(Variables.SHAPE_BORDER_COLOR);
        this.setStrokeLineJoin(StrokeLineJoin.ROUND);
        this.getElements().add(new MoveTo(initialPoint.getX(), initialPoint.getY()));
    }

    public Morph getMorph() {
        return new Morph(this.getPoints());
    }

    public void addLinePoint(Point2D p) {
        this.points.add(p);
        this.getElements().add(new LineTo(p.getX(), p.getY()));
    }

    public void addEndPoint(Point2D endPoint) {
        this.addLinePoint(endPoint);
        this.getElements().add(new LineTo(this.initialPoint.getX(), this.initialPoint.getY()));
        this.setStrokeWidth(Variables.STROKE_WIDTH);
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
