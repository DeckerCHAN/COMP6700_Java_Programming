/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Derek.CHAN
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

package com.decker.javaProgramming.homework.hw8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ImageDemo extends Application {
    public void start(Stage stage) {
        Image image = new Image("amazing-trees.jpg");
        // the stream processing, toImage is the terminal method
        // which breaks laziness


        Image finalImage0 = ColourFilter.transform(image, (x, y, colorAtXY) -> {
            if (x <= 10 || x >= image.getWidth() - 10 || y <= 10 || y >= image.getHeight() - 10) {
                return Color.GRAY;
            } else {
                return colorAtXY;
            }
        });

        Image finalImage = LatentImage
                .from(image)
                .blur()
                .toImage();

        stage.setScene(new Scene(new VBox(
                new ImageView(finalImage0),
                new ImageView(finalImage))));
        stage.show();
    }


}
