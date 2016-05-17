package awtbreakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window that holds the Paddle and all its info
 * 
 * @author Cody Moose
 * @since 2016
 * @version 1.0
 */
public class Paddle extends JFrame
{
    /**
     * 
     */
    private static final long      serialVersionUID = 9168092458364571287L;
    /**
     * Color for player 1(left) paddle. Currently red
     */
    public static final Color      P1_PADDLE_COLOR    = new Color(255, 0, 0);
    /**
     * Color for player 1(right) paddle. Currently green
     */
    public static final Color      P2_PADDLE_COLOR    = new Color(0, 255, 0);
    /**
     * Left X coordinate of Paddle. Used for determining collisions
     */
    public double                  left             = getX();
    /**
     * Right X coordinate of Paddle. Used for determining collisions
     */
    public double                  right            = getX() + getWidth();
    /**
     * Top Y coordinate of Paddle. Used for determining collisions
     */
    public double                  top              = getY();
    /**
     * Bottom Y coordinate of Paddle. Used for determining collisions
     */
    public double                  bottom           = getY() + getHeight();
    /**
     * Middle Y coordinate of the Paddle. Used for determining paddle reflection
     * angles
     */
    public double                  middleY          = getY() + getHeight() / 2;
    /**
     * Middle Y coordinate of the Ball. Used for determining paddle reflection angles
     */
    public double             middleX          = getX() + getWidth() / 2;
    /**
     * Dimension for screen size used for game borders
     */
    private static final Dimension SCREEN_SIZE       = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Maximum distance a paddle may move each frame
     */
    private static final int PADDLE_MOVE_DISTANCE = 40;

    /**
     * Sets properties of Paddle window
     * 
     * @param player1
     *            Indicates whether paddle is for Player 1 or Player 2, and sets
     *            position and color accordingly
     */
    public Paddle()
    {
        JPanel panel = new JPanel();
        setSize(16 * SCREEN_SIZE.width / 109, (16 * SCREEN_SIZE.width/654));
        add(panel);
        setUndecorated(true);
        setVisible(true);
        setLocation(SCREEN_SIZE.width / 2 - getWidth()/2, SCREEN_SIZE.height - 40 - (SCREEN_SIZE.height / 90) - getHeight());
        panel.setBackground(Color.BLUE);
    }

    /**
     * Brings paddle right.
     */
    public void paddleRight()
    {
        if(right + PADDLE_MOVE_DISTANCE <= SCREEN_SIZE.width){
        setLocation(getX() + PADDLE_MOVE_DISTANCE, getY());
        } else setLocation((int) (SCREEN_SIZE.width - getWidth()), getY());
    }

    /**
     * Brings paddle left.
     */
    public void paddleLeft()
    {
        if(getX() -  PADDLE_MOVE_DISTANCE >= 0){
        setLocation(getX() - PADDLE_MOVE_DISTANCE, getY());
        } else setLocation(0 , getY());
    }

    /**
     * Sets information about position of paddle, such as top, bottom, and
     * middle y-coordinates, and left and right x-coordinates
     */
    public void setPositionInfo()
    {
        middleY = getY() + getHeight() / 2;
        top = getY();
        bottom = getY() + getHeight();
        left = getX();
        right = getX() + getWidth();
        middleX = getX() + getWidth() / 2;
    }
}
