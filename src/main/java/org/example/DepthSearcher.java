package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DepthSearcher {
    private HashMap<WayPoints, Color> pointsColorMap = new HashMap();
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
            List<WayPoints> tmpList = getWhiteWayPoints(current);

            for (WayPoints point:tmpList) {
                int increaedDistance = distans + getDistans(current,point);
                return DepthSearch(point, finish, increaedDistance);
            }
        }
        return distans;
    }
    private List<WayPoints> getWayPoints(WayPoints current){
        List<WayPoints> result = new ArrayList();
        for (IGraphLine iGraphLine: iGraphLines) {
            boolean isPointEx1 = iGraphLine.point1==current;
            boolean isPointEx2 = iGraphLine.point2==current;
            if (isPointEx1){result.add(iGraphLine.point1);}
            if (isPointEx2){result.add(iGraphLine.point2);}
        }
        return result;
    }
     protected List<WayPoints> getWhiteWayPoints(WayPoints current){
        List<WayPoints> result = getWayPoints(current);
        result.stream().filter(point -> pointsColorMap.get(point).equals(Color.WHITE)).toList();
        return result;
    }
    int getDistans(WayPoints a, WayPoints b){
        for (IGraphLine iGraphLine: iGraphLines) {
            boolean isPointEx1 = (iGraphLine.point1==a)||(iGraphLine.point1==b);
            boolean isPointEx2 = (iGraphLine.point2==a)||(iGraphLine.point2==b);
            boolean isDirectHit = isPointEx1&&isPointEx2;
            if (isDirectHit){return iGraphLine.distans;}
        }
        return 0;
    }
    public void populeitPointsColor() {
        for (WayPoints point : WayPoints.values()) {
            pointsColorMap.put(point, Color.WHITE);
        }
    }
}
