package awtbreakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The blocks on the screen
 * 
 * @author Cody Moose
 * @since 2016
 */
public class Block extends JFrame
{
    /**
     * 
     */
    private static final long      serialVersionUID = -7976911463241645745L;
    /**
     * Amount of rows to be used in the game
     */
    public static int              rows             = 8;
    /**
     * Amount of columns to be used in the game
     */
    public static int              columns          = 20;
    /**
     * The size of the screen
     */
    private static final Dimension SCREEN_SIZE      = new Dimension(1600, 900);
    /**
     * The width of the blocks
     */
    private static double          BLOCK_WIDTH      = SCREEN_SIZE.width / columns;
    /**
     * The height of the blocks
     */
    private static double          BLOCK_HEIGHT     = SCREEN_SIZE.width / (3 * columns);
    /**
     * Panel that makes the block colored pretty
     */
    public JPanel                  blockPanel       = new JPanel();
    /**
     * The color of the block at the top row. Used for gradienting throughout
     * blocks.
     */
    private static final Color     START_COLOR      = Color.RED;
    /**
     * The color of the block at the bottom row. Used for gradienting throughout
     * blocks.
     */
    private static final Color     END_COLOR        = Color.GREEN;
    /**
     * Array of what the colors of the blocks in each row are
     */
    public static Color[]          BLOCK_COLORS;
    /**
     * What row is currently being painted
     */
    public int                     row              = 0;

    /**
     * Sets properties of block
     * 
     * @param column
     *            what column the block is in
     * @param row
     *            what row the block is in
     * @param superHeight
     *            the height of ScoreWindow
     */
    public Block(int inCols, int inRows, int column, int row, int superHeight)
    {
        setLocation((int) (column * BLOCK_WIDTH), (int) (superHeight + row * BLOCK_HEIGHT));
        setSize((int) BLOCK_WIDTH, (int) BLOCK_HEIGHT);
        setDefaultCloseOperation(3);
        setUndecorated(true);
        setResizable(false);
        setBackground(Color.YELLOW);
        setForeground(Color.YELLOW);
        setVisible(true);
        blockPanel.setEnabled(true);
        blockPanel.setLocation(0, 0);
        blockPanel.setForeground(Color.YELLOW);
        blockPanel.setBackground(Color.YELLOW);
        blockPanel.setVisible(true);
        add(blockPanel);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(BLOCK_COLORS[row]);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setPaint(Color.BLACK);
        g2.drawRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Sets what row is currently being created in ScoreWindow in order to paint
     * blocks accordingly
     * 
     * @param r
     *            what row is currently being created
     */
    public void setPaintRow(int r)
    {
        row = r;
    }

    public static void setCols(int cols)
    {
        columns = cols;
        BLOCK_WIDTH = SCREEN_SIZE.width / columns;
        BLOCK_HEIGHT = SCREEN_SIZE.width / (3 * columns);
    }

    public static void setRows(int inRows)
    {
        rows = inRows;
    }

    public static void setBlockColors()
    {
        BLOCK_COLORS = new Color[rows];
        float redInc = 2 * (END_COLOR.getRed() - START_COLOR.getRed())
                        / (BLOCK_COLORS.length - (BLOCK_COLORS.length % 2));
        float greenInc = 2 * (END_COLOR.getGreen() - START_COLOR.getGreen())
                        / (BLOCK_COLORS.length - (BLOCK_COLORS.length % 2));
        // float blueInc = 2 * (END_COLOR.getBlue() - START_COLOR.getBlue()) /
        // (BLOCK_COLORS.length - (BLOCK_COLORS.length % 2));
        for (int i = 0; i < BLOCK_COLORS.length; i++)
        {
            int red = START_COLOR.getRed();
            int green = START_COLOR.getGreen();
            int blue = START_COLOR.getBlue();
            green = (int) (START_COLOR.getGreen() + i * greenInc);
            if (i > (BLOCK_COLORS.length - (BLOCK_COLORS.length % 2)) / 2)
            {
                red = (int) (START_COLOR.getRed()
                                + (i - ((BLOCK_COLORS.length - (BLOCK_COLORS.length % 2)) / 2)) * redInc);
            }
            if (green > 255) green = 255;
            if (green < 0) green = 0;
            if (red > 255) red = 255;
            if (red < 0) red = 0;
            BLOCK_COLORS[i] = new Color(red, green, blue);
        }
    }
}
