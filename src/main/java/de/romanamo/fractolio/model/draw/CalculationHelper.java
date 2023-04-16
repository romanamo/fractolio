package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.math.DVector2D;
import de.romanamo.fractolio.model.evaluator.FunctionSetEvaluator;

import java.util.ArrayList;
import java.util.List;

public class CalculationHelper extends Thread{

    public List<ImageTile> tileList;

    public FunctionSetEvaluator<DVector2D> evaluator;

    public CalculationHelper(FunctionSetEvaluator<DVector2D> evaluator) {
        this.evaluator = evaluator;
        this.tileList = new ArrayList<>();
    }

    @Override
    public void run() {
        for(ImageTile tile: tileList) {
            tile.calculate(evaluator);
        }
        System.out.println(this.getName() + ": Finished");
    }

    public List<ImageTile> getTileList() {
        return tileList;
    }
}
