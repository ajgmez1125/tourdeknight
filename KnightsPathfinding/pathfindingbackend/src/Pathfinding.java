import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Pathfinding
{
    private HashMap<Integer, ArrayList<Square>> adjMap = new HashMap<Integer, ArrayList<Square>>();
    private HashMap<Square, Boolean> checkList = new HashMap<Square, Boolean>();
    private ArrayList<Square> searchQueue = new ArrayList<Square>();
    private Board board;
    private Knight knight;

    public Pathfinding(Board board, Knight knight)
    {
        this.knight = knight;
        this.board = board;
    }

    private void printAdjMap()
    {
        for(int i = 0; i < adjMap.size(); i++)
        {
            System.out.println(i + ": " + printList(adjMap.get(i)));
        }
    }
    

    public HashMap<Integer, ArrayList<Square>> generate()
    {
        //Adds the init position of knight to search
        searchQueue.add(getSquareFromBoard(knight.getCoordinates()));
        checkList.put(searchQueue.get(0), true);
        int depth = 0;
        while(searchQueue.size() > 0)
        {
            giveDepth(depth);
            findNextDepth();
            depth++;
        }
        return adjMap;
    }

    //Finds all adjacencies for given depth
    private void findNextDepth()
    {
        ArrayList<Square> nextDepth = new ArrayList<Square>();
        for(int i = 0; i < searchQueue.size(); i++)
        {
            nextDepth.addAll(getAdjacentSquares(searchQueue.get(i)));
        }
        searchQueue.clear();
        if(!nextDepth.isEmpty())
        {
            searchQueue.addAll(nextDepth);
        }
    }

    //Finds specific adjacencies for given square
    private ArrayList<Square> getAdjacentSquares(Square square)
    {
        ArrayList<Square> currentAdjacencies = new ArrayList<Square>();
        int[] currentMove = square.getCoordinates();
        int[] newMove = new int[2];
        Square newSquare;
        int[][] knightMoves = knight.getMoveSet();
        for(int i = 0; i < knightMoves.length; i++)
        {
            currentMove = square.getCoordinates();
            newMove[0] = currentMove[0] + knightMoves[i][0];
            newMove[1] = currentMove[1] + knightMoves[i][1];
            if(isValid(newMove) && isUnexplored(newMove))
            {
                newSquare = getSquareFromBoard(newMove);
                currentAdjacencies.add(newSquare);
                checkList.put(newSquare, true);
            }
        }
        return currentAdjacencies;
    }

    private boolean isValid(int[] coordinates)
    {
        boolean overflows = coordinates[0] > board.getX() || coordinates[1] > board.getY();
        boolean underflows = coordinates[0] < 1 || coordinates[1] < 1 ;
        if(!overflows && !underflows)
        {
            return true;
        }
        return false;
    }

    public boolean isUnexplored(int[] currentMove)
    {
        Square currentSquare = getSquareFromBoard(currentMove);
        if(!checkList.containsKey(currentSquare) || checkList.get(currentSquare) == false)
        {
            return true;
        }
        return false;
    }

    private void giveDepth(int depth)
    {
        System.out.println(depth + ": " + printList(this.searchQueue));
        adjMap.put(depth, this.searchQueue);
    }

    private Square getSquareFromBoard(int[] coordinates)
    {
        int index = coordinatesToNum(coordinates) - 1;
        if(index >= board.getSquares().size() || index < 0)
        {
            return null;
        }
        return board.getSquares().get(index);
    }


    private int coordinatesToNum(int[] coord)
    {
        if(coord[1] == 1)
        {
            return coord[0];
        }
        return (this.board.getY() * (coord[1] - 1)) + coord[0];
    }

    private String printList(ArrayList<Square> list)
    {
        String str = "";
        for(int i = 0; i < list.size(); i++)
        {
            str += Arrays.toString(list.get(i).getCoordinates()) + ", "; 
        }
        return str;
    }
}