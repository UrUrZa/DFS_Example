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

    public int DepthSearch(WayPoints current, WayPoints finish, int distans){
        boolean isFinished = current.equals(finish);
        if (isFinished) {
            return distans;
        } else {
            List<WayPoints> ClosesWhitePoints = getWhiteWayPoints(current);
            paintPointBlack(current);
            for (WayPoints point:ClosesWhitePoints) {
                int increaedDistance = distans + getDistans(current,point);
                return DepthSearch(point, finish, increaedDistance);
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
        List<WayPoints> result = getWayPoints(current);
        return result.stream().filter(point -> pointsColorMap.get(point).equals(PointColor.WHITE)).toList();
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
