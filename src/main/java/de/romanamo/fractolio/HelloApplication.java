package de.romanamo.fractolio;

import de.romanamo.fractolio.model.color.HueMap;
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
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apfloat.Apcomplex;
import org.apfloat.Apfloat;

import javafx.embed.swing.SwingFXUtils;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //stage.setTitle("Hello!");
        //stage.setScene(scene);
        //stage.show();
        Canvas canvas = new Canvas(400, 400);
        // Set the width of the Canvas
        canvas.setWidth(400);
        // Set the height of the Canvas
        canvas.setHeight(400);

        SetEvaluator evaluator = new IterationalSetEvaluator(5, new Apfloat(2), new EuclideanMetric());

        ComplexFunction func = new QuadraticPolynomialFunction(new Apcomplex(new Apfloat(-0.70176), new Apfloat(-0.3842)));
        ImageSize size = new ImageSize(100,100);
        ImageDrawer drawer = new ImageDrawer(func, new HueMap(1, 0.0f), evaluator, size);
        BufferedImage buffered = drawer.draw();

        // Get the graphics context of the canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.drawImage(SwingFXUtils.toFXImage(buffered, new WritableImage(400,400)), 0, 0);


        // Create the Pane
        Pane root = new Pane();
        // Add the Canvas to the Pane
        root.getChildren().add(canvas);
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