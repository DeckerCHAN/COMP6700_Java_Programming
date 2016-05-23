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

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

class Utils {

    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void normalisePaths(ExtendedPath p1, ExtendedPath p2, Morph m1, Morph m2) {

        if (p1.getElements().size() > m2.points.size()) {

            m2 = Morph.expand(m2, m1.points.size());
            p2.getPoints().clear();
            p2.getPoints().addAll(m2.points);
            adjustPath(p2, m2);
        } else if (p1.getElements().size() < m2.points.size()) {

            m1 = Morph.expand(m1, m2.points.size());
            p1.getPoints().clear();
            p1.getPoints().addAll(m1.points);
            adjustPath(p1, m1);

        }
    }

    public static Timeline makeTimeline(Path p1, Path p2) {
        assert p1.getElements().size() == p2.getElements().size() : "uneven paths";
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);//(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        int n = p1.getElements().size();
        KeyValue kvx, kvy;
        KeyFrame kf;
        MoveTo ap1, ap2;
        LineTo pe1, pe2;
        ap1 = (MoveTo) p1.getElements().get(0);
        ap2 = (MoveTo) p2.getElements().get(0);
        kvx = new KeyValue(ap1.xProperty(), ap2.getX());
        kvy = new KeyValue(ap1.yProperty(), ap2.getY());
        kf = new KeyFrame(Duration.millis(5000), kvx, kvy);
        timeline.getKeyFrames().add(kf);
        for (int i = 1; i < n; i++) {
            pe1 = (LineTo) p1.getElements().get(i);
            pe2 = (LineTo) p2.getElements().get(i);
            kvx = new KeyValue(pe1.xProperty(), pe2.getX());
            kvy = new KeyValue(pe1.yProperty(), pe2.getY());
            kf = new KeyFrame(Duration.millis(5000), kvx, kvy);
            timeline.getKeyFrames().add(kf);
        }
        return timeline;
    }

    public static void adjustPath(Path path, Morph morph) {
        System.out.printf("size of path %d, size of morph %d%n",
                path.getElements().size(), morph.points.size());
        path.getElements().clear();
        double x0 = morph.anchorPoint().getX();
        double y0 = morph.anchorPoint().getY();
        path.getElements().add(new MoveTo(x0, y0));
        Point2D p;
        for (int i = 1; i < morph.points.size(); i++) {
            p = morph.points.get(i);
            path.getElements().add(new LineTo(p.getX(), p.getY()));
        }
        path.getElements().add(new LineTo(x0, y0));
    }

    public static void popupMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void popupError(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static Double randomDouble(Double min, Double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }

    public static Integer randomInt(Integer min, Integer max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static Double calculatePerimeter(Point2D... points) {
        Double distance = 0D;
        for (int i = 1; i < points.length; i++) {
            distance += points[i].distance(points[i - 1]);
        }
        distance += points[points.length - 1].distance(points[0]);
        return distance;
    }

}
