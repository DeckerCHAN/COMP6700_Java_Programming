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
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class NewShapeChanger extends Application {


    private Stage primaryStage;
    private Scene mainScene;
    private Group rootGroup;
    private MenuBar menuBar;
    private State state = State.DRAWING;
    private ExtendedPath currentPath;
    private ExtendedPath selectedPath;
    private ExtendedPath targetPath;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;


        this.rootGroup = new Group();
        this.mainScene = new Scene(this.rootGroup, Screen.getPrimary().getBounds().getWidth() * Variables.USE_OF_SCREEN_FACTOR,
                Screen.getPrimary().getBounds().getHeight() * Variables.USE_OF_SCREEN_FACTOR, Color.WHEAT);


        this.menuBar = new MenuBar();
        this.menuBar.setPrefHeight(20);
        this.menuBar.setPadding(new Insets(15, 12, 15, 12));


        Menu menuFile = new Menu("File");
        MenuItem open = new MenuItem("Open");
        open.onActionProperty().set(e -> loadFromFile());
        MenuItem save = new MenuItem("Save");
        save.onActionProperty().set(e -> this.saveToFile());
        MenuItem quit = new MenuItem("Quit");
        quit.setOnAction(event -> Platform.exit());

        menuFile.getItems().addAll(open, save, quit);

        Menu menuEdit = new Menu("Edit");
        MenuItem clean = new MenuItem("Clean all shapes");
        clean.onActionProperty().set(event -> this.clean());
        MenuItem select = new MenuItem("Select");
        select.onActionProperty().set(event -> {
            if (this.state == State.DRAWING) {
                this.state = State.SELECTING;
                ((MenuItem) event.getSource()).setText("Exit Select");
            } else {
                this.state = State.DRAWING;
                this.selectedPath.select();
                this.selectedPath = null;
                ((MenuItem) event.getSource()).setText("Select");
            }
        });
        menuEdit.getItems().addAll(clean, select);

        Menu menuMorph = new Menu("Morph");
        MenuItem triangle = new MenuItem("Triangle");
        triangle.onActionProperty().set(this::getTriangleMorphEventHandler);
        MenuItem ellipse = new MenuItem("Ellipse");
        MenuItem rectangle = new MenuItem("Rectangle");
        rectangle.onActionProperty().set(e -> {
            this.rootGroup.getChildren().remove(this.targetPath);
            this.rootGroup.getChildren().add(new Polygon(new Point2D(this.mainScene.getWidth() / 2, this.mainScene.getHeight() / 2), 50D, 12));

        });
        MenuItem polygon = new MenuItem("Polygon");
        polygon.onActionProperty().set(e -> {
            this.rootGroup.getChildren().remove(this.targetPath);
            this.rootGroup.getChildren().add(new Polygon(new Point2D(this.mainScene.getWidth() / 2, this.mainScene.getHeight() / 2), 50D, 12));

        });
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

    private void getTriangleMorphEventHandler(ActionEvent actionEvent) {

    }

    private void getMousePressedEventHandler(MouseEvent event) {

        if (this.state == State.DRAWING) {
            this.currentPath = new ExtendedPath(new Point2D(event.getX(), event.getY()));
            ExtendedPath currentPath = this.currentPath;

            currentPath.setOnMouseEntered(e -> {
                if (this.state == State.SELECTING) {
                    ((Path) e.getSource()).setFill(Variables.SELECTING_SHAPE_FILL_COLOR);
                }
            });
            currentPath.setOnMouseExited(e -> {
                if (this.state == State.SELECTING) {
                    ((Path) e.getSource()).setFill(Variables.SHAPE_FILL_COLOR);
                }
            });
            currentPath.setOnMouseClicked(e -> {
                if (this.state == State.SELECTING) {
                    ExtendedPath path = ((ExtendedPath) e.getSource());
                    if (this.selectedPath != null) {
                        this.selectedPath.select();
                    }
                    this.selectedPath = path;
                    this.selectedPath.select();
                }
            });
            this.rootGroup.getChildren().add(currentPath);
        }
    }


    private void getMouseDragEventHandler(MouseEvent event) {
        if (this.state == State.DRAWING) {
            this.currentPath.addLinePoint(new Point2D(event.getX(), event.getY()));
        }
    }

    private void getMouseReleasedEventHandler(MouseEvent event) {
        if (this.state == State.DRAWING) {
            this.currentPath.addEndPoint(new Point2D(event.getX(), event.getY()));
        }
    }

    private void clean() {
        this.currentPath = null;
        this.selectedPath = null;
        this.rootGroup.getChildren().clear();
        this.rootGroup.getChildren().add(this.menuBar);
        out.println("Group cleared");
    }

    private void saveToFile() {
        if (this.rootGroup.getChildren().size() == 1) {

            Utils.popupError("Not allowed", "There is no shape to save.", "Draw something before you can save.");
            return;
        }

        ArrayList<ExtendedPath> paths = this.rootGroup.getChildren().stream().filter(node -> node instanceof ExtendedPath).map(node -> (ExtendedPath) node).collect(Collectors.toCollection(ArrayList::new));

        try {
            StringBuilder savingContent = new StringBuilder();
            for (ExtendedPath path : paths) {
                for (Point2D point : path.getPoints()) {
                    savingContent.append(String.format("%f,%f|", point.getX(), point.getY()));
                }
                savingContent.append(System.getProperty("line.separator"));
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save shape");
            fileChooser.setInitialFileName("Objects.seq");
            File file = fileChooser.showSaveDialog(this.primaryStage);
            if (file == null) {
                return;
            }

            try (FileWriter fileWriter = new FileWriter(file, false)) {
                fileWriter.write(savingContent.toString());
            }
        } catch (Exception e) {
            Utils.popupError("Error", "Can not save object to file.", e.getMessage());
        }


    }

    private void loadFromFile() {
        this.clean();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open shape");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sequence File", "*.seq"));
        File file = fileChooser.showOpenDialog(this.primaryStage);
        if (file == null) {
            return;
        }
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] pointStrings = line.split("\\|");
                    ArrayList<Point2D> points = new ArrayList<>();
                    for (String pointString : pointStrings) {
                        points.add(new Point2D(Double.valueOf(pointString.split(",")[0]), Double.valueOf(pointString.split(",")[1])));
                    }
                    ExtendedPath path = new ExtendedPath(points);
                    this.rootGroup.getChildren().add(path);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.popupError("Error", "Can not read object from file.", e.getMessage());

        }


    }
}
