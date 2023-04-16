package de.romanamo.fractolio;


import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
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

        public void draw() {
            double width = getWidth();
            double height = getHeight();
            GraphicsContext gc = getGraphicsContext2D();

            if( width <= 1 || height <= 1) {
                return;
            }

            gc.clearRect(0, 0, width, height);
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
            //ZOOM
        });
    }

    public static void main(String[] args) {
        launch();
    }

}