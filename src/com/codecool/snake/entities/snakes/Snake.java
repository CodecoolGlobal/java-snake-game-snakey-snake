package com.codecool.snake.entities.snakes;

import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Display;
import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import jdk.nashorn.internal.objects.Global;

import javax.swing.*;

import static java.awt.Color.cyan;

import java.util.Random;


public class Snake implements Animatable {
    private static float speed = 2;
    private int health = 100;
    KeyCode left = null;
    KeyCode right = null;

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;


    public Snake(Vec2d position, KeyCode LEFT, KeyCode RIGHT) {
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        this.left = LEFT;
        this.right = RIGHT;

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForGameOverConditions();

        body.doPendingModifications();

    }



    private SnakeControl getUserInput() {
        SnakeControl turnDir = SnakeControl.INVALID;
        if(InputHandler.getInstance().isKeyPressed(this.left)) turnDir = SnakeControl.TURN_LEFT;
        if(InputHandler.getInstance().isKeyPressed(this.right)) turnDir = SnakeControl.TURN_RIGHT;
        return turnDir;
    }



    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position);
            if (getLastPart() instanceof SnakeHead) {
                newBodyPart.setBodyPartID(i);
            } else {
                SnakeBody tail = (SnakeBody) getLastPart();
                if (tail.getBodyPartID() > 0) {
                    newBodyPart.setBodyPartID(tail.getBodyPartID()+1);
                } else {
                    newBodyPart.setBodyPartID(i);
                }
            }
            body.add(newBodyPart);

        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
        if (health > 100) {
            health = 100;
        }
    }

    public int getHealth() {
        return health;
    }

    private void checkForGameOverConditions() {

            if (head.isOutOfBounds() || health <= 0) {
                System.out.println("Game Over");
                SnakeHead.gameOver();

            }
    }
;
    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }

    public static void setSpeed(float speed) {
        Snake.speed = speed;
    }

    public static float getSpeed() {
        return speed;
    }
}
