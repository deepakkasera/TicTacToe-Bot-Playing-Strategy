package com.example.tttbotplayingsolution.strategies;

import com.example.tttbotplayingsolution.exceptions.NoPossibleMoveForBotException;
import com.example.tttbotplayingsolution.models.Board;
import com.example.tttbotplayingsolution.models.Move;
import com.example.tttbotplayingsolution.models.Player;

public interface BotPlayingStrategy {
    Move decideMove(Player player, Board board) throws NoPossibleMoveForBotException;
}
