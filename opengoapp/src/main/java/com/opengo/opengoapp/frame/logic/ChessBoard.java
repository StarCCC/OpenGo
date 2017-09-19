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
    private Board chessBoard;

    //同形棋盘，分黑白
    private Board blackLastBoard;
    private Board whiteLastBoard;

    //手数
    private int nowStep;

    //棋盘大小
    private  final int BOARD_SIZE = SystemConfig.BOARD_SIZE;

    private static final ChessBoard INSTANCE = new ChessBoard();

    public static ChessBoard getInstance() {
        return INSTANCE;
    }

    public ChessBoard() {
        chessBoard = new Board();
        blackLastBoard = new Board();
        whiteLastBoard = new Board();
        initChessBoard();
    }

    /**
     * 棋盘初始化
     */
    private void initChessBoard() {
        //棋盘置空
        chessBoard.init();
        blackLastBoard.init();
        whiteLastBoard.init();
        //手数归零
        nowStep = 0;
    }

    /**
     * 计算当前位置棋串的气
     * @param p 位置
     * @return int 气数
     */
    private int countLiberty(Location p) {
        int liberty = 0;
        Chess color = chessBoard.getChess(p);
        if (color == Chess.EMPTY) {
            return liberty;
        }

        //创建棋串位置列表，并把当前位置放进去
        List<Location> locations = new ArrayList<Location>();
        locations.add(p);
        //创建已遍历位置列表
        List<Location> done = new ArrayList<Location>();

        //遍历一遍列表，对每个棋子的上下左右的位置检测
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
                if (t == null || Location.findInLocationList(done, t)) { //该方向没有位置(到边界)，或者已经遍历过
                    continue;
                }
                if (chessBoard.getChess(t) == color) { //该位置为同色，是棋串
                    locations.add(t);
                } else if (chessBoard.getChess(t) == Chess.EMPTY) { //该位置为空，气数加一
                    liberty++;
                }
                //已经遍历过的位置存入已遍历位置列表
                done.add(t);
            }
            i++;
        }
        return liberty;
    }

    /**
     * 当前位置的棋子是否提子
     * @param p 位置
     * @return Boolean  true=有提子  false=不能提子
     */
    private Boolean isTakeChess(Location p) {
        List<Location> next = p.getNextLocationList();
        for (int i = 0; i < next.size(); i++) {
            if (countLiberty(next.get(i)) == 0) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 当前位置是否为禁入点
     * @param p  位置
     * @return Boolean
     */
    private Boolean isBanPoint(Location p) {
        if (countLiberty(p) == 0 && !isTakeChess(p)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
