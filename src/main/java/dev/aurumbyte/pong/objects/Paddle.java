package dev.aurumbyte.pong.objects;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import javafx.scene.paint.Color;

public class Paddle extends Entity {
    boolean isAI = false;
    double speed = 2;

    public Paddle(int xPos, int yPos, int width, int height){
        //init
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(SypherEngine sypherEngine) {
        //player controller
        if(!isAI){
            if(sypherEngine.keyListener.verticalAxis()){
                yPos += speed * sypherEngine.keyListener.getVerticalVector();
            }
        }
    }

    @Override
    public void render(SypherEngine engine) {
        //you know what this does hehe
        engine.getRenderer().drawRectangle((int)xPos, (int)yPos, (int)width, (int)height, true, Color.WHITE);
    }

    public void setIsAI(boolean AI) {
        isAI = AI;
    }

    public boolean isAI() {
        return isAI;
    }

    public int getxPos() {
        return (int) xPos;
    }

    public int getyPos() {
        return (int) yPos;
    }

    public double getSpeed() {
        return speed;
    }
}