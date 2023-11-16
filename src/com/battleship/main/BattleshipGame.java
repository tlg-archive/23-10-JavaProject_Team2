package com.battleship.main;
import com.battleship.boards.FiringBoard;
import com.battleship.boards.ShipBoard;
import com.battleship.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class BattleshipGame {
    private ShipBoard shipBoard; // size of the game board
    private FiringBoard firingBoard; // manages firing attempts
    private Player player;
    private boolean gameOver = false;

    public BattleshipGame() {
        shipBoard = new ShipBoard(); // TODO: pass in parameter from ShipBoard
        firingBoard = new FiringBoard(); // TODO: pass in parameter from FiringBoard
        player = new Player();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        player.placeShips(scanner, shipBoard); // Player places their ships

        while (!gameOver) {
            String impact = player.takeTurn(scanner); // Player takes a turn to fire
            boolean hit = shipBoard.isValidPlacement(parseImpact(impact)); // Check if the fire hit a ship // TODO: change placeholder fireAt() to current shipBoard method

            if (hit) {
                shipBoard.sink(); // sink() updates the ship board after a hit
            }
            // fire() combined with impact() record the shot
            firingBoard.setAiming(impact);
            firingBoard.fire();

            if (hit) {
                System.out.println("Hit!");
                gameOver = shipBoard.allShipsSunk(); // check if all ships are sunk
            }
            else {
                System.out.println("Miss!");
            }
            firingBoard.printBoard(); // Display current state of the firing board
        }

        System.out.println("Game Over! All ships have been sunk.");
        scanner.close(); // Close scanner
    }

    private ArrayList<String> parseImpact(String impact) {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        new BattleshipGame().startGame(); // starts the game
    }
}