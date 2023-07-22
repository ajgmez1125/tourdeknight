package com.harpy.pathfindingapi.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class PathfindingService
{
    public PathfindingService()
    {

    }

    public HashMap<Integer, ArrayList<int[]>> find(int[] knightPosition, int boardX, int boardY)
    {
        return new Board(boardX, boardY).generateObjAdjacencyMap(new Knight(knightPosition));
    }
}
