package hws.hw8;

import java.util.ArrayList;

/**
 * JMU CS themed Pac-Man clone.
 *
 * @author CS159 Faculty and YOUR NAME HERE
 * @version 03/25/2024
 */
public class DuckManGame implements Playable {

    public static final int GRID_SIZE = 32;
    public static final Point PLAYER_SPAWN = new Point(8.5, 5.5);
    public static final Point ENEMY_SPAWN = new Point(8.5, 11.5);

    private boolean running;
    private Level levelMap;

    private ArrayList<Drawable> drawables;
    private ArrayList<Dot> dots;
    private ArrayList<Updatable> updatables;
    private ArrayList<Enemy> enemies;

    private NumericDisplay score;
    private NumericDisplay lives;
    private Player player;

    /**
     * Default constructor.
     */
    public DuckManGame() {
        levelMap = new Level(GRID_SIZE);
        drawables = new ArrayList<Drawable>();
        dots = new ArrayList<Dot>();
        updatables = new ArrayList<Updatable>();
        enemies = new ArrayList<Enemy>();
    }

    /**
     * Draw an image on the screen.
     *
     * @param position where to draw the image
     * @param imagePath path to the image file
     */
    public static void drawImage(Point position, String imagePath) {
        GameDriver.picture(position, GRID_SIZE, imagePath);
    }

    /**
     * Add a dot to the game.
     *
     * @param dot the dot to add
     */
    public void addDot(Dot dot) {
        dots.add(dot);
        drawables.add(dot);
    }

    /**
     * Add an enemy to the game.
     *
     * @param enemy the enemy to add
     */
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
        drawables.add(enemy);
        updatables.add(enemy);
    }

    /**
     * Update the game state when the player collides with dots and enemies.
     */
    public void handlePlayerCollisions() {
        int passiveScore = 0;
        ArrayList<Dot> toRemove = new ArrayList<>();
        for (int i = 0; i < dots.size(); i++) {
            if (player.collidesWith(dots.get(i))) {
                if (dots.get(i) instanceof MagicEgg) {
                    passiveScore += score.getValue() + MagicEgg.POINTS;
                    score.setValue(passiveScore);
                    for (int k = 0; k < enemies.size(); k++) {
                        enemies.get(k).makeEdible();
                    }
                    toRemove.add(dots.get(i));
                } else if (dots.get(i) instanceof Dot) {
                    passiveScore += score.getValue() + Dot.POINTS;
                    score.setValue(passiveScore);
                    toRemove.add(dots.get(i));
                }


            }

        }
        for (int j = 0; j < toRemove.size(); j++) {
            dots.remove(toRemove.get(j));
            drawables.remove(toRemove.get(j));

        }
        if (dots.size() == 0) {
            running = false;
        }

        for (int r = 0; r < enemies.size(); r++) {
            if (player.collidesWith(enemies.get(r))) {
                if (enemies.get(r).isEdible()) {
                    passiveScore += score.getValue() + Enemy.POINTS;
                    score.setValue(passiveScore);
                    enemies.get(r).reset();
                } else {
                    lives.setValue(lives.getValue() - 1);
                    player.reset();
                    if (lives.getValue() <= 0) {
                        running = false;
                    }
                }
            }
        }

    }

    /**
     * Iterate through the level map and create a new dot/egg object if the map
     * has a dot/egg at that position.
     */
    public void spawnNewDots() {
        for (int i = 0; i < levelMap.getHeight(); i++) {
            double x = i + 1.5;
            for (int k = 0; k < levelMap.getWidth(); k++) {
                double y = k + 1.5;
                Point point = new Point(x, y);
                if (levelMap.isDot(x, y)) {
                    Dot dot = new Dot(point);
                    addDot(dot);
                }
                if (levelMap.isEgg(x, y)) {
                    MagicEgg egg = new MagicEgg(point);
                    addDot(egg);
                }
            }

        }
        
    }

    /**
     * Create and add a player to the game.
     */
    public void spawnNewPlayer() {
        player = new Player(levelMap, PLAYER_SPAWN);
        drawables.add(player);
        updatables.add(player);
    }

    /**
     * Create and add enemies to the game.
     */
    public void spawnNewEnemies() {
        WallEnemy wallEnemy = new WallEnemy(levelMap, ENEMY_SPAWN);
        WanderEnemy wanderEnemy = new WanderEnemy(levelMap, ENEMY_SPAWN);
        ChaseEnemy chaseEnemy = new ChaseEnemy(levelMap, ENEMY_SPAWN, player);

        addEnemy(chaseEnemy);
        addEnemy(wallEnemy);
        addEnemy(wanderEnemy);
    }

    @Override
    public void startGame() {
        Point upperLeft = new Point(10, GameDriver.SCREEN_HEIGHT - 10);
        score = new NumericDisplay(upperLeft, "Points", 0);
        drawables.add(score);

        Point upperRight = new Point(GameDriver.SCREEN_WIDTH - 80,
                GameDriver.SCREEN_HEIGHT - 10);
        lives = new NumericDisplay(upperRight, "Lives", 3);
        drawables.add(lives);

        spawnNewDots();
        spawnNewPlayer();
        spawnNewEnemies();

        running = true;
    }

    @Override
    public void updateAll() {
        if (!running) {
            return;
        }

        handlePlayerCollisions();

        for (int i = 0; i < updatables.size(); i++) {
            updatables.get(i).update();
        }



    }

    @Override
    public void drawAll() {
        levelMap.draw();
        for (Drawable drawable : drawables) {
            drawable.draw();
        }
    }

}
