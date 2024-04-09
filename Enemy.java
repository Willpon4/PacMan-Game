package hws.hw8;

/**
 * Pac-Man enemies.
 *
 * @author Will Ponczak
 * @version 04/5/2024
 */
public abstract class Enemy extends Actor {

    public static final double ENEMY_SPEED = 0.06;
    public static final int EDIBLE_DURATION = 300;
    public static final int POINTS = 200;

    protected int timeUntilReleased;
    protected int timeUntilNormal;

    /**
     * Default constructor.
     *
     * @param dataLevel levelmap.
     * @param spawnLocation where the enemy spawns.
     * @param timeUntilReleased time until released.
     */
    public Enemy(Level dataLevel, Point spawnLocation, int timeUntilReleased) {
        super(dataLevel, spawnLocation, Direction.UP, ENEMY_SPEED);
        timeUntilNormal = 0;
        this.timeUntilReleased = timeUntilReleased;
    }

    /**
     * Make the enemy edible.
     */
    public void makeEdible() {
        if (timeUntilReleased <= 0) {
            timeUntilNormal = EDIBLE_DURATION;
            super.setDesiredDirection(getCurrentDirection().getOpposite());
        }
    }

    /**
     * if the enemy is edible.
     *
     * @return boolean.
     */
    public boolean isEdible() {
        if (timeUntilNormal > 0) {
            return true;
        }
        return false;
    }

    /**
     * reset the enemy.
     */
    public void reset() {
        super.reset();
        timeUntilNormal = 0;
    }

    /**
     * update the enemy.
     */
    public void update() {
        if (timeUntilReleased > 0) {
            timeUntilReleased = timeUntilReleased - 1;
        }
        if (timeUntilReleased <= 0) {
            if (isEdible()) {
                timeUntilNormal -= 1;
            }
            if (isStopped()) {
                super.setDesiredDirection(
                        getCurrentDirection().getRandomTurn());
            }
        }
        super.update();

    }
}
