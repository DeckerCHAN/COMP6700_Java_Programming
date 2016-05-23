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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.*;

/**
 * The model part of the ShapeChanger.
 * The morphing algorithm @link Morph.normalise
 * is not perfected -- for complex enough shapes
 * the results is a strongly diminished shape compared with
 * the expected one (TODO: 25/04/2016 needs fixing later)
 *
 * <p>
 * Created with IntelliJ IDEA.
 * User: abx
 * Date: 21/04/2016
 * Time: 5:46 PM
 * Created for ass2 in package shapechanger
 * @version 1.0
 * @author abx
 * @author (your name and id)
 *
 * @see ShapeChanger
 * </p>
 */

public class Morph {

    public List<Point> points;

    public Morph(List<Point> points) {

        this.points = new ArrayList<>();
        this.points.addAll(points
                .stream()
                .map(p -> Point.makePoint(p.x, p.y))
                .collect(Collectors.toList())
        );
    }

    public Point anchorPoint() {
        return points.get(0);
    }

    public Morph roundMorph() {
        if (points.size() < 3)
            return this;

        List<Point> newPoints = new ArrayList<>();
        double mx = medX();
        double my = medY();
        double firstX = points.get(0).x - mx;
        double firstY = points.get(0).y - my;
        double theta0 = /*0.5*PI*/ -atan2(firstY, firstX);
        double secondX = points.get(1).x - mx;
        double secondY = points.get(2).y - my;
        int orient = (int) signum(firstX * secondY - firstY * secondX);
        int n = points.size();
        double r = radius();
        double theta;

        for (int i = 0; i < n; i++) {
            theta = orient * 2 * PI * i / n - theta0;
            newPoints.add(Point.makePoint(mx + r * cos(theta),
                    my + r * sin(theta)));
        }
        //System.out.printf("Morph radius %.2f%n", r);
        return new Morph(newPoints);
    }

    public static Morph normalize(Morph source, int pointLimit) {
        if (source.points.size() < pointLimit)
            throw new AssertionError("Source oneMorph has less points than pointLimit");

        int sourcePoints = source.points.size();
        int excess = sourcePoints - pointLimit;
        int gap = sourcePoints / (excess + 1);
        List<Point> filteredPoints =
                IntStream.range(0, source.points.size())
                        .filter(i -> (i+1) % gap != 0 || i >= gap * excess)
                        .mapToObj(source.points::get)
                        .collect(Collectors.toList());

//        System.out.printf("source %d, limit %d and normalised morph %d%n",
//                source.points.size(), pointLimit, filteredPoints.size());

        return new Morph(filteredPoints);
    }


    private double medX() {
        double x = 0;
        for (Point p : points) {
            x += p.x;
        }
        return x / points.size();
    }

    private double medY() {
        double y = 0;
        for (Point p : points) {
            y += p.y;
        }
        return y / points.size();
    }

    private double area() {
        double res = 0;
        if (points.size() < 3)
            return res;
        double cx = medX();
        double cy = medY();
        double dx1, dx2, dy1, dy2;
        for (int i = 0; i < points.size() - 1; i++) {
            dx1 = points.get(i).x - cx;
            dy1 = points.get(i).y - cy;
            dx2 = points.get(i + 1).x - cx;
            dy2 = points.get(i + 1).y - cy;
            res += 0.5 * abs(dx1 * dy2 - dx2 * dy1);
        }
        dx1 = points.get(points.size() - 1).x - cx;
        dy1 = points.get(points.size() - 1).y - cy;
        dx2 = points.get(0).x - cx;
        dy2 = points.get(0).y - cy;
        res += 0.5 * abs(dx1 * dy2 - dx2 * dy1);

        return res;
    }

    private double radius() {
        return sqrt(area() / PI);
    }
}