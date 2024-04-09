package hws.hw8;

/**
 * hw8 dot.
 *
 * @author Will Ponczak
 * @version 03/31/2024
 */

public class Dot implements Drawable {

    public static final int POINTS = 10;
    protected Point position;

    /**
     * Constructor.
     *
     * @param position the position of the dot
     */
    public Dot(Point position) {
        this.position = position;
    }

    /**
     * get the position.
     *
     * @return the position of the dot
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * draw method.
     *
     */
    public void draw() {
        String imagePath = "hws/hw8/img/dot.png";
        DuckManGame.drawImage(this.position, imagePath);
    }

}
