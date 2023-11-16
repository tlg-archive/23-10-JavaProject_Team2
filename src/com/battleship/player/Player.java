package com.battleship.player;

import com.battleship.boards.FiringBoard;
import com.battleship.boards.ShipBoard; //ShipBoard needs to be public
import com.battleship.ship.ShipType; //Ship and shiptype need to be in a package

import java.util.Scanner;
import java.util.regex.Pattern;

public class Player {

    private String fleetName;
    private final String pattern = "[a-jA-J]{1}[0-9]{1}";

    public Player() {

    }

    public Player(String name) {
        setFleetName(name);
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }

    public void placeShips(Scanner scanner, ShipBoard shipBoard){

        for (ShipType ship : ShipType.values()){
            System.out.println("Placing ship: " + ship.getName() + "; size: " + ship.getSize());
            String shipPlacement;
            boolean isHorizontal;

            do {
                System.out.println("Enter the position you want "+ ship.getName() +" (e.g., C3): ");
                try {
                    shipPlacement = scanner.next();
                    shipPlacement = shipPlacement.trim();


                } catch (){}
                System.out.println("Ship horizontal? (true/false)");
                isHorizontal = scanner.nextBoolean();
            } while (!ShipBoard.isValidPlacement(shipPlacement, isHorizontal, ship));

            ShipBoard.placeShip(ship);
        }
    }
    // TODO add takeTurn method
    public String takeTurn(Scanner scanner){

        System.out.println(getFleetName()+ " enter your guess (e.g., B7): ");
        scanner = new Scanner(System.in);
        String guess = scanner.next().toUpperCase();

        while (!guess.matches(pattern)){
            System.out.println("That is an invalid sequence, valid sequence = A9. first being A-J and second being 0-9");
            guess = scanner.next();
        }
        return guess;
    }
}