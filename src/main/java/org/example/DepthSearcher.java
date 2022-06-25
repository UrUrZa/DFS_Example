package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepthSearcher {
    private HashMap<WayPoints, PointColor> pointsColorMap = new HashMap();
    private IGraphLine[] iGraphLines = new IGraphLine[]{};
    public DepthSearcher(IGraphLine[] iGraphLines) {
        this.iGraphLines = iGraphLines;
        populeitPointsColor();
    }
    public DepthSearcher(IGraphLine[] iGraphLines, HashMap<WayPoints, PointColor> pointsColorMap) {
        this.iGraphLines = iGraphLines;
        this.pointsColorMap =pointsColorMap;
    }

    public int DepthSearch(WayPoints current, WayPoints finish, int distans){
        boolean isFinished = current.equals(finish);
        if(current.equals(WayPoints.B2)){
            System.out.println("");
        }
        if (isFinished) {
            return distans;
        } else {
            List<WayPoints> ClosesWhitePoints = getWhiteWayPoints(current);
            paintPointBlack(current);
            for (WayPoints point:ClosesWhitePoints) {
                DepthSearcher depthSearcher = new DepthSearcher(iGraphLines,pointsColorMap);
                int incresedDistance = distans + getDistans(current,point);
                return depthSearcher.DepthSearch(point, finish, incresedDistance);
            }
        }
        return distans;
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
}
