package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.Enemy;
import com.codecool.snake.entities.enemies.Police;
import com.codecool.snake.entities.powerups.PowerUp3;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.PowerUp2;

import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class SnakeHead extends GameEntity implements Interactable {
    private static final float turnRate = 2;
    private Snake snake;
    public SnakeHead(Snake snake, Vec2d position) {
        this.snake = snake;
        setImage(Globals.getInstance().getImage("SnakeHead"));
        setPosition(position);
    }

    public void updateRotation(SnakeControl turnDirection, float speed) {
        double headRotation = getRotate();

        if (turnDirection.equals(SnakeControl.TURN_LEFT)) {
            headRotation = headRotation - turnRate;
        }
        if (turnDirection.equals(SnakeControl.TURN_RIGHT)) {
            headRotation = headRotation + turnRate;
        }

        // set rotation and position
        setRotate(headRotation);
        Point2D heading = Utils.directionToVector(headRotation, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        if(entity instanceof Enemy){
            System.out.println(getMessage());
            snake.changeHealth(((Enemy) entity).getDamage()*(-1));
            System.out.println(snake.getHealth());
        }
        if (entity instanceof Police) {
            System.out.println(getMessage());
            gameOver();
        }
        if(entity instanceof SimplePowerUp){
            System.out.println(getMessage());
            snake.addPart(4);
        }
        if(entity instanceof SnakeBody && ((SnakeBody) entity).getBodyPartID() > 3) {
            System.out.println("Oh no! Marky Mark stepped on his own casettes! :(");
            snake.changeHealth(-100);
        }
        if (entity instanceof PowerUp2) {
            System.out.println(getMessage());
            snake.addPart(2);
        }
        if (entity instanceof PowerUp3) {
            System.out.println(getMessage());
            snake.changeHealth(20);
        }
    }

    static void gameOver() {
        Globals.getInstance().stopGame();
        Text over = new Text("Game Over \n Press the 'Restart' Button");
        over.setFill(Color.GREEN);
        over.setStyle("-fx-font: 55 arial;");
        over.setY(300);
        over.setX(300);
        Globals.getInstance().display.add(over);
    }

    @Override
    public String getMessage() {
        return "IMMA SNAEK HED! SPITTIN' MAH WENOM! SPITJU-SPITJU!";
    }

}
