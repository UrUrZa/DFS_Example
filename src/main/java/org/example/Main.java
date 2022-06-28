package org.example;

import java.awt.*;
import java.util.HashMap;

public class Main {
        public static void main(String[] args) {
            SearchResultPool searchResults = new SearchResultPool();
               try {
                       DepthSearcher depthSearcher = new DepthSearcher(iGraphLines, searchResults);
                       depthSearcher.DepthSearch(WayPoints.A,WayPoints.J,0);
                       System.out.println(searchResults.getShortestPath().toString());
                } catch (Exception e){
                        e.printStackTrace();
                }
        }

        public static IGraphLine[] iGraphLines = {
                new IGraphLine(
                        WayPoints.A,
                        WayPoints.B1,
                        2),
                new IGraphLine(
                        WayPoints.A,
                        WayPoints.B2,
                        3),
                new IGraphLine(
                        WayPoints.B1,
                        WayPoints.C1,
                       7),
                new IGraphLine(
                        WayPoints.B2,
                        WayPoints.C1,
                        5),
                new IGraphLine(
                        WayPoints.C1,
                        WayPoints.D1,
                        9),
                new IGraphLine(
                        WayPoints.C1,
                        WayPoints.D3,
                        12),
                new IGraphLine(
                        WayPoints.D1,
                        WayPoints.D2,
                        7),
                new IGraphLine(
                        WayPoints.D3,
                        WayPoints.D2,
                5),
                new IGraphLine(
                        WayPoints.D1,
                        WayPoints.E1,
                        8),
                new IGraphLine(
                        WayPoints.D2,
                        WayPoints.E2,
                        2),
                new IGraphLine(
                        WayPoints.D3,
                        WayPoints.E3,
                        6),
                new IGraphLine(
                        WayPoints.E1,
                        WayPoints.F2,
                        17),
                new IGraphLine(
                        WayPoints.E2,
                        WayPoints.F3,
                        12),
                new IGraphLine(
                        WayPoints.E3,
                        WayPoints.F1,
                        22),
                new IGraphLine(
                        WayPoints.F1,
                        WayPoints.J,
                        8),
                new IGraphLine(
                        WayPoints.F2,
                        WayPoints.J,
                        3),
                new IGraphLine(
                        WayPoints.F3,
                        WayPoints.J,
                        6)
        };
}