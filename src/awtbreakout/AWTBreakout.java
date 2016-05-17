package awtbreakout;

public class AWTBreakout
{
    public static int superCols = 20;
    public static int superRows = 8;
    public static ScoreWindow game;

    public static void main(String[] args)
    {
        new StartWindow();
    }

    public static int getSuperCols()
    {
        return superCols;
    }

    public static int getSuperRows()
    {
        return superRows;
    }

    public static void setSuperCols(int cols)
    {
        superCols = cols;
    }

    public static void setSuperRows(int rows)
    {
        superRows = rows;
    }
    
    public static void setGame(int cols, int rows){
        game = new ScoreWindow(cols, rows);
    }
}