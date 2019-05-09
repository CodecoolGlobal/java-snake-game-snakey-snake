package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;

import java.util.Random;

public class PowerUp3 extends GameEntity implements Interactable {

    private Point2D heading;
    private static Random rnd = new Random();

    public PowerUp3() {

        setImage(Globals.getInstance().getImage("PowerUp3"));

        setX(rnd.nextDouble() * (Globals.PLAYABLE_WIDTH_END - Globals.PLAYABLE_WIDTH_START) + 1);
        setY(rnd.nextDouble() * (Globals.PLAYABLE_HEIGHT_END - Globals.PLAYABLE_HEIGHT_START) + 1);

        double direction = rnd.nextDouble() * 180;

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}

