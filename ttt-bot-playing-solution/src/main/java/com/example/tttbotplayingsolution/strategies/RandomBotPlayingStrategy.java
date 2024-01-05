package com.example.tttbotplayingsolution.strategies;

import com.example.tttbotplayingsolution.exceptions.NoPossibleMoveForBotException;
import com.example.tttbotplayingsolution.models.*;

public class RandomBotPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move decideMove(Player player, Board board) throws NoPossibleMoveForBotException {
        //Bot will just iterate through the board and make the move at the first empty cell.

        for (int i = 0; i < board.getBoard().size(); i++) {
            for (int j = 0; j < board.getBoard().size(); j++) {
                if (board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)) {
                    //Bot will make a move here.
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        throw new NoPossibleMoveForBotException("No possible move for bot");
    }
}
