package com.opengo.opengoapp.frame.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * 位置类
 * Created by StarCCC on 2017/9/15.
 */
public class Location {
    private int x;//x轴
    private int y;//y轴

    public Location() {
        this(0, 0);
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //获取上面邻接点坐标，没有返回null
    public Location getUp() {
        if (x <= 0) {
            return null;
        }
        return new Location(x - 1, y);
    }

    //获取下面邻接点坐标，没有返回null
    public Location getDown() {
        if (x >= 18) {
            return null;
        }
        return new Location(x + 1, y);
    }

    //获取左面邻接点坐标，没有返回null
    public Location getLeft() {
        if (y <= 0) {
            return null;
        }
        return new Location(x, y - 1);
    }

    //获取右面邻接点坐标，没有返回null
    public Location getRight() {
        if (y >= 18) {
            return null;
        }
        return new Location(x, y + 1);
    }

    //获取一个包含上下左右四个点的List
    public List<Location> getNextLocationList() {
        List<Location> r = new ArrayList<Location>();
        Location next = this.getUp();
        if (next != null) {
            r.add(next);
        }
        next = this.getDown();
        if (next != null) {
            r.add(next);
        }
        next = this.getRight();
        if (next != null) {
            r.add(next);
        }
        next = this.getLeft();
        if (next != null) {
            r.add(next);
        }
        return r;
    }

    //判断相等
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        Location e = (Location)obj;
        if (this.x == e.getX() && this.y == e.getY())
            return true;
        return false;
    }

    /**
     * 在位置列表里查找某位置是否存在
     * @param list
     * @param e
     * @return
     */
    public static Boolean findInLocationList(List<Location> list, Location e) {
        for (Location loc :list) {
            if (loc.equals(e))
                return true;
        }
        return false;
    }
}