package com.example.tttbotplayingsolution.models;

import com.example.tttbotplayingsolution.exceptions.NoPossibleMoveForBotException;
import com.example.tttbotplayingsolution.strategies.BotPlayingStrategy;

public class Bot extends Player {
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, PlayerType playerType, BotPlayingStrategy botPlayingStrategy) {
        super(name, symbol, playerType);
        this.botPlayingStrategy = botPlayingStrategy;
    }

    @Override
    public Move makeMove(Board board) throws NoPossibleMoveForBotException {
        Move move = botPlayingStrategy.decideMove(this, board);
        return move;
    }
}
