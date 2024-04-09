package hws.hw8;

/**
 * Pac-Man enemies.
 *
 * @author Will Ponczak
 * @version 04/5/2024
 */
public class WallEnemy extends Enemy {

    public static final int RELEASE_TIME = 360;

    /**
     * constructor.
     *
     * @param levelData levelData.
     * @param spawnLocation where the wall enemy spawns.
     */
    public WallEnemy(Level levelData, Point spawnLocation) {
        super(levelData, spawnLocation, RELEASE_TIME);

    }

    /**
     * draw the wall enemy.
     */
    public void draw() {
        String imagePath = "hws/hw8/img/parentheses.png";
        String edibleImagePath = "hws/hw8/img/parentheses_scared.png";

        if (super.isEdible()) {
            DuckManGame.drawImage(currentPosition, edibleImagePath);
        } else if (!super.isEdible()) {
            DuckManGame.drawImage(currentPosition, imagePath);
        }
    }
}
