package com.harpy.pathfindingapi.service;

public class Knight
{
    private int[] coordinates;
    private static int[][] moveSet = {{1,2}, {2,1}, {-1,-2}, {-2,-1}, {1,-2}, {2,-1}, {-1,2}, {-2,1}};
    public Knight(int[] coordinates)
    {
        this.coordinates = coordinates;
    }

    public int[] getCoordinates()
    {
        return this.coordinates;
    }

    public static int[][] getMoveSet()
    {
        return moveSet;
    }
}