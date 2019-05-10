package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;


public class SimplePowerUp extends GameEntity implements Interactable {
    private static Random rnd = new Random();



    public SimplePowerUp() {
        setImage(Globals.getInstance().getImage("PowerUp1"));

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
