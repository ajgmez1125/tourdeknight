package com.harpy.pathfindingapi.service;

import java.util.ArrayList;
import java.util.HashMap;

public class Board
{
    private int x;
    private int y;
    private ArrayList<int[]> squares = new ArrayList<int[]>();
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
                squares.add(new int[] {x,y});
            }
        }
    }

    public HashMap<String, Integer> generateObjAdjacencyMap(Knight knight)
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

    public ArrayList<int[]> getSquares()
    {
        return this.squares;
    }
}