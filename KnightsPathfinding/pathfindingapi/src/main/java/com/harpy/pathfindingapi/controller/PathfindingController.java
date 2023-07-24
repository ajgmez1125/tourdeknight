package com.harpy.pathfindingapi.controller;
import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harpy.pathfindingapi.service.PathfindingService;

@RestController
@RequestMapping(path = "/pathfind")
public class PathfindingController
{
    private final PathfindingService pathfindingService;

    public PathfindingController(PathfindingService pathfindingService)
    {
        this.pathfindingService = pathfindingService;
    }

    @GetMapping("/find")
    public HashMap<Integer, Integer> pathfind(@RequestParam int[] knight, int x, int y)
    {
        return pathfindingService.find(knight, x, y);
    }
}
