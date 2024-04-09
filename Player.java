package hws.hw8;

/**
 * Player.
 *
 * @author Will Ponczak
 * @version 03/31/2024
 */
public class Player extends Actor {

    public static final double PLAYER_SPEED = 0.07;

    /**
     * Constructor.
     *
     * @param level current level
     * @param spawnLocation spawn location
     */
    public Player(Level level, Point spawnLocation) {
        // How do I do this constructor?
        super(level, spawnLocation, Direction.LEFT, PLAYER_SPEED);
    }

    /**
     * Update the game.
     *
     */
    public void update() {
        if (GameDriver.upPressed()) {
            super.setDesiredDirection(Direction.UP);
        }
        if (GameDriver.downPressed()) {
            super.setDesiredDirection(Direction.DOWN);

        }
        if (GameDriver.leftPressed()) {
            super.setDesiredDirection(Direction.LEFT);

        }
        if (GameDriver.rightPressed()) {
            super.setDesiredDirection(Direction.RIGHT);

        }
        super.update();
    }

    /**
     * Draw the duck.
     *
     */
    public void draw() {
        String leftImagePath = "hws/hw8/img/duck_left.png";
        String rightImagePath = "hws/hw8/img/duck_right.png";
        String upImagePath = "hws/hw8/img/duck_up.png";
        String downImagePath = "hws/hw8/img/duck_down.png";

        Direction direction = super.getCurrentDirection();

        if (direction == direction.LEFT) {
            DuckManGame.drawImage(currentPosition, leftImagePath);
        }
        if (direction == direction.UP) {
            DuckManGame.drawImage(currentPosition, upImagePath);
        }
        if (direction == direction.RIGHT) {
            DuckManGame.drawImage(currentPosition, rightImagePath);
        }
        if (direction == direction.DOWN) {
            DuckManGame.drawImage(currentPosition, downImagePath);
        }
    }

    /**
     * will the player collide with a dot.
     *
     * @param dot the enemy to add
     * @return boolean
     */
    public boolean collidesWith(Dot dot) {
        // get the x value of the player and dot then subtract them
        // get the y value of the player and dot then subtract them
        Point current = super.getCurrentPosition();
        Point positionD = dot.getPosition();
        double distance = current.distance(positionD);
        if (distance < 0.5) {
            return true;
        }
        return false;
    }
}
