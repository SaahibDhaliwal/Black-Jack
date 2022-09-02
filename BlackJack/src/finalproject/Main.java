/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 * Program Description: This program aims to mimic the Black Jack experience whilst still providing a fun and enjoyable experience for the user.
 * This program provides menu options where the user can read the rules/instructions, start a game, or exit the program. Each choice has the consequence implied
 * by the name of the choice (for example, the option "start game", starts the game and etc.). Furthermore, once the game has started the user is able to bet from the 
 * 100 dollars they are given at the start of every new game. The user and the dealer (computer) are given two cards from a shuffled deck of cards and the user is 
 * able to choose to hit (get another card) or stand (keep the cards they have). Each choice has the consequence implied by the name of the choice. The program then 
 * determines who has won between the user and the dealer (by using the total value of their cards). The program then determines how much money the user has and it 
 * also updates a scoreboard with the wins, losses, and ties. Afterwards, there is another menu option where the user can decide to play again, start a new game, or 
 * exit the program. Each choice has the consequence implied by the name of the choice. Moreover, playing again will let the user keep the money they have (and use it
 * to bet) and the scoreboard will continue to add wins, losses, and ties after each round (keeping track of the total wins, losses, and ties). Whereas, starting a 
 * new game will wipe the scoreboard and give the user 100 dollars to start once again. 
 */

//Import toolkits
import javax.swing.*;
import java.text.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    //Declare and set extent for shuffled deck array
    static int[] shuffleDeck = new int[52];
    //Declare and set elements arryas with all of the card names and values
    static String[] cardNames = {"2 of Spades", "2 of Hearts", "2 of Clubs", "2 of Diamonds", "3 of Spades", "3 of Hearts", "3 of Clubs", "3 of Diamonds",
        "4 of Spades", "4 of Hearts", "4 of Clubs", "4 of Diamonds", "5 of Spades", "5 of Hearts", "5 of Clubs", "5 of Diamonds",
        "6 of Spades", "6 of Hearts", "6 of Clubs", "6 of Diamonds", "7 of Spades", "7 of Hearts", "7 of Clubs", "7 of Diamonds",
        "8 of Spades", "8 of Hearts", "8 of Clubs", "8 of Diamonds", "9 of Spades", "9 of Hearts", "9 of Clubs", "9 of Diamonds",
        "10 of Spades", "10 of Hearts", "10 of Clubs", "10 of Diamonds", "Ace of Spades", "Ace of Hearts", "Ace of Clubs", "Ace of Diamonds",
        "Queen of Spades", "Queen of Hearts", "Queen of Clubs", "Queen of Diamonds", "King of Spades", "King of Hearts", "King of Clubs", "King of Diamonds",
        "Jack of Spades", "Jack of Hearts", "Jack of Clubs", "Jack of Diamonds"};
    static int[] cardValues = {2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 10, 11, 11, 11, 11, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    //Declare arrays for player (user) and dealer (computer) cards and player and dealer card values (the extent is 11 becuase that is the maximum number of cards that someone can have without busting)
    static String[] playerCard = new String[11];
    static int[] playerCValue = new int[11];
    static String[] dealerCard = new String[11];
    static int[] dealerCValue = new int[11];
    //Declare variables for player and dealer card totals
    static int playerTotal;
    static int dealerTotal;
    //Declare and assign global variables for counters that will be used to give the player and dealer their next card if they hit
    static int playerCounter;
    static int dealerCounter;
    static int playerCardNum;
    static int dealerCardNum;
    //Declare a variable for the user's choice of hitting or standing
    static int userChoice;
    //Declare variables for menu choices
    static int menuInstructionChoice, menuAfterChoice, menuChoice;
    static int aceValueChoice;
    //Declare and assign value to how much money a user starts with
    static double money = 100;
    //Declare variables for how much the user wants to bet and for wins, losses, and ties
    static double betValue;
    static int wins, losses, ties;

    //The main method will call the new game method
    public static void main(String[] args) {
        newGame();
    }

    //The menu choice method has different consequences based on what menu option the user chooses
    public static void menuChoice() {
        //Get the user's menu option (the menu lets the user see the instructions, play, and exit) and ensure it is valid
        boolean flag1;
        do {
            flag1 = false;
            try {
                menuChoice = Integer.parseInt(JOptionPane.showInputDialog("Welcome to Black Jack\n====================\nMenu (enter the corresponding number to the option you would like to choose)"
                        + " \n1. Rules/Instructions\n2. Start game\n3. Exit program"));
            } catch (NumberFormatException a) {
                JOptionPane.showMessageDialog(null, "Please enter a valid menu option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                flag1 = true;
            }
            if ((menuChoice != 1 && menuChoice != 2 && menuChoice != 3) && flag1 != true) {
                JOptionPane.showMessageDialog(null, "Please enter a valid menu option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                flag1 = true;
            }
        } while (flag1 == true);
        //If the user's menu option is 1, display the instructions and give them another menu option that lets them play and exit
        if (menuChoice == 1) {
            System.out.println("Rules/Instructions:\n===================\nTo begin, in “Black Jack” the player and the dealer are dealt two cards from a shuffled deck of cards.\nHowever, before the cards are dealt the "
                    + "player gets to place a bet. Each card has a numerical value, for all suits,\ncards with numbers 2-10 have a numerical value from 2-10 that corresponds to that card. For example, a "
                    + "one of hearts\nhas a value of 1. If the card is an Ace, the user gets to decide whether they want it to have a value of 1 or 11,\nhowever, the game will automatically choose the best option for the user if "
                    + "there is only one choice that prevents\nthem from busting or if there is a choice that will result in the user getting a 21. (the dealer always plays an Ace\nas an 11 unless it will make "
                    + "them bust). Moreover, face cards (jack, queen, and king of any suit) will have a value\nof 10. Therefore, the sum of the first two cards a player has is their total. As mentioned earlier, "
                    + "the dealer\nwill also get two cards, however, only one will be revealed to the player. The player can then choose to hit\n(get another card) or stand (keep the cards they have). The sum of all "
                    + "the cards a player has cannot be greater\nthan 21, if it is, they bust and lose. The dealer must hit if the sum of their own first two cards is less than 17,\nand the dealer must stand if the sum of "
                    + "their own first two cards is greater than 17. Therefore, if the player\ndoes not bust and they have a greater total with their cards then the dealer, the player wins, and vice versa.\nHowever, if the "
                    + "player busts (even if the dealer busts), the player loses. Furthermore, if the dealer busts and the\nplayer does not, the player wins. Finally, if the player’s total is equal to the dealer’s total"
                    + " (without either of\nthem busting), then there is a draw. If the player wins, they win what they had initially bet (for example, if they\nbet $100 and they win, they will get back $200, thus, gaining $100). "
                    + "However, if the player wins and the player has\na 21, they win double what they had initially bet. If the player loses, they lose the money they had bet. If the\nplayer ties with the dealer, the player does not gain or lose money.\n");
            //Get the user's menu option from the menu after the instructions and ensure it is valid
            boolean flag2;
            do {
                flag2 = false;
                try {
                    menuInstructionChoice = Integer.parseInt(JOptionPane.showInputDialog("Enter the corresponding number to the option you would like to choose:\n1. Start game\n2. Exit Program"));
                } catch (NumberFormatException b) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid menu option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    flag2 = true;
                }
                if ((menuInstructionChoice != 1 && menuInstructionChoice != 2) && flag2 != true) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid menu option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    flag2 = true;
                }
            } while (flag2 == true);
        }
        //If user's menu option is 2 or if the user's option is 1 in the menu after the instructios, run the game and display another menu after the game
        if (menuChoice == 2 || menuInstructionChoice == 1) {
            menuAfterChoice = 0;
            do {
                //Initialize the counters and ace value
                playerCounter = 2;
                dealerCounter = 3;
                playerCardNum = 1;
                dealerCardNum = 1;
                aceValueChoice = 0;
                //Call all of the methods 
                shuffleDeck();
                startDisplayandCardTotal();
                userOption();
                dealerHitorStand();
                resultNoBust();
                scoreboardMoney();

                /**
                 * Get the user's menu option from the menu after the game (the
                 * menu lets the user play again, start a new game, or exit) and
                 * ensure it is valid Run the game again and give this menu
                 * option again if the user decides to play again
                 */
                boolean flag3;
                do {
                    flag3 = false;
                    try {
                        menuAfterChoice = Integer.parseInt(JOptionPane.showInputDialog("Enter the corresponding number to the option you would like to choose:\n1. Play again\n2. New game\n3. Exit program"));
                    } catch (NumberFormatException c) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid menu option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flag3 = true;
                    }
                    if ((menuAfterChoice != 1 && menuAfterChoice != 2 && menuAfterChoice != 3) && flag3 != true) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid menu option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flag3 = true;
                    }
                } while (flag3 == true);
            } while (menuAfterChoice == 1);
        }
        //End the program and print have a great day if the user decides to exit the program in any one of the three menus 
        if (menuChoice == 3 || menuInstructionChoice == 2 || menuAfterChoice == 3) {
            System.out.println("Have a great day!");
        }
    }

    //This method calles the menuChoice method and creates a new game if the user decides to start a new game from the after game menu in the menuChoice method
    public static void newGame() {
        menuChoice();
        if (menuAfterChoice == 2) {
            do {
                //Intialize the money, wins, losses, and ties
                wins = 0;
                ties = 0;
                losses = 0;
                money = 100;
                //Print a messge to make it clear to the user that a new game has been created
                System.out.println("\n\n\n\n\n========\nNEW GAME\n========");
                //Run the menuChoce method again (because that runs the game and gives the player all of the menus again)
                menuChoice();
            } while (menuAfterChoice == 2);
        }
    }

    /**
     * This method shuffles the deck by generating a random numbers (0-51) that
     * do not repeat (these numbers will then be used as index positions for the
     * card names and values
     */
    public static void shuffleDeck() {
        boolean flag = false;
        for (int i = 0; i < 52; i++) {
            int randomCard = (int) (Math.random() * (52));
            for (int j = 0; j < i; j++) {
                if (randomCard == shuffleDeck[j]) {
                    i--;
                    flag = true;
                }
            }
            if (flag == false) {
                shuffleDeck[i] = randomCard;
            } else {
                flag = false;
            }
        }
    }

    /**
     * This method determines the player and the dealer's card total for the
     * first two cards. This method will also display a message for each game
     * that includes how much money the player has and it asks how much they
     * would like to bet.
     */
    public static void startDisplayandCardTotal() {
        //Initialize decimal format
        DecimalFormat twoDigit = new DecimalFormat("$##0.00");
        //Give the player the first and third card in the shuffled deck by using the random index positions generated in the shuffleDeck method
        playerCard[0] = cardNames[shuffleDeck[0]];
        playerCard[1] = cardNames[shuffleDeck[2]];
        playerCValue[0] = cardValues[shuffleDeck[0]];
        playerCValue[1] = cardValues[shuffleDeck[2]];
        //Display message for each game and tell the user how much money they have
        System.out.println("=========\nBLACKJACK\n=========");
        System.out.println("\nYou have " + twoDigit.format(money));
        //Ask the user how much they would like to bet and get the value (ensure it is valid)
        boolean flag4;
        do {
            flag4 = false;
            try {
                betValue = Double.parseDouble(JOptionPane.showInputDialog("How much would you like to bet? Enter a value within your range of money:"));
            } catch (NumberFormatException d) {
                JOptionPane.showMessageDialog(null, "Please enter a valid betting amount.", "ERROR", JOptionPane.ERROR_MESSAGE);
                flag4 = true;
            }
            if ((betValue > money || betValue < 0) && flag4 != true) {
                JOptionPane.showMessageDialog(null, "Value is outside of your range of money. Please enter a valid betting amount.", "ERROR", JOptionPane.ERROR_MESSAGE);
                flag4 = true;
            }
        } while (flag4 == true);
        //Display how much the user decided to best and their first card
        System.out.println("\nYou are betting " + twoDigit.format(betValue));
        System.out.println("\nYour first card is: " + playerCard[0]);

        //Let the player decide what value their card holds if it is an ace (1 or 11)
        if (playerCard[0].equals(cardNames[36]) || playerCard[0].equals(cardNames[37]) || playerCard[0].equals(cardNames[38]) || playerCard[0].equals(cardNames[39])) {
            //Get the value the user would like to use the Ace as and ensure it is valid
            boolean flag5;
            do {
                flag5 = false;
                try {
                    aceValueChoice = Integer.parseInt(JOptionPane.showInputDialog("Your card was an " + playerCard[0] + ". Would you like the card to have a value of 1 or 11? "
                            + "Enter the corresponding number to the option you would like to choose.\n1. 1\n2. 11"));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    flag5 = true;
                }
                if ((aceValueChoice != 1 && aceValueChoice != 2) && flag5 != true) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    flag5 = true;
                }
            } while (flag5 == true);
        }
        //Use the players choice for the Ace value as the value of the Ace card
        if (aceValueChoice == 1) {
            playerCValue[0] = 1;
        } else if (aceValueChoice == 2) {
            playerCValue[0] = 11;
        }
        //Display the user's second card
        System.out.println("Your second card is: " + playerCard[1]);

        /**
         * If the user's second card is an Ace, automatically give the card a
         * value of 11 if it results in a 21. If the player's first card is an
         * Ace that has a value of 11, automatically give the second Ace a value
         * of 1. Let the player decide what value their Ace holds (1 or 11) if
         * none of the above occur
         */
        if (playerCard[1].equals(cardNames[36]) || playerCard[1].equals(cardNames[37]) || playerCard[1].equals(cardNames[38]) || playerCard[1].equals(cardNames[39])) {
            if (playerCValue[0] + 11 == 21) {
                playerCValue[1] = 11;
            } else if (playerCValue[0] == 11) {
                playerCValue[1] = 1;
            } else {
                //Get the value the user would like to use the Ace as and ensure it is valid
                boolean flag6;
                do {
                    flag6 = false;
                    try {
                        aceValueChoice = Integer.parseInt(JOptionPane.showInputDialog("Your card was an " + playerCard[1] + ". Would you like the card to have a value of 1 or 11? "
                                + "Enter the corresponding number to the option you would like to choose.\n1. 1\n2. 11"));
                    } catch (NumberFormatException f) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flag6 = true;
                    }
                    if ((aceValueChoice != 1 && aceValueChoice != 2) && flag6 != true) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flag6 = true;
                    }
                } while (flag6 == true);
                //Use the players choice for the Ace value as the value of the Ace card
                if (aceValueChoice == 1) {
                    playerCValue[1] = 1;
                } else if (aceValueChoice == 2) {
                    playerCValue[1] = 11;
                }
            }
        }
        /**
         * If the user's second card has a value of 10 and the first card was an
         * Ace with value 1, automatically change the value of the Ace to 11 to
         * give the player a 21
         */
        if (playerCValue[0] == 1 && (playerCValue[1] == 10)) {
            playerCValue[0] = 11;
            System.out.println("Your Ace card that had a value of 1 has been changed to have a value of 11 to give you a total of 21.");
        }

        //Calculate the player's total and display it (also, display a message that the player has stood if their total is already 21)
        playerTotal = playerCValue[0] + playerCValue[1];
        if (playerTotal == 21) {
            System.out.println("Your total is: " + playerTotal + ". You stand.");
        } else {
            System.out.println("Your total is: " + playerTotal);
        }
        //Give the dealer the second and fourth card in the shuffled deck by using the random index positions generated in the shuffleDeck method
        dealerCard[0] = cardNames[shuffleDeck[1]];
        dealerCard[1] = cardNames[shuffleDeck[3]];
        dealerCValue[0] = cardValues[shuffleDeck[1]];
        dealerCValue[1] = cardValues[shuffleDeck[3]];
        //Only display the dealer's first card and print that the second card is hidden
        System.out.println("\nThe dealer's first card is: " + dealerCard[0]);
        System.out.println("The dealer's second card is hidden.");
        //Calculate the dealer's total and their known total and also display the dealer's knwon total
        dealerTotal = dealerCValue[0] + dealerCValue[1];
        int dealerKnownTotal = dealerCValue[0];
        System.out.println("The dealer's known total is: " + dealerKnownTotal);
    }

    //This method takes the user's choice of hitting or standing if their total is not already 21
    public static void userOption() {
        do {
            if (playerTotal != 21) {
                //Get the value the user would like to use the ace as and ensure it is valid
                boolean flag7;
                do {
                    flag7 = false;
                    try {
                        userChoice = Integer.parseInt(JOptionPane.showInputDialog("Would you like to hit or stand? Choose an option by entering the corresponding number:\n"
                                + "1. Hit\n2. Stand"));
                    } catch (NumberFormatException g) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flag7 = true;
                    }
                    if ((userChoice != 1 && userChoice != 2) && flag7 != true) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                        flag7 = true;
                    }
                } while (flag7 == true);
                //Calls the hit or stand method 
                userHitorStand();
            }
        } while (playerTotal < 21 && userChoice == 1);
    }

    //This method gets a new card for the user if they choose to hit, if they choose to stand the user's cards and card total stay the same
    public static void userHitorStand() {
        //If the user chooses to hit a new card is given to the player
        if (userChoice == 1) {
            playerCardNum = playerCardNum + 1;
            playerCounter = playerCounter + 2;
            playerCard[playerCardNum] = cardNames[shuffleDeck[playerCounter]];
            playerCValue[playerCardNum] = cardValues[shuffleDeck[playerCounter]];
            System.out.println("\nYou got a(n): " + playerCard[playerCardNum]);
            /**
             * If the user's new card is an Ace, automatically give the card a
             * value of 11 if it results in a 21. If an Ace value of 11 will
             * result in a bust, automatically give the Ace a value of 1. Let
             * the player decide what value their Ace holds (1 or 11) if none of
             * the above occur
             */
            if (playerCard[playerCardNum].equals(cardNames[36]) || playerCard[playerCardNum].equals(cardNames[37]) || playerCard[playerCardNum].equals(cardNames[38]) || playerCard[playerCardNum].equals(cardNames[39])) {
                if (playerTotal + 11 > 21) {
                    playerCValue[playerCardNum] = 1;
                } else if (playerTotal + 11 == 21) {
                    playerCValue[playerCardNum] = 11;
                } else {
                    //Get the value the user would like to use the ace as and ensure it is valid
                    boolean flag8;
                    do {
                        flag8 = false;
                        try {
                            aceValueChoice = Integer.parseInt(JOptionPane.showInputDialog("Your card was an " + playerCard[playerCardNum] + ". Would you like the card to have a value of 1 or 11? "
                                    + "Enter the corresponding number to the option you would like to choose.\n1. 1\n2. 11"));
                        } catch (NumberFormatException k) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            flag8 = true;
                        }
                        if ((aceValueChoice != 1 && aceValueChoice != 2) && flag8 != true) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid option.", "ERROR", JOptionPane.ERROR_MESSAGE);
                            flag8 = true;
                        }
                    } while (flag8 == true);
                    //Use the players choice for the ace value as the card value
                    if (aceValueChoice == 1) {
                        playerCValue[playerCardNum] = 1;
                    } else if (aceValueChoice == 2) {
                        playerCValue[playerCardNum] = 11;
                    }
                }
            }
            //Calculate the new player total 
            playerTotal = playerTotal + playerCValue[playerCardNum];

            //If the player has an Ace card that has a value of 11 and the player's total has become greater than 21, change the Ace with the value of 11 to a 1 so the player does not bust
            if ((playerCValue[0] == 11 || playerCValue[1] == 11 || playerCValue[playerCardNum] == 11) && playerTotal > 21) {
                //Print the message to the user to make it clear what has happened
                System.out.println("Your Ace card that had a value of 11 has been changed to have a value of 1 to prevent you from busting.");
                //Change the value of the Ace card that has a value of 11 and calculate the new player total
                if (playerCValue[0] == 11) {
                    playerCValue[0] = 1;
                    playerTotal = (playerTotal - 11) + playerCValue[0];

                }
                if (playerCValue[1] == 11) {
                    playerCValue[1] = 1;
                    playerTotal = (playerTotal - 11) + playerCValue[1];

                }
                if (playerCValue[playerCardNum] == 11) {
                    playerCValue[playerCardNum] = 1;
                    playerTotal = (playerTotal - 11) + playerCValue[playerCardNum];
                }
            }

            //If one of the user's cards is an Ace with a value of 1, automatically change the card's value to 11 if it results in a 21
            if ((playerCValue[0] == 1 || (playerCValue[1] == 1) || playerCValue[playerCardNum] == 1) && playerTotal + 10 == 21) {
                if (playerCValue[0] == 1) {
                    playerTotal = playerTotal + 10;
                    System.out.println("Your Ace card that had a value of 1 has been changed to have a value of 11 to give you a 21.");
                } else if (playerCValue[1] == 1) {
                    playerTotal = playerTotal + 10;
                    System.out.println("Your Ace card that had a value of 1 has been changed to have a value of 11 to give you a 21.");
                } else if (playerCValue[playerCardNum] == 1) {
                    playerTotal = playerTotal + 10;
                    System.out.println("Your Ace card that had a value of 1 has been changed to have a value of 11 to give you a 21.");
                }
            }

            //If the player does not bust after hitting, display their new total (also, display a message that the player has stood if their total is already 21)
            if (playerTotal < 21) {
                System.out.println("Your new total is: " + playerTotal);
            } else if (playerTotal == 21) {
                System.out.println("Your new total is: " + playerTotal + ". You stand.");
                //If the player busts after hitting, display their new total and dislay a message that they lost    
            } else {
                System.out.println("Your new total is: " + playerTotal);
                System.out.println("Bust! You lost.");
                //Add a loss to the total user losses and calculate how much money the user has after losing their bet
                losses = losses + 1;
                money = money - betValue;
            }
        //Display a message if the user chose to stand and their current total
        } else {
            System.out.println("\nYou chose to stand. Your total is still: " + playerTotal);
        }
    }

    //This method decides whether the dealer has to hit or stand and performs the consequences 
    public static void dealerHitorStand() {
        //Reveal the dealer's hidden card and the dealer's actual total if the user has not busted
        if (playerTotal <= 21) {
            System.out.println("\nThe dealer's hidden card was: " + dealerCard[1]);
            System.out.println("Therefore, the dealer's actual total is: " + dealerTotal);
        }
        //If the dealer's total is less than 17 and the user has not busted keep giving the dealer another card 
        do {
            if (dealerTotal < 17 && playerTotal <= 21) {
                dealerCounter = dealerCounter + 2;
                dealerCardNum = dealerCardNum + 1;
                dealerCard[dealerCardNum] = cardNames[shuffleDeck[dealerCounter]];
                dealerCValue[dealerCardNum] = cardValues[shuffleDeck[dealerCounter]];
                //If the dealer's new card is an Ace give it a value of 11, if that results in a bust, give the Ace a value of 1
                if (dealerCard[dealerCardNum].equals(cardNames[36]) || dealerCard[dealerCardNum].equals(cardNames[37]) || dealerCard[dealerCardNum].equals(cardNames[38]) || dealerCard[dealerCardNum].equals(cardNames[39])) {
                    if (dealerTotal + 11 > 21) {
                        dealerCValue[dealerCardNum] = 1;
                    } else {
                        dealerCValue[dealerCardNum] = 11;
                    }
                }
                //Calculate the new dealer total 
                dealerTotal = dealerTotal + dealerCValue[dealerCardNum];
                //Print out that the dealer had to hit and what card they got, also print out the dealer's total
                System.out.println("\nThe dealer must hit. The dealer got a(n): " + dealerCard[dealerCardNum]);
                System.out.println("The dealer's total is now: " + dealerTotal);
                
                //If the dealer has an Ace card that has a value of 11 and the dealer's total has become greater than 21, change the Ace with the value of 11 to a 1 so the dealer does not bust
                if ((dealerCValue[0] == 11 || dealerCValue[1] == 11 || dealerCValue[dealerCardNum] == 11) && dealerTotal > 21) {
                    //Change the value of the Ace that has a value of 11 and print out a message making it clear to the user
                    if (dealerCValue[0] == 11) {
                        dealerCValue[0] = 1;
                        System.out.println("The dealer's Ace card that had a value of 11 has been changed to have a value of 1 to prevent the dealer form busting.");
                        //Calculate the dealer's new total
                        dealerTotal = (dealerTotal - 11) + dealerCValue[0];
                        System.out.println("Therefore, the dealer's total is now: " + dealerTotal);
                    }
                    if (dealerCValue[1] == 11) {
                        dealerCValue[1] = 1;
                        System.out.println("The dealer's Ace card that had a value of 11 has been changed to have a value of 1 to prevent the dealer form busting.");
                        //Calculate the dealer's new total
                        dealerTotal = (dealerTotal - 11) + dealerCValue[0];
                        System.out.println("Therefore, the dealer's total is now: " + dealerTotal);
                    }
                    if (dealerCValue[dealerCardNum] == 11) {
                        dealerCValue[dealerCardNum] = 1;
                        System.out.println("The dealer's Ace card that had a value of 11 has been changed to have a value of 1 to prevent the dealer from busting.");
                        //Calculate the dealer's new total 
                        dealerTotal = (dealerTotal - 11) + dealerCValue[dealerCardNum];
                        System.out.println("Therefore, the dealer's total is now: " + dealerTotal);
                    }
                }
                //If the dealer busts, print out a message stating that the dealer busted and the user wins
                if (dealerTotal > 21) {
                    System.out.println("The dealer busted. You win!");
                    //Add a win to the total user wins and calculate how much money the user has after winning their bet
                    wins = wins + 1;
                    money = money + betValue;
                    if (playerTotal == 21) {
                        //If the player had a 21 (and they won), add the bet value onto their total again (double earnings)
                        money = money + betValue;
                    }
                }
            }
        } while (dealerTotal < 17 && playerTotal <= 21);
        /**
         * After escaping the first loop the dealer has to have a total greater
         * than 17, therefore, if the dealer and the player have not busted,
         * print out a message stating that the dealer had to stand.
         */
        if (dealerTotal <= 21 && playerTotal <= 21) {
            System.out.println("The dealer must stand.");
        }
    }

    //This method will decide whether the user or dealer wins when no one busts
    public static void resultNoBust() {
        //If the player's total is greater than the dealer's total and no one has busted, print out a message saying the dealer lost and the user won
        if (playerTotal > dealerTotal && playerTotal <= 21 && dealerTotal <= 21) {
            System.out.println("\nThe dealer lost! You have won!");
            //Add a win to the total user wins and calculate how much money the user has after winning their bet
            wins = wins + 1;
            money = money + betValue;
            //If the player had a 21 (and they won), add the bet value onto their total again (double earnings)
            if (playerTotal == 21) {
                money = money + betValue;
            }
            //If the dealer's total is greater than the player's total and no one has busted, print out a message saying the dealer won and the user lost
        } else if (dealerTotal > playerTotal && playerTotal <= 21 && dealerTotal <= 21) {
            System.out.println("\nThe dealer won! You have lost!");
            //Add a loss to the total user losses and calculate how much money the user has after losing their bet
            losses = losses + 1;
            money = money - betValue;
            //If the dealer's total and the player's total are equal, print out a message saying there was a tie
        } else if (dealerTotal == playerTotal && playerTotal <= 21 && dealerTotal <= 21) {
            System.out.println("\nYou and the dealer have tied!");
            //Add a tie to the total ties
            ties = ties + 1;
        }
    }

    //This method displays the scoreboard and user's money
    public static void scoreboardMoney() {
        //Initialize decimal format      
        DecimalFormat twoDigit = new DecimalFormat("$##0.00");
        //Print out the user's money
        System.out.println("\nYou now have " + twoDigit.format(money));
        //Print out the total wins, losses, and ties
        System.out.println("\n===========");
        System.out.println("WINS: " + wins);
        System.out.println("LOSSES: " + losses);
        System.out.println("TIES: " + ties);
        System.out.println("===========");
    }
}
