package dev.aurumbyte.pong.objects;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class Ball extends Entity {
    int radius = 20;
    int speed = 4;
    int xSpeed = speed, ySpeed = speed;
    boolean play = false;

    public Ball(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;

        this.setWidth(2 * radius);
        this.setHeight(2 * radius);
    }

    @Override
    public void update(SypherEngine sypherEngine) {
        //ball moving once a click happens (or spacebar is pressed)
        if(sypherEngine.mouseListener.isDown(MouseButton.PRIMARY) || sypherEngine.keyListener.isDown(KeyCode.SPACE)){
            play = true;
        }

        //check if ball goes out of bounds, aka, a player or opponent misses
        if(xPos > sypherEngine.getScreenWidth() || xPos < 0){
            play = false;

            xPos = sypherEngine.getRenderer().getScreenCenter().getX();
            yPos = sypherEngine.getRenderer().getScreenCenter().getY();
        }

        //check for out of bounds and reflecting the ball (y axis)
        if(play){
            if(yPos >= sypherEngine.getScreenHeight() || yPos <= 0){
                ySpeed *= -1;
            }

            //ball movement
            xPos -= xSpeed;
            yPos += ySpeed;
        }
    }

    @Override
    public void render(SypherEngine sypherEngine) {
        //you know what this does hehe
        sypherEngine.getRenderer().drawCircle((int)xPos, (int)yPos, radius, true, Color.WHITE);
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getxPos() {
        return (int) xPos;
    }

    public int getyPos() {
        return (int) yPos;
    }

    public int getRadius() {
        return radius;
    }
}