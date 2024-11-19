 import java.util.*;

public class PlayerAction {
    private Round round;
    private Lobby lobby;
    private Scanner scanner;
    private int button;
    private Player[] players;
    private Player[] temp;
    private int highestBet;
    private boolean actionOpen;
    private boolean[] hasGone;
    

    public PlayerAction(Round round, Lobby lobby) {
        this.round = round;
        this.lobby = lobby;
        scanner = new Scanner(System.in);
        this.button = round.getButton();
    }

    public void setRoundOrder() {
        players = lobby.getPlayers();
        button = round.getButton();
        temp = new Player[lobby.players.length];
        int tempInt = 0;

        for(int i = button ; i < players.length; i++) {
            temp[tempInt] = players[i];
            tempInt++;
        }

        for(int i = 0; i < button; i++) {
            temp[tempInt] = players[i];
            tempInt++;
        }

        System.out.println("Round order;");

        for(int i = 0; i < temp.length; i++) {
            System.out.println(temp[i].getName());
        }
    }

    public void getAction() {
        highestBet = 0;
        actionOpen = true;
        int actionCase;
        int bet;
        int raise;
        hasGone = new boolean[temp.length];

            // Reset highestBet and open the action
        highestBet = 0;
        actionOpen = true;

        // Reset hasPlayed for all players at the start of the round
        for (Player player : temp) {
            player.setHasPlayed(false);
        }
    
        while (actionOpen) {
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].getinHand() && (temp[i].getHasPlayed() == false || temp[i].getCurrentBet() != highestBet)) {
                    System.out.println(temp[i].getName() + " action:");
                    System.out.println("Check[0], Fold[1], Bet[2], Call[3], Raise[4]");
                    actionCase = scanner.nextInt();
    
                    switch (actionCase) {
                        case 0: // Check
                            if (highestBet == 0) {
                                temp[i].check();
                                temp[i].setHasPlayed(true); // Mark action as complete
                            } else {
                                System.out.println("Cannot check, there is an active bet.");
                                i--; // Retry the same player's turn
                            }
                            break;
    
                        case 1: // Fold
                            temp[i].fold();
                            temp[i].setHasPlayed(true); // Mark action as complete
                            System.out.println("Player folded.");
                            break;
    
                        case 2: // Bet
                            if (highestBet == 0) {
                                System.out.println("Enter bet amount:");
                                bet = scanner.nextInt();
                                highestBet = bet;
                                temp[i].bet(bet, round);
                                temp[i].setCurrentBet(bet);
                                temp[i].setHasPlayed(true); // Mark action as complete
                            } else {
                                System.out.println("Cannot bet, there is already an active bet.");
                                i--; // Retry the same player's turn
                            }
                            break;
    
                        case 3: // Call
                            if (highestBet > 0) {
                                temp[i].call(highestBet, round);
                                temp[i].setCurrentBet(highestBet);
                                temp[i].setHasPlayed(true); // Mark action as complete
                            } else {
                                System.out.println("No bet to call.");
                                i--; // Retry the same player's turn
                            }
                            break;
    
                        case 4: // Raise
                            if (highestBet > 0) {
                                System.out.println("Enter raise amount:");
                                raise = scanner.nextInt();
                                temp[i].raise(highestBet, raise, round);
                                highestBet += raise;
                                temp[i].setCurrentBet(highestBet);
                                temp[i].setHasPlayed(true); // Mark action as complete
                            } else {
                                System.out.println("No bet to raise. You must bet instead.");
                                i--; // Retry the same player's turn
                            }
                            break;
    
                        default:
                            System.out.println("Invalid action. Please try again.");
                            i--; // Retry the same player's turn
                            break;
                    }
                }
            }
    
            // Check if all players have acted or are in sync with the highest bet
            boolean allPlayersHaveBetOrFolded = true;
            for (Player player : temp) {
                if (player.getinHand() && (player.getHasPlayed() == false || player.getCurrentBet() != highestBet)) {
                    allPlayersHaveBetOrFolded = false;
                    break;
                }
            }
    
            if (allPlayersHaveBetOrFolded) {
                actionOpen = false; // Exit loop if all actions are complete
            }
        }
    }
    

}  

