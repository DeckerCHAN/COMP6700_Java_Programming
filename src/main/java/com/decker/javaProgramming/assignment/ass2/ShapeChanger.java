///*
// * The MIT License (MIT)
// *
// * Copyright (c) 2016 Derek.CHAN
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package com.decker.javaProgramming.assignment.ass2;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.KeyValue;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Point2D;
//import javafx.geometry.Rectangle2D;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.LineTo;
//import javafx.scene.shape.MoveTo;
//import javafx.scene.shape.Path;
//import javafx.stage.Screen;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Consumer;
//import java.util.function.Predicate;
//
///**
// * The skeleton code for comp6700.2016 assignment 2.
// * Draws free-hand shapes and morphs one into another.
// * <p>
// * Created with IntelliJ IDEA.
// * User: abx
// * Date: 21/04/2016
// * Time: 5:46 PM
// * Created for ass2 in package shapechanger
// *
// * @author abx
// * @author (your name and id)
// * @version 1.0
// * @see Morph
// */
//
//public class ShapeChanger extends Application {
//
//    private static final double useOfScreenFactor = 0.8;
//
//    private static Map<Predicate<KeyEvent>, Consumer<KeyEvent>> keyEventSelectors =
//            new HashMap<>();
//    // these are for smoother-like part, for drawing and splotching
//    private final Path onePath = new Path();
//    private final Path twoPath = new Path();
//    private final ArrayList<Point> points = new ArrayList<>();
//    private Morph oneMorph;
//    private Morph twoMorph;
//    private Point2D anchorPt;
//    private Point currentPoint;
//    private Point lastPoint;
//    private State state = State.CLEAR; // at the start there is no paths
//    private boolean normalised = false;
//    private Stage primaryStage;
//
//    /**
//     * The entry point of application.
//     *
//     * @param args the input arguments
//     */
//    public static void main(String[] args) {
//
//        launch(args);
//    }
//
//    /**
//     * override start method which creates the scene
//     * and all nodes and shapes in it (main window only),
//     * and redefines how the nodes react to user inputs
//     * and other events;
//     *
//     * @param primaryStage Stage (the top level container)
//     */
//    @Override
//    public void start(Stage primaryStage) throws Exception {
////        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
//        this.primaryStage = primaryStage;
//        primaryStage.setTitle("Shape Changing Objects");
//
//        /* next two lines are needed to read command-line args
//         * -- such are JavaFX's awkward ways
//         */
////        Parameters parameters = getParameters();
////        String fontFileName = parameters.getRaw().get(0);
//        final MenuBar menuContainer = new MenuBar();
//        menuContainer.setPrefHeight(20);
//        menuContainer.setPadding(new Insets(15, 12, 15, 12));
//
//
//        Menu menuFile = new Menu("File");
//        MenuItem open = new MenuItem("Open");
//        MenuItem save = new MenuItem("Save");
//        MenuItem quit = new MenuItem("Quit");
//        quit.setOnAction(event -> Platform.exit());
//
//        menuFile.getItems().addAll(open, save, quit);
//
//        Menu menuEdit = new Menu("Edit");
//        MenuItem select = new MenuItem("Select");
//        menuEdit.getItems().add(select);
//
//        Menu menuMorph = new Menu("Morph");
//        MenuItem triangle = new MenuItem("Triangle");
//        triangle.setOnAction(event -> {
//            this.createTriangle();
//
//        });
//        MenuItem ellipse = new MenuItem("Ellipse");
//        MenuItem rectangle = new MenuItem("Rectangle");
//        MenuItem polygon = new MenuItem("Polygon");
//        menuMorph.getItems().addAll(triangle, ellipse, rectangle, polygon);
//
//
//        menuContainer.getMenus().addAll(menuFile, menuEdit, menuMorph);
//
//        final Group group = new Group();
//        //wrapper.setPrefHeight(80);
//        // add paths
//        group.getChildren().addAll(onePath, twoPath, menuContainer);
//
//
//        final BorderPane root = new BorderPane();
//
//        /* reading the screen size and using it to set up all
//         * necessary dimensions, scaling factor and locations */
//        Rectangle2D screenBound = Screen.getPrimary().getBounds();
//        double screenWidth = screenBound.getWidth();
//        double screenHeight = screenBound.getHeight();
//        final Scene scene = new Scene(group, screenWidth * useOfScreenFactor,
//                screenHeight * useOfScreenFactor, Color.YELLOW);
//
//        // starting initial path
//        scene.onMousePressedProperty().set(event ->
//        {
//            System.out.println(String.format("onMousePressed at %f,%f", event.getX(), event.getY()));
//            anchorPt = new Point2D(event.getX(), event.getY());
//            // clean points which comprise a path to be drawn and start anew
//            points.clear();
//            points.add(Point.makePoint(anchorPt.getX(), anchorPt.getY()));
//            if (state == State.BOTH) state = State.CLEAR;
//
//            if (state == State.CLEAR) {
//                state = State.ONE;
//                normalised = false;
//                // clear both paths
//                this.clear();
//                // start collecting points into path one
//                onePath.setStrokeWidth(3);
//                onePath.setStrokeDashOffset(0.7);
//                onePath.setStroke(Color.RED);
//                onePath.getElements()
//                        .add(new MoveTo(anchorPt.getX(), anchorPt.getY()));
//            } else {
//                state = State.BOTH;
//                // start collecting points into path two
//                twoPath.setStrokeWidth(3);
//                twoPath.setStrokeDashOffset(0.7);
//                twoPath.setStroke(Color.GREEN);
//                twoPath.getElements()
//                        .add(new MoveTo(anchorPt.getX(), anchorPt.getY()));
//            }
//        });
//
//        // dragging creates lineTos added to the path
//        scene.onMouseDraggedProperty().set(event ->
//        {
//            System.out.println("onMouseDragged");
//            currentPoint = Point.makePoint(event.getX(), event.getY());
//            points.add(currentPoint);
//            if (state == State.ONE) {
//                onePath.getElements().add(new LineTo(currentPoint.x, currentPoint.y));
//            } else if (state == State.BOTH) {
//                twoPath.getElements().add(new LineTo(currentPoint.x, currentPoint.y));
//            }
//        });
//
//        // end onePath or twoPath (depending on which
//        // is being drawn) when mouse released event
//        scene.onMouseReleasedProperty().set(event ->
//        {
//            System.out.println("onMouseReleased");
////            System.out.printf("Switching from %s -> ", state);
//            lastPoint = Point.makePoint(event.getX(), event.getY());
//            points.add(lastPoint);
//            if (state == State.ONE) {
//                onePath.getElements().add(new LineTo(lastPoint.x, lastPoint.y));
//                onePath.getElements().add(new LineTo(anchorPt.getX(), anchorPt.getY()));
//                onePath.setStrokeWidth(1);
//                onePath.setFill(Color.BLUE);
//                oneMorph = new Morph(points);
//                System.out.printf("morph one has %d points %n", oneMorph.points.size());
//            } else if (state == State.BOTH) {
//                twoPath.getElements().add(new LineTo(lastPoint.x, lastPoint.y));
//                twoPath.getElements().add(new LineTo(anchorPt.getX(), anchorPt.getY()));
//                twoPath.setStrokeWidth(1);
//                twoPath.setFill(Color.DARKGRAY);
//                twoMorph = new Morph(points);
//                System.out.printf("morph two has %d points %n", twoMorph.points.size());
//            }
//            System.out.printf("%s%n", state);
//            System.out.printf("The size of path %s is %d%n",
//                    state == State.ONE ? "one" : "two", points.size());
//        });
//
//        // simple event handlers (key board inputs which initiate transitions
//        scene.onKeyPressedProperty().set(keyEvent ->
//        {
//            if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.M) {
//                if (state != State.BOTH) {
//                    System.out.println("Need BOTH paths to perform morphing");
//                    return;
//                }
//                if (!normalised) {
//                    System.out.println("Normalising before morphing can be attempted");
//                    normalisePaths(onePath, twoPath, oneMorph, twoMorph);
//                }
//                if (onePath.getElements().size() > 0
//                        && twoPath.getElements().size() > 0) {
//                    //twoPath.setFill(Color.GRAY);
//                    //twoPath.setOpacity(0.5);
////                    normalisePaths(onePath, twoPath);
//                    final Timeline timeline = makeTimeline(onePath, twoPath);
//                    timeline.play();
//                    System.out.println("Morphing should be seen now");
//                } else {
//                    System.out.println("Paths are empty");
//                }
//            } else if (keyEvent.isControlDown()
//                    && keyEvent.getCode() == KeyCode.N
//                    && state == State.BOTH) {
//                System.out.println("Attempt to Normalise...");
//                System.out.printf("oneMorph: %d, twoMorph: %d%n",
//                        oneMorph.points.size(), twoMorph.points.size());
//                // normalise longest morph to shortest
//                normalisePaths(onePath, twoPath, oneMorph, twoMorph);
//            } else if (keyEvent.isControlDown() && keyEvent.getCode() == KeyCode.Q) {
////                System.exit(0);
//                Platform.exit(); // better, JavaFX's way
//            }
//        });
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        primaryStage.setOnCloseRequest(e -> Platform.exit());
//
//    }
//
//    private void createTriangle() {
//        Double biasV = this.getMainSceneWidth() / Utils.randInt(2, 6);
//
//        Double biasH = this.getMainSceneHeight() / Utils.randInt(2, 6);
//        if (state == State.BOTH) {
//            this.clear();
//            state = State.CLEAR;
//        }
//
//        if (state == State.CLEAR) {
//            onePath.setStrokeWidth(3);
//            onePath.setStrokeDashOffset(0.7);
//            onePath.setStroke(Color.RED);
//
//
//            Point topPoint = Point.makePoint(this.getMainSceneWidth() / 2, biasH);
//            Point leftBottom = Point.makePoint(biasH, this.getMainSceneHeight() - biasV);
//            Point rightBottom = Point.makePoint(this.getMainSceneWidth() - biasH, this.getMainSceneHeight() - biasV);
//
//            points.clear();
//            onePath.getElements().add(new MoveTo(topPoint.x, topPoint.y));
//            points.add(topPoint);
//
//            onePath.getElements().add(new LineTo(leftBottom.x, leftBottom.y));
//            points.add(leftBottom);
//
//            onePath.getElements().add(new LineTo(rightBottom.x, rightBottom.y));
//            points.add(rightBottom);
//
//            onePath.setStrokeWidth(1);
//            onePath.setFill(Color.BLUE);
//            oneMorph = new Morph(points);
//            System.out.printf("morph one has %d points %n", oneMorph.points.size());
//
//
//            state = State.ONE;
//        } else if (state == State.ONE) {
//
//
//            twoPath.setStrokeWidth(3);
//            twoPath.setStrokeDashOffset(0.7);
//            twoPath.setStroke(Color.GREEN);
//
//            Point topPoint = Point.makePoint(this.getMainSceneWidth() / 2, biasH);
//            Point leftBottom = Point.makePoint(biasH, this.getMainSceneHeight() - biasV);
//            Point rightBottom = Point.makePoint(this.getMainSceneWidth() - biasH, this.getMainSceneHeight() - biasV);
//
//            points.clear();
//            twoPath.getElements().add(new MoveTo(topPoint.x, topPoint.y));
//            points.add(topPoint);
//
//            twoPath.getElements().add(new LineTo(leftBottom.x, leftBottom.y));
//            points.add(leftBottom);
//
//            twoPath.getElements().add(new LineTo(rightBottom.x, rightBottom.y));
//            points.add(rightBottom);
//
//            twoPath.setStrokeWidth(1);
//            twoPath.setFill(Color.DARKGRAY);
//            twoMorph = new Morph(points);
//            System.out.printf("morph two has %d points %n", oneMorph.points.size());
//            state = State.BOTH;
//        }
//
//        System.out.printf("%s%n", state);
//        System.out.printf("The size of path %s is %d%n",
//                state == State.ONE ? "one" : "two", points.size());
//    }
//
//    private void normalisePaths(Path p1, Path p2, Morph m1, Morph m2) {
//        if (normalised) return;
//        if (p1.getElements().size() > m2.points.size()) {
//            p1.setOpacity(0.5);
//            m1 = Morph.normalize(m1, m2.points.size());
//            p1.setFill(null);
//            adjustPath(p1, m1);
//        } else if (p1.getElements().size() < m2.points.size()) {
//            p2.setOpacity(0.5);
//            m2 = Morph.normalize(m2, m1.points.size());
//            p2.setFill(null);
//            adjustPath(p2, m2);
//            p2.setStrokeWidth(1);
//            p2.setFill(Color.CORNSILK);
//        }
//        p1.setStrokeWidth(1);
//        p1.setFill(Color.CORNSILK);
//        p2.setOpacity(0.5);
//        normalised = true;
//    }
//
//    private void adjustPath(Path path, Morph morph) {
//        System.out.printf("size of path %d, size of morph %d%n",
//                path.getElements().size(), morph.points.size());
//        path.getElements().clear();
//        double x0 = morph.anchorPoint().x;
//        double y0 = morph.anchorPoint().y;
//        path.getElements().add(new MoveTo(x0, y0));
//        Point p;
//        for (int i = 1; i < morph.points.size(); i++) {
//            p = morph.points.get(i);
//            path.getElements().add(new LineTo(p.x, p.y));
//        }
//        path.getElements().add(new LineTo(x0, y0));
//    }
//
//    private Timeline makeTimeline(Path p1, Path p2) {
//        assert p1.getElements().size() == p2.getElements().size() : "uneven paths";
//        final Timeline timeline = new Timeline();
//        timeline.setCycleCount(1);//(Timeline.INDEFINITE);
//        timeline.setAutoReverse(false);
//        int n = p1.getElements().size();
//        KeyValue kvx, kvy;
//        KeyFrame kf;
//        MoveTo ap1, ap2;
//        LineTo pe1, pe2;
//        ap1 = (MoveTo) p1.getElements().get(0);
//        ap2 = (MoveTo) p2.getElements().get(0);
//        kvx = new KeyValue(ap1.xProperty(), ap2.getX());
//        kvy = new KeyValue(ap1.yProperty(), ap2.getY());
//        kf = new KeyFrame(Duration.millis(5000), kvx, kvy);
//        timeline.getKeyFrames().add(kf);
//        for (int i = 1; i < n; i++) {
//            pe1 = (LineTo) p1.getElements().get(i);
//            pe2 = (LineTo) p2.getElements().get(i);
//            kvx = new KeyValue(pe1.xProperty(), pe2.getX());
//            kvy = new KeyValue(pe1.yProperty(), pe2.getY());
//            kf = new KeyFrame(Duration.millis(5000), kvx, kvy);
//            timeline.getKeyFrames().add(kf);
//        }
//        return timeline;
//    }
//
//    private void clear() {
//        // clear both paths
//        onePath.getElements().clear();
//        onePath.setOpacity(1);
//        onePath.setFill(null);
//        twoPath.getElements().clear();
//        twoPath.setOpacity(1);
//        twoPath.setFill(null);
//    }
//
//    private Scene getMainScene() {
//        return this.primaryStage.getScene();
//    }
//
//    private Double getMainSceneWidth() {
//        return this.getMainScene().getWidth();
//    }
//
//    private Double getMainSceneHeight() {
//        return this.getMainScene().getHeight();
//    }
//
//    private EventHandler<MouseEvent> getSaveButtonEventHandler() {
//        return mouseEvent -> {
//
//        };
//    }
//
//}
