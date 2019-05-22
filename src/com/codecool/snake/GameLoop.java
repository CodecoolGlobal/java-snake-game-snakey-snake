package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.powerups.PowerUp2;
import com.codecool.snake.entities.powerups.PowerUp3;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameLoop {
    private Snake snake;
    private Snake secondSnake;
    private boolean running = false;

    public GameLoop(Snake snake, Snake secondSnake) {
        this.snake = snake;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            snake.step();
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
            checkPowerUps();
            respawnPowerUps();

        }

        Globals.getInstance().display.frameFinished();
    }

    private List<Integer> checkPowerUps() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        List<Integer> numOfPowerUps = new ArrayList<>();
        numOfPowerUps.add(0, 0);
        numOfPowerUps.add(1, 0);
        numOfPowerUps.add(2, 0);
        for (int i = 0; i < gameObjs.size(); i++) {
            if (gameObjs.get(i) instanceof SimplePowerUp) {
                numOfPowerUps.set(0, numOfPowerUps.get(0) +1);
            } else if (gameObjs.get(i) instanceof PowerUp2) {
                numOfPowerUps.set(1, numOfPowerUps.get(1) +1);
            } else if (gameObjs.get(i) instanceof PowerUp3) {
                numOfPowerUps.set(2, numOfPowerUps.get(2) +1);
            }
        } return numOfPowerUps;
    }

    private void respawnPowerUps() {
        List<Integer> numOfPowerUps = checkPowerUps();
        if (numOfPowerUps.get(0) <= 2) {
            Globals.getInstance().game.spawnPowerUps(Globals.getInstance().random.nextInt(3) +1);
        } else if (numOfPowerUps.get(1) <= 2) {
            Globals.getInstance().game.spawnPowerUps2(Globals.getInstance().random.nextInt(2) +1);
        } else if (numOfPowerUps.get(2) <= 2) {
            Globals.getInstance().game.spawnPowerUps3(Globals.getInstance().random.nextInt(4) +1);
        }
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }
}
