package awtbreakout;

public class BlockBool
{
    @SuppressWarnings("null")
    public boolean bool   = (Boolean) null;
    public Block   block  = null;
    public int     column = 0;
    public int     row    = 0;

    public BlockBool(boolean inBool, int inCol, int inRow)
    {
        bool = inBool;
        column = inCol;
        row = inRow;
    }

    @SuppressWarnings("null")
    public BlockBool(boolean inBool)
    {
        bool = inBool;
        column = (Integer) null;
        row = (Integer) null;
    }

    public boolean getBool()
    {
        return bool;
    }

    public Block getBlock()
    {
        return block;
    }

    public int getCol()
    {
        return column;
    }

    public int getRow()
    {
        return row;
    }
}
