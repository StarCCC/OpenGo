package com.opengo.opengoapp.frame.logic;

import com.opengo.opengoapp.frame.enums.Chess;
import com.opengo.opengoapp.frame.units.SystemConfig;

/**
 * 棋盘类
 * Created by StarCCC on 2017/9/15.
 */
public class Board {

    //棋盘
    private Chess[][] board;

    //大小
    private int SIZE;

    public Board() {
        this(SystemConfig.BOARD_SIZE);
    }

    public Board(int size) {
        SIZE = size;
        board = new Chess[SIZE][SIZE];
    }

    /**
     * 棋盘初始化
     */
    public void init() {
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE ; j++) {
                board[i][j] = Chess.EMPTY;
            }
        }
    }

    /**
     * 棋盘复制函数，从源棋盘
     * @param source  源
     */
    public void copyFrom(Board source) {
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE ; j++) {
                board[i][j] = source.getChess(i, j);
            }
        }
    }

    /**
     * 判断两棋盘是否相同
     * @param obj   比较棋盘
     * @return  Boolean
     */
    @Override
    public boolean equals(Object obj) {
        Board bBoard = (Board)obj;
        for (int i = 0; i < SIZE ; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != bBoard.getChess(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 获取某位置棋子
     * @param x
     * @param y
     * @return
     */
    public Chess getChess(int x, int y) {
        return board[x][y];
    }

    /**
     * 获取某位置棋子
     * @param location
     * @return
     */
    public Chess getChess(Location location) {
        return board[location.getX()][location.getY()];
    }
}