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

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import static java.lang.System.out;

public class NewShapeChanger extends Application {

    private static final double useOfScreenFactor = 0.8;

    private Stage primaryStage;
    private Scene mainScene;
    private Group rootGroup;
    private MenuBar menuBar;
    private State state = State.CLEAR;
    private LinedShape shapeA;
    private LinedShape shapeB;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;


        this.rootGroup = new Group();
        this.mainScene = new Scene(this.rootGroup, Screen.getPrimary().getBounds().getWidth() * useOfScreenFactor,
                Screen.getPrimary().getBounds().getHeight() * useOfScreenFactor, Color.WHEAT);


        this.menuBar = new MenuBar();
        this.menuBar.setPrefHeight(20);
        this.menuBar.setPadding(new Insets(15, 12, 15, 12));


        Menu menuFile = new Menu("File");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(event -> Platform.exit());

        menuFile.getItems().addAll(open, save, quit);

        Menu menuEdit = new Menu("Edit");
        MenuItem select = new MenuItem("Select");
        menuEdit.getItems().add(select);

        Menu menuMorph = new Menu("Morph");
        MenuItem triangle = new MenuItem("Triangle");

        MenuItem ellipse = new MenuItem("Ellipse");
        MenuItem rectangle = new MenuItem("Rectangle");
        MenuItem polygon = new MenuItem("Polygon");
        menuMorph.getItems().addAll(triangle, ellipse, rectangle, polygon);

        this.menuBar.getMenus().addAll(menuFile, menuEdit, menuMorph);

        this.rootGroup.getChildren().add(this.menuBar);

        //Binding events
        this.mainScene.onMousePressedProperty().set(this::getMousePressedEventHandler);
        this.mainScene.onMouseDraggedProperty().set(this::getMouseDragEventHandler);
        this.mainScene.onMouseReleasedProperty().set(this::getMouseReleasedEventHandler);

        this.primaryStage.setScene(this.mainScene);
        this.primaryStage.show();
        this.primaryStage.setOnCloseRequest(e -> Platform.exit());

    }

    private void getMousePressedEventHandler(MouseEvent event) {
        switch (this.state) {

            case CLEAR:
                this.shapeA = new CustomerShapeA(new Point2D(event.getX(), event.getY()));
                this.rootGroup.getChildren().add(this.shapeA.getPath());
                break;
            case ONE:
                this.shapeB = new CustomerShapeB(new Point2D(event.getX(), event.getY()));
                this.rootGroup.getChildren().add(this.shapeB.getPath());
                break;
            case BOTH:
                this.cleanGroup();
                this.shapeA = new CustomerShapeA(new Point2D(event.getX(), event.getY()));
                this.rootGroup.getChildren().add(this.shapeA.getPath());
                break;
        }
    }

    private void getMouseDragEventHandler(MouseEvent event) {
        switch (this.state) {

            case CLEAR:
                this.shapeA.addLinePoint(new Point2D(event.getX(), event.getY()));
                break;
            case ONE:
                this.shapeB.addLinePoint(new Point2D(event.getX(), event.getY()));
                break;
            case BOTH:
                this.cleanGroup();
                break;
        }
    }

    private void getMouseReleasedEventHandler(MouseEvent event) {
        switch (this.state) {

            case CLEAR:
                this.shapeA.addEndPoint(new Point2D(event.getX(), event.getY()));
                state = State.ONE;
                break;
            case ONE:
                this.shapeB.addEndPoint(new Point2D(event.getX(), event.getY()));
                state = State.BOTH;
                break;
            case BOTH:
                this.cleanGroup();
                break;
        }
    }

    private void cleanGroup() {
        this.rootGroup.getChildren().clear();
        this.rootGroup.getChildren().add(this.menuBar);
        this.state = State.CLEAR;
        out.println("Group cleared");
    }
}
