package hws.hw8;

/**
 * hw8 MagicEgg.
 *
 * @author Will Ponczak
 * @version 03/31/2024
 */
public class MagicEgg extends Dot {

    public static final int POINTS = 50;

    /**
     * constructor.
     *
     * @param position position of egg.
     */
    public MagicEgg(Point position) {
        super(position);
    }

    /**
     * draw.
     *
     */
    public void draw() {
        String imagePath = "hws/hw8/img/egg.png";
        DuckManGame.drawImage(position, imagePath);
    }
}
