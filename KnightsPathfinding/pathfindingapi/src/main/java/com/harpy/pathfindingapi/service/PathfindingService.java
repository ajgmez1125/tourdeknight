package com.harpy.pathfindingapi.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public class PathfindingService
{
    public PathfindingService()
    {

    }

    public HashMap<String, Integer> find(int[] knightPosition, int boardX, int boardY)
    {
        Knight knight = new Knight(knightPosition);
        return new Board(boardX, boardY).generateObjAdjacencyMap(knight);
    }
}
