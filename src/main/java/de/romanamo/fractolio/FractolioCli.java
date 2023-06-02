package de.romanamo.fractolio;

import de.romanamo.fractolio.log.Log;
import de.romanamo.fractolio.model.draw.ImageDrawer;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;
import de.romanamo.fractolio.model.math.DVector2D;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FractolioCli {

    private static Logger logger = Logger.getLogger(Log.NAME);

    public static void notmain(String[] args) {

        Log.initialize("");
        logger.setLevel(Level.ALL);
        String fractalType = args.length > 0 ? args[0].toLowerCase() : "";

        FunctionSetEvaluator<DVector2D> evaluator = null;

        /*
         * -p for Precision
         * -f <width> <height> format
         * -d Outputdirectory
         * -v verbose
         */

        ImageDrawer drawer = new ImageDrawer(evaluator);

        drawer.draw(100, 100);
    }
}