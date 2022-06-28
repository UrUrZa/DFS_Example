package org.example;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepthSearcher {
    private HashMap<WayPoints, PointColor> pointsColorMap = new HashMap();
    private IGraphLine[] iGraphLines = new IGraphLine[]{};
    private List<WayPoints> currentWay = new ArrayList<>();
    private List<PathSerchResult> serchResults;
    public DepthSearcher(IGraphLine[] iGraphLines, List<PathSerchResult> serchResults) {
        this.iGraphLines = iGraphLines;
        this.serchResults = serchResults;
        populeitPointsColor();
    }
    protected DepthSearcher(IGraphLine[] iGraphLines, HashMap<WayPoints, PointColor> pointsColorMap, List<WayPoints> currentWay, List<PathSerchResult> serchResults) {
        this.iGraphLines = iGraphLines;
        this.pointsColorMap =pointsColorMap;
        this.currentWay = currentWay;
        this.serchResults = serchResults;
    }

    public void DepthSearch(WayPoints current, WayPoints finish, Integer distans){
        boolean isFinished = current.equals(finish);
        boolean wayNotExists = getWhiteWayPoints(current).size()==0;
        if (isFinished) {
            serchResults.add(new PathSerchResult(currentWay, distans));
            System.out.print(current+" -> ");
            //return distans;
        } else if (wayNotExists){
            System.out.println(currentWay.toString() + " -> No way");
           // return null;
        } else {
            List<WayPoints> ClosesWhitePoints = getWhiteWayPoints(current);
            paintPointBlack(current);
            addCurrentToPointCurrentWay(current);
            System.out.print(currentWay.toString()+" -> ");
            for (WayPoints point:ClosesWhitePoints) {
                HashMap<WayPoints, PointColor> pointsColorMap2 = new HashMap(pointsColorMap);
                ArrayList<WayPoints>mayCurrentWay= new ArrayList<>(currentWay);
                DepthSearcher depthSearcher = new DepthSearcher(iGraphLines, pointsColorMap2, mayCurrentWay, serchResults);
                if (distans != null) {
                    int incresedDistance = distans + getDistans(current, point);
                    depthSearcher.DepthSearch(point, finish, incresedDistance);
                }
            }

        }
    }
    protected List<WayPoints> getWayPoints(WayPoints current){
        List<WayPoints> result = new ArrayList();
        for (IGraphLine iGraphLine: iGraphLines) {
            boolean isPointEx1 = iGraphLine.point1==current;
            boolean isPointEx2 = iGraphLine.point2==current;
            if (isPointEx1){result.add(iGraphLine.point2);}
            if (isPointEx2){result.add(iGraphLine.point1);}
        }
        return result;
    }
    protected List<WayPoints> getWhiteWayPoints(WayPoints current) {
        List<WayPoints> points = getWayPoints(current);
        List<WayPoints> result = points.stream().filter(point -> pointsColorMap.get(point).equals(PointColor.WHITE)).toList();
        return result;
    }
    protected int getDistans(WayPoints a, WayPoints b){
        for (IGraphLine iGraphLine: iGraphLines) {
            boolean isPointEx1 = (iGraphLine.point1==a)||(iGraphLine.point1==b);
            boolean isPointEx2 = (iGraphLine.point2==a)||(iGraphLine.point2==b);
            boolean isDirectHit = isPointEx1&&isPointEx2;
            if (isDirectHit){return iGraphLine.distans;}
        }
        return 0;
    }
    protected void populeitPointsColor() {
        for (WayPoints point : WayPoints.values()) {
            pointsColorMap.put(point, PointColor.WHITE);
        }
    }
    protected void paintPointBlack(WayPoints point){
        pointsColorMap.put(point, PointColor.BLACK);
    }
    protected void addCurrentToPointCurrentWay(WayPoints current){
        currentWay.add(current);
    }
}
