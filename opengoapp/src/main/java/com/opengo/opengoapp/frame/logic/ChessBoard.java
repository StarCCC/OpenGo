package com.opengo.opengoapp.frame.logic;

import com.opengo.opengoapp.frame.enums.Chess;
import com.opengo.opengoapp.frame.units.SystemConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 棋盘逻辑类
 * 主要解决下棋的问题
 * 包含棋规和下棋动作的接口，并记录真实棋盘状态
 * Created by StarCCC on 2017/8/22.
 */
public class ChessBoard {

    //棋盘
    private Chess[][] chessBoard;

    //手数
    private int nowStep;

    //棋盘大小
    private final int BOARD_SIZE = SystemConfig.BOARD_SIZE;

    //坐标
    private class Location {
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
    }

    private static final ChessBoard INSTANCE = new ChessBoard();

    public static ChessBoard getInstance() {
        return INSTANCE;
    }

    public ChessBoard() {
        chessBoard = new Chess[BOARD_SIZE][BOARD_SIZE];
        initChessBoard();
    }

    /**
     * 棋盘初始化
     */
    private void initChessBoard() {
        //棋盘置空
        for (int i = 0; i < BOARD_SIZE ; i++) {
            for (int j = 0; j < BOARD_SIZE ; j++) {
                chessBoard[i][j] = Chess.EMPTY;
            }
        }
        //手数归零
        nowStep = 0;
    }

    /**
     * 棋盘复制函数，从源棋盘到目标棋盘
     * @param cbSource
     * @param cbTarget
     */
    private void copyChessBoard(Chess[][] cbSource, Chess[][] cbTarget) {
        for (int i = 0; i < BOARD_SIZE ; i++) {
            for (int j = 0; j < BOARD_SIZE ; j++) {
                cbTarget[i][j] = cbSource[i][j];
            }
        }
    }

    /**
     * 计算当前位置棋串的气
     * @param p
     * @return
     */
    private int countLiberty(Location p) {
        int liberty = 0;
        Chess color = getChessFromBoard(p);
        if (color == Chess.EMPTY) {
            return liberty;
        }

        List<Location> locations = new ArrayList<Location>();
        locations.add(p);

        int i = 0;
        while (locations.size() > 0){
            Location loc = null;
            loc = locations.get(i);
            if (loc == null) {
                break;
            }
            for (int j = 0; j < 4; j++) {
                Location t = new Location();
                switch (j) {
                    case 0:t = p.getUp();break;
                    case 1:t = p.getDown();break;
                    case 2:t = p.getLeft();break;
                    case 3:t = p.getRight();break;
                    default:break;
                }
                if (t == null) {
                    continue;
                }
                if (getChessFromBoard(t) == color) {
                    locations.add(t);
                } else if (getChessFromBoard(t) == Chess.EMPTY) {
                    liberty++;
                }
            }
            i++;
        }
        return liberty;
    }

    /**
     * 获取当前位置棋子
     * @param p
     * @return
     */
    private Chess getChessFromBoard(Location p) {
        return chessBoard[p.getX()][p.getY()];
    }




}
