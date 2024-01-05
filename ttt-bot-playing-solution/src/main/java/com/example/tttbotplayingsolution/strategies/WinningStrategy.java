package com.example.tttbotplayingsolution.strategies;

import com.example.tttbotplayingsolution.models.Board;
import com.example.tttbotplayingsolution.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
