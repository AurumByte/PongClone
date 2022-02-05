package dev.aurumbyte.pong;

import dev.aurumbyte.pong.objects.Ball;
import dev.aurumbyte.pong.objects.Paddle;
import dev.aurumbyte.sypherengine.config.EngineConfig;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.logic.GameManager;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends GameManager {
    Paddle player;
    Paddle enemy;

    Font font = new Font("Tahoma", 80);

    int playerScore = 0, enemyScore = 0;

    Ball ball;

    @Override
    public void init(SypherEngine sypherEngine) {
        //entity initialization
        int centerX = (int)sypherEngine.getRenderer().getScreenCenter().getX();
        int centerY = (int)sypherEngine.getRenderer().getScreenCenter().getY();

        player = new Paddle(100, 40, 25, 250);
        enemy = new Paddle( sypherEngine.getScreenWidth() - 100, 40, 25, 250);
        ball = new Ball(centerX, centerY);

        //adding the entities to render pipeline
        entities.add(player);

        enemy.setIsAI(true);
        entities.add(enemy);

        entities.add(ball);
    }

    @Override
    public void update(SypherEngine sypherEngine) {
        //updating score
        if(ball.getxPos() > sypherEngine.getScreenWidth()) playerScore++;
        if(ball.getxPos() < 0) enemyScore++;

        //enemy AI
        if((ball.getyPos() + 2 * ball.getRadius()) > (enemy.getyPos() + enemy.getHeight())){
            enemy.yPos += enemy.getSpeed();
        }

        if((ball.getyPos() - 2 * ball.getRadius()) < enemy.getyPos()){
            enemy.yPos -= enemy.getSpeed();
        }

        //setting boundaries
        player.setBoundary(player.getxPos(), player.getyPos(), (int)player.getWidth(), (int)player.getHeight());
        enemy.setBoundary(enemy.getxPos(), enemy.getyPos(), (int)enemy.getWidth(), (int)enemy.getHeight());
        ball.setBoundary(
                ball.getxPos() - ball.getRadius(),
                ball.getyPos() - ball.getRadius(),
                ball.getRadius()*2,
                ball.getRadius()*2
                );

        //collision detection
        if(ball.collidesWith(player) || ball.collidesWith(enemy)){
            ball.setxSpeed(ball.getxSpeed() * -1);
        }
    }

    @Override
    public void render(SypherEngine engine) {
        Renderer renderer = engine.getRenderer();

        //background
        renderer.drawRectangle(0, 0, engine.getScreenWidth(), engine.getScreenHeight(), true, Color.BLACK);

        //score
        renderer.drawText(
                String.valueOf(playerScore),
                30,
                100,
                Color.WHITE,
                font
        );

        renderer.drawText(
                String.valueOf(enemyScore),
                1210,
                100,
                Color.WHITE,
                font
        );
    }

    public static void main(String[] args){
        //initializing engine
        EngineConfig config = new EngineConfig();
        config.setTitle("PongClone - SypherEngine Edition");
        config.setFixedUpdate(80);

        SypherEngine.init(new Main(), config);

        //of course, you must run the game lmao
        SypherEngine.run(args);
    }
}