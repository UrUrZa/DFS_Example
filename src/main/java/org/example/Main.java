package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
        public static HashMap<WayPoints, Color> pointsColorMap = new HashMap();
        public static void main(String[] args) {
                populeitPointsColor();
                System.out.println("Hello world!");
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
        public static void populeitPointsColor() {
                for (WayPoints point : WayPoints.values()) {
                        pointsColorMap.put(point, Color.WHITE);
                }
        }
        public static int DepthSearch(WayPoints current, WayPoints finish, int distans){
                boolean isFinished = current.equals(finish);
        if (isFinished) {
                return distans;
        } else {
                List<WayPoints> tmpList = getWhiteWayPoints(current);

                for (WayPoints point:tmpList) {
                        int increaedDistance = distans + getDistans(current,point);
                      return DepthSearch(point, finish, increaedDistance);
                 }
        }
        return distans;
        }
        public static List<WayPoints> getWayPoints(WayPoints current){
                List<WayPoints> result = new ArrayList();
                for (IGraphLine iGraphLine: iGraphLines) {
                        boolean isPointEx1 = iGraphLine.point1==current;
                        boolean isPointEx2 = iGraphLine.point2==current;
                        if (isPointEx1){result.add(iGraphLine.point1);}
                        if (isPointEx2){result.add(iGraphLine.point2);}
                }
                return result;
        }
        public static List<WayPoints> getWhiteWayPoints(WayPoints current){
                List<WayPoints> result = getWayPoints(current);
                result.stream().filter(point -> pointsColorMap.get(point).equals(Color.WHITE)).toList();
                return result;
        }
        public static int getDistans(WayPoints a, WayPoints b){
               for (IGraphLine iGraphLine: iGraphLines) {
                        boolean isPointEx1 = (iGraphLine.point1==a)||(iGraphLine.point1==b);
                        boolean isPointEx2 = (iGraphLine.point2==a)||(iGraphLine.point2==b);
                        boolean isDirectHit = isPointEx1&&isPointEx2;
                        if (isDirectHit){return iGraphLine.distans;}
                }
                return 0;
        }
}