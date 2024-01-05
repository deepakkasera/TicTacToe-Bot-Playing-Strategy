package com.example.tttbotplayingsolution.models;

import com.example.tttbotplayingsolution.exceptions.NoPossibleMoveForBotException;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Move makeMove(Board board) throws NoPossibleMoveForBotException {
        Scanner scanner = new Scanner(System.in);

        //Ask the Player to provide the index to make a move.
        System.out.println("Please tell the row index to make a move");
        int rowNumber = scanner.nextInt();

        System.out.println("Please tell the col index to make a move");
        int colNumber = scanner.nextInt();

        return new Move(this, new Cell(rowNumber, colNumber));
    }
}
