package com.codecool.snake;

import com.codecool.snake.entities.enemies.WillFerrellEnemy;
import com.codecool.snake.entities.enemies.Police;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.PowerUp3;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.powerups.PowerUp2;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.control.Button;


public class Game extends Pane {
    private Snake snake = null;
    private Snake secondSnake = null;
    private GameTimer gameTimer = new GameTimer();


    public Game() {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();

        init();
    }


    public void restart() {

        Button button = new Button("Restart");
        button.setLayoutX(0);
        button.setLayoutY(0);
        this.getChildren().add(button);
        button.setOnMouseClicked(event -> {
            System.out.println("Restarting app!");
            Globals.getInstance().stopGame();
            Globals.getInstance().display.clear();
            init();
            start();

        });


    }


    public void init() {
        spawnSnake();
        spawnSimpleEnemy(4);
        spawnWillFerrellEnemy(4);
        spawnPolice(2);
        spawnPowerUps(2);
        spawnPowerUps2(3);
        spawnPowerUps3(2);

        GameLoop gameLoop = new GameLoop(snake, secondSnake);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
        restart();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        snake = new Snake(new Vec2d(500, 500));
        secondSnake = new Snake(new Vec2d(600, 500));
    }

    public void spawnSimpleEnemy(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    public void spawnWillFerrellEnemy(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new WillFerrellEnemy();
    }

    void spawnPolice(int numberOfPolice) { for (int i = 0; i < numberOfPolice; ++i) new Police(); }

    void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    void spawnPowerUps2(int numberOfPowerUps2) {
        for(int i = 0; i < numberOfPowerUps2; ++i) new PowerUp2();
    }

    void spawnPowerUps3(int numberOfPowerUps3) {for(int i = 0; i < numberOfPowerUps3; ++i) new PowerUp3(); }


    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }


    public void setTableBackground(Image tableBackground) {
        BackgroundSize bgSize = new BackgroundSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT, false, false, false, false);
        setBackground(new Background(new BackgroundImage(tableBackground,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bgSize)));
    }


}
