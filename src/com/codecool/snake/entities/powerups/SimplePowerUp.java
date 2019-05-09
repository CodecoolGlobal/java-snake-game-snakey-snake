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

        setX(rnd.nextDouble() * (Globals.PLAYABLE_WIDTH_END - Globals.PLAYABLE_WIDTH_START) + 1);
        setY(rnd.nextDouble() * (Globals.PLAYABLE_HEIGHT_END - Globals.PLAYABLE_HEIGHT_START) + 1);
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
