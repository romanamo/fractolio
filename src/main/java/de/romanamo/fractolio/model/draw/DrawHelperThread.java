package de.romanamo.fractolio.model.draw;

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
            int iteration = drawer.getEvaluator().evaluate(drawer.getFunction(), idea.getNumber());

            int color = drawer.getColorMap().translate((double) iteration / drawer.getEvaluator().getMaxIteration());
            idea.getImage().setRGB(idea.getX(), idea.getY(), color);

        }
    }
}
