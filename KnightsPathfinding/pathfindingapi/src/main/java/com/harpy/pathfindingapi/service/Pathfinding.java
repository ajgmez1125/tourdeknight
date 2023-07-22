package com.harpy.pathfindingapi.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Pathfinding
{
    private HashMap<Integer, ArrayList<int[]>> adjMap = new HashMap<Integer, ArrayList<int[]>>();
    private HashMap<int[], Boolean> checkList = new HashMap<int[], Boolean>();
    private ArrayList<int[]> searchQueue = new ArrayList<int[]>();
    private Board board;
    private Knight knight;

    public Pathfinding(Board board, Knight knight)
    {
        this.knight = knight;
        this.board = board;
    }
    

    public HashMap<Integer, ArrayList<int[]>> generate()
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
        System.out.println(adjMap);
        return adjMap;
    }

    //Finds all adjacencies for given depth
    private void findNextDepth()
    {
        ArrayList<int[]> nextDepth = new ArrayList<int[]>();
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
    private ArrayList<int[]> getAdjacentSquares(int[] square)
    {
        ArrayList<int[]> currentAdjacencies = new ArrayList<int[]>();
        int[] newMove = new int[2];
        int[] newSquare;
        int[][] knightMoves = Knight.getMoveSet();
        for(int i = 0; i < knightMoves.length; i++)
        {
            newMove[0] = square[0] + knightMoves[i][0];
            newMove[1] = square[1] + knightMoves[i][1];
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
        int[] currentSquare = getSquareFromBoard(currentMove);
        if(!checkList.containsKey(currentSquare) || checkList.get(currentSquare) == false)
        {
            return true;
        }
        return false;
    }

    private void giveDepth(int depth)
    {
        adjMap.put(depth, new ArrayList<int[]>(searchQueue));
        System.out.println(depth + ": " + printList(searchQueue));
    }

    private int[] getSquareFromBoard(int[] coordinates)
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

    private String printList(ArrayList<int[]> list)
    {
        String str = "";
        for(int i = 0; i < list.size(); i++)
        {
            str += Arrays.toString(list.get(i)) + ", "; 
        }
        return str;
    }
}