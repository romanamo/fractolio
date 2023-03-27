package de.romanamo.fractolio;

import de.romanamo.fractolio.model.color.HueMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.draw.ImageSize;
import de.romanamo.fractolio.model.evaluator.EvaluationContents;
import de.romanamo.fractolio.model.evaluator.IterationalSetEvaluator;
import de.romanamo.fractolio.model.evaluator.SetEvaluator;
import de.romanamo.fractolio.model.function.ComplexFunction;
import de.romanamo.fractolio.model.function.EuclideanMetric;
import de.romanamo.fractolio.model.function.QuadraticPolynomialFunction;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

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
            gc.clearRect(0, 0, width, height);

            SetEvaluator evaluator = new IterationalSetEvaluator(2, new Apfloat(2), new EuclideanMetric());
            ComplexFunction func = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842)));

            if(width > 1.0 && height > 1.0 ) {
                PixelWriter pw = gc.getPixelWriter();
                for(int i = 0; i < (int) width; i ++) {
                    for(int j = 0; j < (int) height; j ++) {

                        Apfloat scaledX = new Apfloat(-ImageDrawer.FRAME_WIDTH/2.0 + i * (ImageDrawer.FRAME_WIDTH / (double) width));
                        Apfloat scaledY = new Apfloat(ImageDrawer.FRAME_HEIGHT/2.0 - j * (ImageDrawer.FRAME_HEIGHT / (double) height));
                        Apcomplex c = new Apcomplex(scaledX, scaledY);

                        EvaluationContents contents = evaluator.evaluate(func, c);
                        pw.setColor(i, j, Color.color(0,0, contents.getRelation()));
                    }
                }
                //gc.fill();
                //SetEvaluator evaluator = new IterationalSetEvaluator(2, new Apfloat(2), new EuclideanMetric());
                //ComplexFunction func = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842)));
                //ImageSize size = new ImageSize((int) width, (int) height);
                //ImageDrawer drawer = new ImageDrawer(func, new HueMap(1, 0.0f), evaluator, size);
                //BufferedImage buffered = drawer.draw();
                //
                //gc.drawImage(SwingFXUtils.toFXImage(buffered, new WritableImage((int)width,(int)height)), 0, 0);
            }

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