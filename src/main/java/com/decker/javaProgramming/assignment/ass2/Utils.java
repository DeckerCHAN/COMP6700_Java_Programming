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
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.concurrent.ThreadLocalRandom;

class Utils {

    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static void normalisePaths(Path p1, Path p2, Morph m1, Morph m2) {

        if (p1.getElements().size() > m2.points.size()) {
            p1.setOpacity(0.5);
            m1 = Morph.normalize(m1, m2.points.size());
            p1.setFill(null);
            adjustPath(p1, m1);
        } else if (p1.getElements().size() < m2.points.size()) {
            p2.setOpacity(0.5);
            m2 = Morph.normalize(m2, m1.points.size());
            p2.setFill(null);
            adjustPath(p2, m2);
            p2.setStrokeWidth(1);
            p2.setFill(Color.CORNSILK);
        }
        p1.setStrokeWidth(1);
        p1.setFill(Color.CORNSILK);
        p2.setOpacity(0.5);
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


}
