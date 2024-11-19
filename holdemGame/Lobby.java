import java.util.*;

public class Lobby {
    private Scanner scanner = new Scanner(System.in);
    public Player[] players;
    private int numberOfPlayers;
    private Deck deck;
    public Round round;
    public PlayerAction action;

    public Lobby() {
        deck = new Deck();
        System.out.println("Lobby created");
    }

    public void startRound() {
       Round round = new Round(this);
       action = new PlayerAction(round, this);
    }

    public void makePlayers() {
        System.out.println("Number of players:");
        setNumberOfPlayers(scanner.nextInt());
        scanner.nextLine();
        players = new Player[numberOfPlayers];
        for (int i = 0; i < players.length; i++) {
            System.out.println("Enter name:");
            players[i] = new Player(scanner.nextLine());
        }
    }

    public void clearPlayersCards() {
        for(int i = 0; i < players.length; i++) {
            players[i].setFirstCard(null);
            players[i].setSecondCard(null);
        }
    }

    public void printPlayers() {
        for (Player player : players) {
            System.out.println(player);
        }
    }

    public void newDeck() {
        deck = new Deck();
    }

    public void shuffle() {
        deck.shuffle();
    }

    // Getters and setters
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }
}
