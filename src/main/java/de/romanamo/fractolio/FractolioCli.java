package de.romanamo.fractolio;

import de.romanamo.fractolio.log.Log;
import de.romanamo.fractolio.model.math.DVector2D;
import de.romanamo.fractolio.model.color.BlackWhiteMap;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.evaluator.JuliaEvaluator;
import de.romanamo.fractolio.model.evaluator.MandelbrotEvaluator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FractolioCli {

    private static Logger logger = Logger.getLogger(Log.NAME);

    public static void main(String[] args) {

        Log.initialize("");
        logger.setLevel(Level.ALL);
        String fractalType = args.length > 0 ? args[0].toLowerCase() : "";

        FunctionSetEvaluator<DVector2D> evaluator = null;

        /**
         * -p for Precision
         * -f <width> <height> format
         * -d Outputdirectory
         * -v verbose
         **/

        if(fractalType.equals("mandelbrot")) {
            evaluator = new MandelbrotEvaluator(10);
        } else if (fractalType.equals("julia")) {
            evaluator = new JuliaEvaluator(10, (element) -> (element.scale(2.0)));
        }
        else {
            System.err.println("Wrong Use");
            System.exit(1);
        }

        ImageDrawer drawer = new ImageDrawer(evaluator);

        drawer.draw(100, 100, new BlackWhiteMap());
    }
}