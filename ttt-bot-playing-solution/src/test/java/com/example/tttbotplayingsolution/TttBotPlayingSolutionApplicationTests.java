package com.example.tttbotplayingsolution;

import com.example.tttbotplayingsolution.controllers.GameController;
import com.example.tttbotplayingsolution.exceptions.NoPossibleMoveForBotException;
import com.example.tttbotplayingsolution.models.*;
import com.example.tttbotplayingsolution.strategies.BotPlayingStrategy;
import com.example.tttbotplayingsolution.strategies.RandomBotPlayingStrategy;
import com.example.tttbotplayingsolution.strategies.WinningStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TttBotPlayingSolutionApplicationTests {
    private Game game;
    private GameController gameController;
    private List<Player> players;
    private WinningStrategy winningStrategy;
    private Board board;
    private BotPlayingStrategy botPlayingStrategy;

    @BeforeEach
    public void initializeGame() throws Exception {
        gameController = new GameController();
        int dimension = 3;
        players = new ArrayList<>();
        players.add(new Player("Player1", new Symbol('X'), PlayerType.HUMAN));
        players.add(new Player("Player2", new Symbol('O'), PlayerType.HUMAN));
        game = gameController.startGame(dimension, players);
        botPlayingStrategy = new RandomBotPlayingStrategy();
    }

    @Test
    public void testGameWinningCase() {
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        Board board = game.getBoard();
        winningStrategy = game.getWinningStrategy();

        makeMove(board, player1, 0, 0); //P1 -> 0,0
        makeMove(board, player2, 1, 1 ); //P2 -> 1,1
        makeMove(board, player1, 0, 1); //P1 -> 0,1
        makeMove(board, player2, 1, 2); //P2 -> 1,2

        //Winning move
        board.getBoard().get(0).get(2).setPlayer(player1);
        board.getBoard().get(0).get(2).setCellState(CellState.FILLED);

        assertTrue(winningStrategy.checkWinner(board, new Move(player1, new Cell(0, 2))),
                "Player 1 should be the winner");
    }

    @Test
    public void testNoPossibleMoveForBotException() throws NoPossibleMoveForBotException {
        Player humanPlayer = new Player("humanPlayer", new Symbol('X'), PlayerType.HUMAN);
        Player botPayer = new Bot("botPlayer", new Symbol('O'), PlayerType.BOT, botPlayingStrategy);
        Board board = game.getBoard();
        winningStrategy = game.getWinningStrategy();

        makeMove(board, humanPlayer, 0, 0); //P1 -> 0,0
        botMove(board, botPayer); //0,1
        makeMove(board, humanPlayer, 0, 2);
        botMove(board, botPayer); //1,0
        makeMove(board, humanPlayer, 1, 1);
        botMove(board, botPayer);
        makeMove(board, humanPlayer, 2, 0);
        botMove(board, botPayer);
        makeMove(board, humanPlayer, 2, 2);
        assertThrows(NoPossibleMoveForBotException.class, () -> botMove(board, botPayer));
    }

    @Test
    public void testBotPlayingWinningCase() throws NoPossibleMoveForBotException {
        Player humanPlayer = new Player("humanPlayer", new Symbol('X'), PlayerType.HUMAN);
        Player botPayer = new Bot("botPlayer", new Symbol('O'), PlayerType.BOT, botPlayingStrategy);
        Board board = game.getBoard();
        winningStrategy = game.getWinningStrategy();

        botMove(board, botPayer); // 0,0
        makeMove(board, humanPlayer, 0, 1);
        botMove(board, botPayer);
        makeMove(board, humanPlayer, 1, 1);
        botMove(board, botPayer);
        makeMove(board, humanPlayer, 1, 2);
        Move move = botMove(board, botPayer);

        assertTrue(winningStrategy.checkWinner(board, move),
                "Bot should be the winner");
    }

    @Test
    public void testHumanPlayingWinningCase() throws NoPossibleMoveForBotException {
        Player humanPlayer = new Player("humanPlayer", new Symbol('X'), PlayerType.HUMAN);
        Player botPayer = new Bot("botPlayer", new Symbol('O'), PlayerType.BOT, botPlayingStrategy);
        Board board = game.getBoard();
        winningStrategy = game.getWinningStrategy();

        makeMove(board, humanPlayer, 1, 1);
        botMove(board, botPayer);
        makeMove(board, humanPlayer, 0, 2);
        botMove(board, botPayer);

        //Winning move
        board.getBoard().get(2).get(0).setPlayer(humanPlayer);
        board.getBoard().get(2).get(0).setCellState(CellState.FILLED);

        assertTrue(winningStrategy.checkWinner(board, new Move(humanPlayer, new Cell(2, 0))));
    }

    private void makeMove(Board board, Player player, int row, int col) {
        board.getBoard().get(row).get(col).setPlayer(player);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        Move move = new Move(player, new Cell(row, col));
        game.getMoves().add(move);
        game.getWinningStrategy().checkWinner(board, move);
    }

    private Move botMove(Board board, Player player) throws NoPossibleMoveForBotException {
        Move move = player.makeMove(board);
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        board.getBoard().get(row).get(col).setPlayer(player);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        game.getWinningStrategy().checkWinner(board, move);
        return move;
    }
}
