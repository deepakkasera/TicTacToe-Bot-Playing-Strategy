package com.example.tttbotplayingstarter.controllers;

import com.example.tttbotplayingstarter.exceptions.InvalidMoveException;
import com.example.tttbotplayingstarter.models.Game;
import com.example.tttbotplayingstarter.models.GameState;
import com.example.tttbotplayingstarter.models.Player;

import java.util.List;

public class GameController {
    public Game startGame(int dimension, List<Player> players) throws Exception {
        return Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .build();
    }

    public void makeMove(Game game) throws InvalidMoveException {
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
