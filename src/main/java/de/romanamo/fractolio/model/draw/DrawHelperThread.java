package de.romanamo.fractolio.model.draw;

import de.romanamo.fractolio.model.evaluator.EvaluationContents;

import java.util.List;

public class DrawHelperThread extends Thread{

    private List<DrawInfo> info;
    public DrawHelperThread(List<DrawInfo> info) {
        this.info = info;
    }
    @Override
    public void run() {
        for(DrawInfo idea: info) {
            ImageDrawer drawer = idea.getDrawer();
            EvaluationContents content = drawer.getEvaluator().evaluate(drawer.getFunction(), idea.getNumber());

            int color = drawer.getColorMap().translate(content.getRelation());
            idea.getImage().setRGB(idea.getX(), idea.getY(), color);

        }
    }
}
