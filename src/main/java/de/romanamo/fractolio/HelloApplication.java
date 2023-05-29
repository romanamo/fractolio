package de.romanamo.fractolio;


import de.romanamo.fractolio.model.color.BiColorMap;
import de.romanamo.fractolio.model.color.BlackWhiteColorMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.evaluator.MandelbrotEvaluator;
import de.romanamo.fractolio.model.math.DVector2D;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static FunctionSetEvaluator<DVector2D> mandelbrot = new MandelbrotEvaluator(200);

    public static ImageDrawer drawer = new ImageDrawer(mandelbrot);

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

            if (width <= 1 || height <= 1) {
                return;
            }

            gc.clearRect(0, 0, width, height);

            int imageHeight = 500;
            int imageWidth = 700;
            int[][] raster = drawer.draw(imageWidth, imageHeight);
            PixelWriter pw = gc.getPixelWriter();

            BiColorMap colorMap = new BlackWhiteColorMap(true);
            for (int h = 0; h < (int) height; h++) {
                for (int w = 0; w < (int) width; w++) {
                    int relX = (int) Math.round((w / width) * imageWidth);
                    int relY = (int) Math.round((h / height) * imageHeight);
                    relX = Math.min(relX, imageWidth-1);
                    relY = Math.min(relY, imageHeight-1);
                    int color = colorMap.map(raster[relY][relX], drawer.getEvaluator().getMaxIteration());
                    int r = (color >> 16) & 0xFF;
                    int g = (color >> 8) & 0xFF;
                    int b = color & 0xFF;
                    pw.setColor(w, h, Color.rgb(r, g, b));
                }
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
    public void start(Stage stage){


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
            drawer.setZoom(zoom + scrollEvent.getDeltaY() * 0.01 * zoom);
            canvas.draw();
        });

        scene.addEventFilter(KeyEvent.ANY, e -> {
            double distance = 0.2 * (1.0 / drawer.getZoom());
            double x = drawer.getOffset().getX();
            double y = drawer.getOffset().getY();
            if (e.getCode() == KeyCode.W) {
                y += distance;
            } else if (e.getCode() == KeyCode.S) {
                y -= distance;
            }
            if (e.getCode() == KeyCode.A) {
                x -= distance;
            } else if (e.getCode() == KeyCode.D) {
                x += distance;
            }
            drawer.setOffset(new DVector2D(x, y));
            canvas.draw();
        });
    }

    public static void main(String[] args) {
        launch();
    }

}