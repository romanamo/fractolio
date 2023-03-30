package de.romanamo.fractolio;

import de.romanamo.fractolio.model.color.BlackWhiteMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.draw.ImageSize;
import de.romanamo.fractolio.model.evaluator.IterationalSetEvaluator;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;
import de.romanamo.fractolio.model.function.ComplexFunction;
import de.romanamo.fractolio.model.function.EuclideanMetric;
import de.romanamo.fractolio.model.function.QuadraticPolynomialFunction;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloApplication extends Application {

    class ResizableCanvas extends Canvas {

        public ResizableCanvas() {
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> draw());
            heightProperty().addListener(evt -> draw());
        }

        private void draw() {
            double width = getWidth();
            double height = getHeight();
            GraphicsContext gc = getGraphicsContext2D();

            if( width <= 1 || height <= 1) {
                return;
            }

            gc.clearRect(0, 0, width, height);

            SetEvaluator evaluator = new IterationalSetEvaluator(20, new Apfloat(2), new EuclideanMetric());
            ComplexFunction func = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842)));

            ImageDrawer drawer = new ImageDrawer(func, new BlackWhiteMap(), evaluator, new ImageSize((int) width, (int) height));

            BufferedImage buffered = drawer.draw();
        }

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) {
            return getWidth();
        }

        @Override
        public double prefHeight(double width) {
            return getHeight();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {


        // Create the Pane
        Pane root = new Pane();
        // Add the Canvas to the Pane

        Canvas canvas = new ResizableCanvas();
        root.getChildren().add(canvas);

        canvas.widthProperty().bind(
                root.widthProperty());
        canvas.heightProperty().bind(
                root.heightProperty());

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Creation of a Canvas");
        // Display the Stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}