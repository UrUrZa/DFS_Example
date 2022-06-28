package org.example;


import java.util.ArrayList;
import java.util.List;

public class SearchResultPool {
    protected List<PathSerchResult> searchResults = new ArrayList<>();

    public SearchResultPool() {

    }
    public void add(PathSerchResult pathSearchResult){
        searchResults.add(pathSearchResult);
    }
    @Override
    public String toString(){
        String result = "";
        for (PathSerchResult pathResult: searchResults){
             result = result + pathResult.toString() +'\n';

        }
        return result;
    }
    public PathSerchResult getShortestPath(){
        if (searchResults.size() == 0){
            return null;
        }
        PathSerchResult shortestPath = searchResults.get(0);
        for (PathSerchResult pathSerch: searchResults) {
            if (shortestPath.distansPath > pathSerch.distansPath){
                shortestPath = pathSerch;
            }
        }
        return shortestPath;
    }
}
