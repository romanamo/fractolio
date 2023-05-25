package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.log.Log;
import de.romanamo.fractolio.model.math.DVector2D;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class CalculationHelper extends Thread{

    private static Logger logger = Logger.getLogger(Log.NAME);

    public List<ImageTile> tileList;

    public FunctionSetEvaluator<DVector2D> evaluator;

    public CalculationHelper(FunctionSetEvaluator<DVector2D> evaluator) {
        this.evaluator = evaluator;
        this.tileList = new ArrayList<>();
    }

    @Override
    public void run() {
        Date start = new Date();
        for(ImageTile tile: tileList) {
            tile.calculate(evaluator);
        }
        Date end = new Date();

        double delta = (end.getTime() - start.getTime()) / 1000.0;
        logger.fine(String.format("%s finished in: %.3f seconds", this.getName(), delta));
    }

    public List<ImageTile> getTileList() {
        return tileList;
    }
}
