package com.example.tttbotplayingsolution.controllers;

import com.example.tttbotplayingsolution.exceptions.InvalidMoveException;
import com.example.tttbotplayingsolution.exceptions.NoPossibleMoveForBotException;
import com.example.tttbotplayingsolution.models.Game;
import com.example.tttbotplayingsolution.models.GameState;
import com.example.tttbotplayingsolution.models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players) throws Exception {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException, NoPossibleMoveForBotException {
        game.makeMove();
    }

    public void displayBoard(Game game) {
        game.displayBoard(game.getBoard());
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public GameState getGameState(Game game) {
        return game.getGameState();
    }
}
