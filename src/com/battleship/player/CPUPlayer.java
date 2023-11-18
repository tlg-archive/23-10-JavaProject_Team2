package com.battleship.player;

import com.battleship.boards.FiringBoard;
import com.battleship.boards.ShipBoard;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class CPUPlayer extends Player{
    private static Random random;
    private static String previousHit;
    private static final String pattern = "[a-jA-J]{1}[0-9]{1}";
    private String shot = null;

    public CPUPlayer() {
        random = new Random();
    }

    public String takeTurn(FiringBoard firingBoard, ShipBoard shipBoard){

        // rule-based shooting strategy
        String guess;

        // Rule 1: If there was a hit, target nearby locations
        if (previousHit != null){
            guess = generateNearbyTarget(previousHit);
        }

        // Rule 2: On the first turn or no hits, shoot randomly
        else{
            do {
                char letter = (char) (random.nextInt(9) + ('a'));
                char number = (char) (random.nextInt(9));
                String s1 = String.valueOf(letter);
                String s2 = String.valueOf(number+1);
                guess = s1 + s2;
            } while (!guess.matches(pattern));
        }
        if (firingBoard.impact(guess, shipBoard) && !shipBoard.sink(shipBoard)){
            previousHit = guess;
        }

        return guess;
    }

    private String generateNearbyTarget(String previousHit){
        String guess;
        char row = previousHit.charAt(0);
        char col = previousHit.charAt(1);

        // simple strategy, shoot above, below, left, or right of previous hit
        int direction = random.nextInt(4);
        switch (direction){
            case 0: // above
                guess = String.valueOf(row) + (col + 1);
                break;
            case 1: //below
                guess = String.valueOf(row)+ (col - 1);
                break;
            case 2: // left
                guess = String.valueOf(row -1) + col;
                break;
            case 3: //right
                guess = String.valueOf(row + 1) + col;
                break;
            default:
                return previousHit;

        }

        return guess;
    }


}