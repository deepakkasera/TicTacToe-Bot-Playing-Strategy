package com.example.tttbotplayingstarter.strategies;

import com.example.tttbotplayingstarter.models.Board;
import com.example.tttbotplayingstarter.models.Move;
import com.example.tttbotplayingstarter.models.Player;

public interface BotPlayingStrategy {
    Move decideMove(Player player, Board board) throws NoPossibleMoveForBotException;
}
