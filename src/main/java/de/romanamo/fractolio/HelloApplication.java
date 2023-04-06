package de.romanamo.fractolio;

import de.romanamo.fractolio.model.color.HueMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.draw.ImageScaler;
import de.romanamo.fractolio.model.draw.ImageSize;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.function.ComplexFunction;
import de.romanamo.fractolio.model.function.EuclideanMetric;
import de.romanamo.fractolio.model.function.QuadraticPolynomialFunction;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloApplication extends Application {

    private final FunctionSetEvaluator evaluator = new FunctionSetEvaluator(12, new Apfloat(2), new EuclideanMetric());

    //private final ComplexFunction function = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842)));
    private final ComplexFunction function = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(0), new Apfloat(0)));

    private final ImageDrawer drawer = new ImageDrawer(function, new HueMap(1, 0), evaluator, new ImageSize( 200, 200));

    class ResizableCanvas extends Canvas {

        public ResizableCanvas() {
            // Redraw canvas when size changes.
            widthProperty().addListener(evt -> draw());
            heightProperty().addListener(evt -> draw());
        }

        public void draw() {
            double width = getWidth();
            double height = getHeight();
            GraphicsContext gc = getGraphicsContext2D();

            if( width <= 1 || height <= 1) {
                return;
            }

            gc.clearRect(0, 0, width, height);

            BufferedImage original = drawer.draw();
            BufferedImage resized = ImageScaler.scale(original, (int) width, (int) height);

            gc.drawImage(SwingFXUtils.toFXImage(resized, null), 0, 0);
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

        ResizableCanvas canvas = new ResizableCanvas();
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
        stage.setTitle("fractolio");

        stage.getIcons().add(new Image("file:assets/logo-round.png"));
        // Display the Stage
        stage.show();


        root.setOnScroll(scrollEvent -> {
            double zoom = drawer.getZoom();
            //TODO Change that zoom stays relative
            drawer.setZoom(zoom + scrollEvent.getDeltaY() * 0.01);
            canvas.draw();
        });
    }

    public static void main(String[] args) {
        launch();
    }

}