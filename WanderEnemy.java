package hws.hw8;

/**
 * Pac-Man enemies.
 *
 * @author Will Ponczak
 * @version 04/5/2024
 */
public class WanderEnemy extends Enemy {

    public static final int RELEASE_TIME = 180;

    /**
     * constructor.
     *
     * @param levelData levelData.
     * @param spawnLocation where the wander enemy spawns.
     */
    public WanderEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);

    }

    /**
     * update the wander enemy.
     */
    public void update() {
        if (super.isCenteredOnGrid()) {
            super.setDesiredDirection(getCurrentDirection().getRandomTurn());
        }
        super.update();
    }

    /**
     * draw the wander enemy.
     */
    public void draw() {
        String imagePath = "hws/hw8/img/brackets.png";
        String edibleImagePath = "hws/hw8/img/brackets_scared.png";

        if (super.isEdible()) {
            DuckManGame.drawImage(currentPosition, edibleImagePath);
        } else if (!super.isEdible()) {
            DuckManGame.drawImage(currentPosition, imagePath);
        }
    }

}
