package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import javafx.geometry.Point2D;



public class WillFerrellEnemy extends Enemy implements Animatable, Interactable {

    private Point2D heading;
    private static Random rnd = new Random();
    private double speed = 3;
    private double direction = rnd.nextDouble() * 360;

    public WillFerrellEnemy() {
        super(15);

        setImage(Globals.getInstance().getImage("WillFerrellEnemy"));

        double spawnX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        while (Globals.PLAYABLE_WIDTH_START > spawnX || Globals.PLAYABLE_WIDTH_END < spawnX) {
            spawnX = rnd.nextDouble() * Globals.WINDOW_WIDTH;
        }

        double spawnY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        while (Globals.PLAYABLE_HEIGHT_START > spawnY || Globals.PLAYABLE_HEIGHT_END < spawnY) {
            spawnY = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
        }

        setX(spawnX);
        setY(spawnY);



/*
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);



 */
        setRotate(direction);

        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            /*if (direction - 180 < 0) {
                direction += 360;
            }*/

            if (collideVertical()) {
                direction = 180 - (direction - 180);
            }

            if (collideHorizontal()) {
                    direction = 180 - direction;
            }
            speed += 0.2;
            heading = Utils.directionToVector(direction, speed);
        }
            setX(getX() + heading.getX());
            setY(getY() + heading.getY());
    }

    @Override
    public void stepForOtherSnake() {
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof SnakeHead){
            System.out.println(getMessage());
            //Globals.getInstance().game.spawnWillFerrellEnemy(1);
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
