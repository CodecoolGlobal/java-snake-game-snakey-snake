package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1278;
    public static final double WINDOW_HEIGHT = 720;

    public Display display;
    public Game game;
    public Random random = new Random();

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();

        resources.addImage("DicaprioEnemy", new Image("angry_dicaprio.png"));
        resources.addImage("WillFerrellEnemy", new Image("angry_willferrell.png"));
        resources.addImage("SnakeHead", new Image("marky.png"));
        resources.addImage("SnakeBody", new Image("casette_body.png"));
        resources.addImage("Police", new Image("police.png"));
        resources.addImage("PowerUp1", new Image("powerup1.png"));
        resources.addImage("PowerUp2", new Image("powerup2.png"));
        resources.addImage("PowerUp3",  new Image("powerup3.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }


    private Globals() {
        // singleton needs the class to have private constructor
    }
}
