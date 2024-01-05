package com.example.tttbotplayingstarter.strategies;

import com.example.tttbotplayingstarter.models.Board;
import com.example.tttbotplayingstarter.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
