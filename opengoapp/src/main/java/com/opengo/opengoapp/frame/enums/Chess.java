package com.opengo.opengoapp.frame.enums;

/**
 * Created by itservice on 2017/8/22.
 */
public enum Chess {
    BLACK_CHESS(0), WHITE_CHESS(1), EMPTY(2);

    private int value;

    Chess(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Chess getReverseChess() {
        if (value == BLACK_CHESS.getValue())
            return WHITE_CHESS;
        return BLACK_CHESS;
    }
}
