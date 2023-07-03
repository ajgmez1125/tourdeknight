import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Board
{
    private int x;
    private int y;
    private ArrayList<Square> squares = new ArrayList<Square>();
    public Board(int x, int y)
    {
        this.x = x;
        this.y = y;
        generateBoard();
    }

    private void generateBoard()
    {
        for(int y = 1; y <= this.y; y++)
        {
            for(int x = 1; x <= this.x; x++)
            {
                squares.add(new Square(new int[] {x,y}));
            }
        }
    }

    public HashMap<Integer, ArrayList<Square>> generateObjAdjacencyMap(Knight knight)
    {
        return new Pathfinding(this, knight).generate();
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public ArrayList<Square> getSquares()
    {
        return this.squares;
    }
}