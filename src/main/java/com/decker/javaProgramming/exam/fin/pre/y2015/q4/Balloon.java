package com.decker.javaProgramming.exam.fin.pre.y2015.q4;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Balloon extends Application {

    final double ropeLength = 70;
    final double radius = 30;
    final int sceneWidth = 700;
    final int sceneHeight = 550;
    final int baseX = sceneWidth / 2;
    final int baseY = sceneHeight;
    final int swing = 50;
    final Point2D equilibriumPoint = new Point2D(baseX, baseY - ropeLength - radius);


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Let Balloon Fly!");
//        primaryStage.setFullScreen(true);
        final Group root = new Group();
        final StringBuffer typeAndInflate = new StringBuffer();
        final Label label = new Label();
        Model model = new Model();
        double oneStepRadiusInflate = 2;
        label.alignmentProperty().setValue(Pos.BASELINE_CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        Scene scene = new Scene(root, sceneWidth, sceneHeight, Color.CYAN);
        label.setMinSize(scene.getWidth(), Control.USE_COMPUTED_SIZE);
        label.setMaxSize(scene.getWidth(), Control.USE_COMPUTED_SIZE);
        final Circle balloon = new Circle(equilibriumPoint.getX() - swing,
                equilibriumPoint.getY(), radius);
        balloon.setFill(Color.GOLD);
        Path rope = new Path();
        rope.setStrokeWidth(1);
        rope.setStrokeDashOffset(0.8);
        rope.setStroke(Color.BLACK);
        MoveTo moveTo = new MoveTo(baseX, baseY);
        LineTo lineTo = new LineTo(balloon.getCenterX(), balloon.getCenterY() + radius);
        rope.getElements().addAll(moveTo, lineTo);

        // timeline for wind swinging
        Timeline wind = new Timeline();
        wind.setCycleCount(Timeline.INDEFINITE);
        wind.setAutoReverse(true);
        Point2D rightSwing = new Point2D(equilibriumPoint.getX() + swing,
                equilibriumPoint.getY());
        setTimeline(wind, balloon, rope, rightSwing, windInterpolator);
        wind.setAutoReverse(true);
        wind.setCycleCount(Timeline.INDEFINITE);
        wind.play();

        // timeline for flying away
        Timeline fly = new Timeline();
        int flyX = sceneWidth + 100;
        int flyY = 0;
        Point2D away = new Point2D(flyX, flyY);
        KeyValue kv1, kv2;
        // frame in fly timeline which makes the rope to detach and drop on the ground
        LineTo pe = (LineTo) rope.getElements().get(1);
        kv1 = new KeyValue(pe.xProperty(), baseX + ropeLength, ip);
        kv2 = new KeyValue(pe.yProperty(), sceneHeight, ip);
        fly.getKeyFrames().add(new KeyFrame(Duration.millis(2000), kv1, kv2));
        // TODO provide the implementation as solution of Q4.2a
        // Hint: look at how the rope dropping part is done (three lines above)
        // (the remainder of Q4.2 task complete should be done on lines Balloon:120)
        fly.setAutoReverse(false);
        fly.setCycleCount(1);

        //timeline for introductiory text
        label.setText("Breeze... balloon is swinging");
        Timeline intro = new Timeline();
        KeyValue kv = new KeyValue(label.opacityProperty(), 0.0);
        intro.getKeyFrames().add(new KeyFrame(Duration.millis(5000), kv));
        intro.setOnFinished(ae -> {label.setOpacity(1.0); label.setText("");});
        intro.play();
        root.getChildren().addAll(balloon, rope, label);

        scene.onKeyPressedProperty().set(
                ke -> {
                    KeyCode kc = ke.getCode();
                    if (kc.isLetterKey() || kc.isWhitespaceKey()) {
                        model.add(ke.getText());
                    } else if (ke.isShiftDown()) {
                        model.add(ke.getText());
                    }
                    if (kc == KeyCode.BACK_SPACE && !model.isEmpty())
                        model.deleteLastChar();
                    label.setText("You typed: " + model.toString());
                    int n = model.matchingLength();
                    // TODO provide the implementation as solution of Q4.1b
                    setTimeline(wind, balloon, rope, rightSwing, windInterpolator,20);
                    // (make the balloon raduis inflate with every new 
                    // matching character typed in)
                    // your code goes here
                    if (model.isMatched()) {
                        label.setText("Let's fly!");
                        wind.stop();
                        // TODO provide the implementation as solution of Q4.2b
                        // (complete the timeline fly definition (lines Balloon:86-88) 
                        // and play it here)
                    }
                }
        );

        // adding everything to the stage and showing it
	Group group = new Group();
        group.getChildren().add(balloon);
        root.getChildren().addAll(group);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * will set the timeline tl to move circle c and rope r from
     * their current state to position at point p (the base of the rope
     * remains unchanged, only the point of attachment of r to c)
     */
    private void setTimeline(Timeline tl, Circle c, Path r, Point2D p, Interpolator ip) {
        tl.getKeyFrames().removeAll();
        KeyValue kv1, kv2;
        kv1 = new KeyValue(c.centerXProperty(), p.getX(), ip);
        kv2 = new KeyValue(c.centerYProperty(), p.getY(), ip);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(2000), kv1, kv2));
        LineTo pe = (LineTo) r.getElements().get(1);
        kv1 = new KeyValue(pe.xProperty(), p.getX(), ip);
        kv2 = new KeyValue(pe.yProperty(), p.getY(), ip);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(2000), kv1, kv2));
    }

    private void setTimeline(Timeline tl, Circle c, Path r, Point2D p, Interpolator ip,Integer millis) {
        tl.getKeyFrames().removeAll();
        KeyValue kv1, kv2;
        kv1 = new KeyValue(c.centerXProperty(), p.getX(), ip);
        kv2 = new KeyValue(c.centerYProperty(), p.getY(), ip);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(millis), kv1, kv2));
        LineTo pe = (LineTo) r.getElements().get(1);
        kv1 = new KeyValue(pe.xProperty(), p.getX(), ip);
        kv2 = new KeyValue(pe.yProperty(), p.getY(), ip);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(millis), kv1, kv2));
    }

    // two interpolators to make animation non-monotone (can be ignored)
    private Interpolator ip = new Interpolator() {
        @Override
        protected double curve(double v) {
            return Math.sqrt(v);
        }
    };

    private Interpolator windInterpolator = new Interpolator() {
        @Override
        protected double curve(double v) {
            return (1 - Math.cos(v * Math.PI)) / 2;
        }
    };

    public static void main(String[] args) {
        launch(args);
    }
}
