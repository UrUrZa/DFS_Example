package org.example;

import java.util.ArrayList;
import java.util.List;

public class PathSerchResult {
    public List<WayPoints> Path = new ArrayList<>();
    public Integer distansPath = 0;

    public PathSerchResult(List<WayPoints> path, Integer distansPath) {
        Path = path;
        this.distansPath = distansPath;
    }
}
