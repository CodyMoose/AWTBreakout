package awtbreakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Window that holds the game. Holds paddle, ball, blocks, and score. Closing it
 * will close program.
 * 
 * @author Cody Moose
 * @since 2016
 * @version 1.0
 */
public class ScoreWindow extends JFrame implements KeyListener
{
    /**
     * 
     */
    private static final long      serialVersionUID     = -6831488356359858965L;
    /**
     * Panel which holds scores
     */
    private JPanel                 scorePanel           = new JPanel();
    /**
     * Integer value that keeps Player's score for displaying
     */
    private int                    score                = 0;
    /**
     * Starting amount of lives
     */
    private int                    START_LIVES          = 10;
    /**
     * Amount of lives
     */
    private int                    lives                = START_LIVES;
    /**
     * Label to indicate which number is the score
     */
    private Label                  playerLbl            = new Label("Score");
    /**
     * Label to indicate which number is the amount of lives
     */
    private Label                  livesLbl             = new Label("Lives");
    /**
     * Label to display Player's score
     */
    private Label                  scoreLbl             = new Label("0");
    /**
     * Label to display amount of lives
     */
    private Label                  livesNumLbl          = new Label(Integer.toString(lives));
    /**
     * Window for Player 1's paddle(the red one)
     */
    private Paddle                 playerPaddle         = new Paddle();
    /**
     * Window for ball
     */
    private final Ball             ball                 = new Ball();
    /**
     * Dimension for screen size used for game borders
     */
    private static final Dimension SCREEN_SIZE          = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Height element of screen size, used to keep from calling getHeight()
     * function of the screen size dimension every time
     */
    private static final double    SCREEN_HEIGHT        = SCREEN_SIZE.getHeight();
    /**
     * Width element of screen size, used to keep from calling getWidth()
     * function of the screen size dimension every time
     */
    private static final double    SCREEN_WIDTH         = SCREEN_SIZE.getWidth();
    /**
     * Maximum angle from the horizontal ball can move at
     */
    private static final double    MAX_ANGLE            = 150;
    /**
     * Minimum angle from the horizontal ball can move at
     */
    private static final double    MIN_ANGLE            = 30;
    /**
     * Multiplier used to control ball speed, increasing as time goes on for
     * increased difficulty
     */
    public static double           speedMultiplier      = 10;
    /**
     * Constant used to keep ball from going too fast
     */
    public static final double     MAX_SPEED_MULTIPLIER = 40;
    /**
     * Constant used to keep ball from going too slow
     */
    public static final double     MIN_SPEED_MULTIPLIER = 10;
    /**
     * Boolean used to determine if "A" key is being held
     */
    private static boolean         leftHeld             = false;
    /**
     * Boolean used to determine if "Z" key is being held
     */
    private static boolean         rightHeld            = false;
    /**
     * The time at which the game was started in miliseconds. Used for refresh
     * rate.
     */
    private static final long      START_TIME           = System.currentTimeMillis();
    /**
     * The optimal fps to keep the game running at.
     */
    private static final int       OPTIMAL_FPS          = 60;
    /**
     * Amount of miliseconds between each frame at optimal frame rate
     */
    private static final double    SKIP_TICKS           = 1000 / OPTIMAL_FPS;
    /**
     * The time at which the next game tick should occur
     */
    private static double          nextGameTick         = START_TIME + SKIP_TICKS;
    /**
     * How many ticks to sleep each cycle to keep with the optimal FPS
     */
    private static double          sleepTicks           = 0;
    /**
     * Y-coordinate for bottom of this frame for reflection purposes
     */
    private static int             thisBottom           = 100;
    /**
     * Boolean to determine if ball has been launched yet
     */
    private static boolean         isLaunched           = false;
    /**
     * Boolean to determine whether or not ball is on paddle and ready for
     * launch
     */
    private static boolean         readyForLaunch       = true;
    /**
     * Array of blocks for ball to hit
     */
    public Block[][]               blocks;
    /**
     * Array of booleans respective to each block to determine if collision can
     * occur with that block
     */
    public boolean[][]             enabledBlocks;
    /**
     * Label to indicate intellectual property rights held by Robert Cody Moose
     */
    public static Label            copyrightLbl         = new Label("©Cody Moose, 2016");
    public static int columns = 20;
    public static int rows = 8;

    /**
     * Sets properties of ScoreWindow
     */
    public ScoreWindow(int inCols, int inRows)
    {
        columns = inCols;
        rows = inRows;
        blocks = new Block[columns][rows];
        enabledBlocks = new boolean[columns][rows];
        setTitle("AWT Pong");
        setSize((int) SCREEN_WIDTH, (int) SCREEN_HEIGHT / 15);
        setDefaultCloseOperation(3);
        setLocation(0, 0);
        setUndecorated(true);
        setOpacity(0.75f);
        setBackground(new Color(0, 0, 0));
        setAlwaysOnTop(true);
        setBackground(Color.BLACK);
        setVisible(true);
        scorePanel.setVisible(true);
        playerPaddle.setPositionInfo();
        setResizable(false);
        playerLbl.setForeground(Color.GREEN);
        scoreLbl.setForeground(Color.WHITE);
        livesLbl.setForeground(Color.RED);
        livesNumLbl.setForeground(Color.WHITE);
        copyrightLbl.setForeground(Color.WHITE);
        playerLbl.setBackground(Color.BLACK);
        livesLbl.setBackground(Color.BLACK);
        scoreLbl.setBackground(Color.BLACK);
        livesNumLbl.setBackground(Color.BLACK);
        copyrightLbl.setBackground(Color.BLACK);
        Font font = new Font("Comic Sans MS", 1, 32);
        playerLbl.setFont(font);
        livesLbl.setFont(font);
        scoreLbl.setFont(font);
        livesNumLbl.setFont(font);
        Font cpFont = new Font("Comic Sans MS", 1, 12);
        copyrightLbl.setFont(cpFont);
        add(playerLbl);
        add(scoreLbl);
        add(livesNumLbl);
        add(livesLbl);
        add(copyrightLbl);
        playerLbl.setVisible(true);
        livesLbl.setVisible(true);
        scoreLbl.setVisible(true);
        livesNumLbl.setVisible(true);
        copyrightLbl.setVisible(true);
        playerLbl.setAlignment(Label.RIGHT);
        scoreLbl.setAlignment(Label.CENTER);
        livesNumLbl.setAlignment(Label.CENTER);
        livesLbl.setAlignment(Label.LEFT);
        copyrightLbl.setAlignment(Label.CENTER);
        playerLbl.setBounds((int) (SCREEN_WIDTH / 2 - 250), 0, 200, getHeight());
        scoreLbl.setBounds(playerLbl.getX() + playerLbl.getWidth(), 0, 50, getHeight());
        livesNumLbl.setBounds(scoreLbl.getX() + scoreLbl.getWidth(), 0, 50, getHeight());
        livesLbl.setBounds(livesNumLbl.getX() + livesNumLbl.getWidth(), 0, 200, getHeight());
        copyrightLbl.setBounds((int) (SCREEN_SIZE.getWidth() - 150), 0, 150, getHeight());
        addKeyListener(this);
        thisBottom = getY() + getHeight();
        scorePanel.setBackground(new Color(0, 0, 0));
        add(scorePanel);
        setVisible(true);
        Block.setCols(inCols);
        Block.setRows(inRows);
        Block.setBlockColors();
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                blocks[c][r] = new Block(columns, rows, c, r, getHeight());
                blocks[c][r].setVisible(true);
                enabledBlocks[c][r] = true;
                blocks[c][r].setBackground(Block.BLOCK_COLORS[r]);
                blocks[c][r].setForeground(Block.BLOCK_COLORS[r]);
                int row = r;
                blocks[c][r].setPaintRow(row);
                blocks[c][r].row = row;
                blocks[c][r].repaint();
            }
        }
    }

    @Override
    public void paint(Graphics g)
    {
        requestFocus();
        ball.setPositionInfo();
        playerPaddle.setPositionInfo();
        super.paint(g);
        if (checkCollisionPaddle() && !readyForLaunch)
        {
            doCollisionPaddle();
        }
        if (readyForLaunch)
        {
            ball.deltaX = 0;
            ball.deltaY = 0;
            ball.setTrueLocation(playerPaddle.middleX - ball.getWidth() / 2, playerPaddle.top - ball.getHeight());
        }
        if (leftHeld) if (!rightHeld) playerPaddle.paddleLeft();
        if (rightHeld) if (!leftHeld) playerPaddle.paddleRight();
        for (int r = 0; r < Block.rows; r++)
        {
            for (int c = 0; c < Block.columns; c++)
            {
                if (ball != null && blocks[c][r] != null && ball.getBounds().intersects(blocks[c][r].getBounds())
                                && blocks[c][r].isVisible())
                {
                    blocks[c][r].setVisible(false);
                    enabledBlocks[c][r] = false;
                    doBlockCollision(blocks[c][r]);
                    c = Block.columns + 1;
                    r = Block.rows + 1;
                }
            }
        }
        if (ball.bottom >= SCREEN_HEIGHT - 40 || ball.top <= getY() + getHeight())
        {
            if (ball.bottom >= SCREEN_HEIGHT - 40)
            {
                ball.setTrueY(((SCREEN_HEIGHT - 40) - ball.getHeight()) - 1);
                ball.deltaX = 0;
                ball.deltaY = 0;
                readyForLaunch = true;
                isLaunched = false;
                speedMultiplier = MIN_SPEED_MULTIPLIER;
                lives--;
                livesNumLbl.setText(Integer.toString(lives));
            }
            else if (ball.top <= thisBottom) ball.setTrueY(thisBottom + 1);
            ball.deltaY = -1 * ball.deltaY;
        }
        ball.move();
        if (ball.right >= SCREEN_WIDTH)
        {
            ball.deltaX = -1 * ball.deltaX;
            ball.setTrueX(SCREEN_WIDTH - ball.getWidth() - 1);
        }
        if (ball.left <= 0)
        {
            ball.deltaX = -1 * ball.deltaX;
            ball.setTrueX(1);
        }
        sleepUntilNextFrame();
        repaint();
    }

    public void keyTyped(KeyEvent e)
    {
    }

    public void keyPressed(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                leftHeld = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightHeld = true;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(1);
                break;
            case KeyEvent.VK_SPACE:
                if (lives > 0) launch();
                break;
            case KeyEvent.VK_R:
                restart();
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                leftHeld = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightHeld = false;
                break;
        }
    }

    /**
     * Checks for a collision with the paddle
     * 
     * @return whether or not the ball is colliding with the paddle
     */
    private boolean checkCollisionPaddle()
    {
        return ball.getBounds().intersects(playerPaddle.getBounds());
    }

    /**
     * Performs collision with paddle
     */
    private void doCollisionPaddle()
    {
        ball.reflectionAngle = MAX_ANGLE * (ball.middleX - playerPaddle.left) / (playerPaddle.getWidth());
        if (ball.reflectionAngle > MAX_ANGLE)
        {
            ball.reflectionAngle = MAX_ANGLE;
        }
        else if (ball.reflectionAngle < MIN_ANGLE) ball.reflectionAngle = MIN_ANGLE;
        speedMultiplier += 3;
        ball.deltaX = -1 * speedMultiplier * Math.cos(Math.toRadians(ball.reflectionAngle));
        ball.deltaY = -1 * speedMultiplier * Math.sin(Math.toRadians(ball.reflectionAngle));
        if (speedMultiplier > MAX_SPEED_MULTIPLIER)
        {
            speedMultiplier = MAX_SPEED_MULTIPLIER;
        }
        ball.player1Hit = !ball.player1Hit;
        ball.repaint();
        ball.setTrueY(playerPaddle.top - 1 - ball.getHeight());
    }

    /*-
    private BlockBool checkCollisionBlock()
    {
        boolean colliding = false;
        int checkRow = 0;
        int checkCol = 0;
        for (int r = 0; r < Block.rows;)
        {
            for (int c = 0; c < Block.columns;)
            {
                if (ball != null)
                {
                    if (blocks[c][r] != null)
                    {
                        if (ball.getBounds().intersects(blocks[c][r].getBounds()))
                        {
                            colliding = true;
                            checkRow = r;
                            checkCol = c;
                            c = Block.columns + 1;
                            r = Block.rows + 1;
                        }
                    }
                }
            }
        }
        return new BlockBool(colliding, checkCol, checkRow);
    }
    */
    /**
     * Performs the collision between the ball and the block
     * 
     * @param block
     *            which block in blocks[r][c] to perform collision with
     */
    private void doBlockCollision(Block block)
    {
        Rectangle topRect = new Rectangle(block.getX(), block.getY() - 2, block.getWidth(), 2);
        Rectangle bottomRect = new Rectangle(block.getX(), block.getY() + block.getHeight(), block.getWidth(), 2);
        Rectangle leftRect = new Rectangle(block.getX() - 2, block.getY() - 2, 2, block.getHeight());
        Rectangle rightRect = new Rectangle(block.getX() + block.getWidth(), block.getY() - 2, 2, block.getHeight());
        boolean top = ball.getBounds().intersects(topRect.getBounds());
        boolean bottom = ball.getBounds().intersects(bottomRect.getBounds());
        boolean left = ball.getBounds().intersects(leftRect.getBounds());
        boolean right = ball.getBounds().intersects(rightRect.getBounds());
        if (top != bottom && left == right)
        {
            ball.deltaY = -1 * ball.deltaY;
        }
        if (top == bottom && left != right)
        {
            ball.deltaX = -1 * ball.deltaX;
        }
        if (top != bottom && left != right)
        {
            double topInter = Math.abs(block.getY() - ball.bottom);
            double bottomInter = Math.abs((block.getY() + block.getHeight()) - ball.top);
            double leftInter = Math.abs(block.getX() - ball.right);
            double rightInter = Math.abs((block.getX() + block.getWidth()) - ball.left);
            if (top && left)
            {
                if (topInter < leftInter) ball.deltaY *= -1;
                else if (leftInter < topInter) ball.deltaX *= -1;
                else
                {
                    ball.deltaX *= -1;
                    ball.deltaY *= -1;
                }
            }
            else if (top && right)
            {
                if (topInter < rightInter) ball.deltaY *= -1;
                else if (rightInter < topInter) ball.deltaX *= -1;
                else
                {
                    ball.deltaX *= -1;
                    ball.deltaY *= -1;
                }
            }
            else if (bottom && left)
            {
                if (bottomInter < leftInter) ball.deltaY *= -1;
                else if (leftInter < bottomInter) ball.deltaX *= -1;
                else
                {
                    ball.deltaX *= -1;
                    ball.deltaY *= -1;
                }
            }
            else if (bottom && right)
            {
                if (bottomInter < rightInter) ball.deltaY *= -1;
                else if (rightInter < bottomInter) ball.deltaX *= -1;
                else
                {
                    ball.deltaX *= -1;
                    ball.deltaY *= -1;
                }
            }
        }
        score++;
        scoreLbl.setText(Integer.toString(score));
    }

    /**
     * Sleeps until the next frame in order to keep with the 'optimal' fps
     */
    private void sleepUntilNextFrame()
    {
        nextGameTick += SKIP_TICKS;
        sleepTicks = nextGameTick - System.currentTimeMillis();
        if (sleepTicks >= 0)
        {
            try
            {
                Thread.sleep((long) sleepTicks);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Launches ball from paddle in direction based on the ball's position
     * relative to the center of the screen
     */
    private void launch()
    {
        if (!isLaunched)
        {
            ball.reflectionAngle = 180 * (getWidth() - ball.middleX) / (getWidth());
            if (ball.reflectionAngle > MAX_ANGLE)
            {
                ball.reflectionAngle = MAX_ANGLE;
            }
            else if (ball.reflectionAngle < MIN_ANGLE) ball.reflectionAngle = MIN_ANGLE;
            speedMultiplier = MIN_SPEED_MULTIPLIER;
            ball.deltaX = speedMultiplier * Math.cos(Math.toRadians(ball.reflectionAngle));
            ball.deltaY = -1 * speedMultiplier * Math.sin(Math.toRadians(ball.reflectionAngle));
            readyForLaunch = false;
            isLaunched = true;
        }
    }

    private void restart()
    {
        for (int r = 0; r < Block.rows; r++)
        {
            for (int c = 0; c < Block.columns; c++)
            {
                blocks[c][r].dispose();
                blocks[c][r] = null;
            }
        }
        lives = START_LIVES;
        score = 0;
        for (int r = 0; r < rows; r++)
        {
            for (int c = 0; c < columns; c++)
            {
                blocks[c][r] = new Block(columns, rows, c, r, getHeight());
                blocks[c][r].setVisible(true);
                enabledBlocks[c][r] = true;
                blocks[c][r].setBackground(Block.BLOCK_COLORS[r]);
                blocks[c][r].setForeground(Block.BLOCK_COLORS[r]);
                int row = r;
                blocks[c][r].setPaintRow(row);
                blocks[c][r].row = row;
                blocks[c][r].repaint();
            }
        }
        ball.setTrueY(((SCREEN_HEIGHT - 40) - ball.getHeight()) - 1);
        ball.deltaX = 0;
        ball.deltaY = 0;
        readyForLaunch = true;
        isLaunched = false;
        speedMultiplier = MIN_SPEED_MULTIPLIER;
        livesNumLbl.setText(Integer.toString(lives));
    }
}