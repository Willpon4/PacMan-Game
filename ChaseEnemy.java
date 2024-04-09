package hws.hw8;

/**
 * Pac-Man Chase enemy.
 *
 * @author Will Ponczak
 * @version 04/5/2024
 */
public class ChaseEnemy extends Enemy {

    public static final int RELEASE_TIME = 0;
    private Player player;

    /**
     * Constructor.
     *
     * @param levelData levelData.
     * @param spawnPoint where the enemy spawns.
     * @param player player to chase.
     */
    public ChaseEnemy(Level levelData, Point spawnPoint, Player player) {
        super(levelData, spawnPoint, RELEASE_TIME);
        this.player = player;
    }

    /**
     * Update the chase enemy.
     */
    public void update() {
        if (isCenteredOnGrid()) {
            if (getCurrentDirection().isUpDown()) {
                if (this.player.getCurrentPosition().
                        getX() < super.getCurrentPosition().getX()) {
                    super.setDesiredDirection(Direction.LEFT);
                } else if (this.player.getCurrentPosition().
                        getX() > super.getCurrentPosition().getX()) {
                    super.setDesiredDirection(Direction.RIGHT);
                }
            } else {
                if (this.player.getCurrentPosition().
                        getY() < super.getCurrentPosition().getY()) {
                    super.setDesiredDirection(Direction.DOWN);
                } else if (this.player.getCurrentPosition().
                        getY() > super.getCurrentPosition().getY()) {
                    super.setDesiredDirection(Direction.UP);
                }
            }
        }
        super.update();
    }

    /**
     * draw the chase enemy.
     */
    public void draw() {
        String imagePath = "hws/hw8/img/semicolons.png";
        String edibleImagePath = "hws/hw8/img/semicolons_scared.png";

        if (super.isEdible()) {
            DuckManGame.drawImage(currentPosition, edibleImagePath);
        } else if (!super.isEdible()) {
            DuckManGame.drawImage(currentPosition, imagePath);
        }
    }
}
