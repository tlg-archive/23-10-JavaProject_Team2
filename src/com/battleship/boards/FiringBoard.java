package com.battleship.boards;

import java.util.ArrayList;
import java.util.List;

public class FiringBoard {
    private List<String> fireRecord;
    private List<String> firingBoardHits;
    private final ShipBoard shipBoard;

    public FiringBoard(ShipBoard shipBoard) {
        this.shipBoard = shipBoard;
    }

    // fires round
    public void fire(String aim) {
        if (fireRecord == null) {
            firingBoardHits = new ArrayList<>();
            fireRecord = new ArrayList<>();
            fireRecord.add(aim);
            impact(aim);
        }
        else {
            for (String shot : fireRecord) {
                if (aim.equals(shot)) {
                    System.out.println("That grid has already been hit!");
                    break;
                } else {
                    fireRecord.add(aim);
                    impact(aim);
                    break;
                }
            }
        }
    }

    // round recorded
    public boolean impact(String aim) {
        boolean result = false;
        for (List<String> boat : shipBoard.getShipBoard()) {

            for (String b : boat) {
                if (b.equals(aim)) {
                    firingBoardHits.add(aim);
                    System.out.println("That round hit a ship! " + aim);
                    hit(aim);
                    result = true;
                    break;

                } else {
                    fireRecord.add(aim);
                    System.out.println("That round hit water! " + aim);
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    // hit removes grid spot
    private void hit(String guess) {
        for (List<String> boat : shipBoard.getShipBoard()) {
            boat.remove(guess);
        }
    }

    // prints current state of FiringBoard
    public void printFiringBoard() {
        char[][] board = displayFiringBoard();
        for (char[] chars : board) {
            List<String> tempBoardList = new ArrayList<>();
            for (char c : chars){
                tempBoardList.add(color(c));
            }
            System.out.println(tempBoardList);
        }
    }

    // displays new FiringBoard
    private char[][] displayFiringBoard() {
        char[][] board = {
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
                {'-', '-', '-', '-', '-', '-', '-', '-', '-', '-',},
        };

        if (fireRecord != null && firingBoardHits != null) {
            for (String record : fireRecord) {
                char row = record.charAt(0);
                char col = record.charAt(1);
                int colInt = col - '1';
                int rowInt = (row - 'a' + 1) - 1;
                board[rowInt][colInt + 1] = 'M';
            }

            for (String record : firingBoardHits) {
                char row = record.charAt(0);
                char col = record.charAt(1);
                int colInt = col - '1';
                int rowInt = (row - 'a' + 1) - 1;
                board[rowInt][colInt + 1] = 'H';
            }
        }
        return board;
    }

    private String color(char grid) {
        String resetColor = "\u001B[0m";
        String red = "\u001B[31m";
        String cyan = "\u001B[36m";
        String gray = "\u001B[90m";

        switch (grid){
            case '-':
                return cyan + grid + resetColor;
            case 'H':
                return red + grid + resetColor;
            case 'M':
                return gray + grid +resetColor;
            default:
                return " ";
        }
    }

    public List<String> getFiringBoardHits() {
        return firingBoardHits;
    }
}
