import java.util.*;

public class Round {
    private Lobby lobby;
    private int pot = 0;
    private int button = 0;
    private Card[] board;
    private Scanner scanner;
    private PlayerAction action;
    private BestHandTest besthand;

    public Round(Lobby lobby) {
        this.lobby = lobby;
        board = new Card[5];
        scanner = new Scanner(System.in);
        besthand = new BestHandTest();
    }

    public void startRound(PlayerAction action) {
        this.action = action;
        System.out.println("Round started");
        lobby.clearPlayersCards();
        dealPlayerCards();
        System.out.println("cards delt");
        generateButton();
        System.out.println("Button generated");
        action.setRoundOrder();
        action.getAction();
        dealflop();
        action.getAction();
        dealTurn();
        action.getAction();
        dealRiver();
        action.getAction();
        checkHands();
    }


    public void dealPlayerCards() {
        Player[] players = lobby.getPlayers(); // import players from lobby
        Deck deck = lobby.getDeck(); //get deck from lobby

        deck.shuffle(); //shuffle deck

        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < 2; j++) {
                Card card = deck.deal();
                players[i].addCard(card); // Add to player's hand
            }
        }
    }

    public void dealflop() {
        Deck deck = lobby.getDeck();

        for(int i = 0; i < 3; i++) {
            board[i] = deck.deal();
        }
        printBoard();
        
    }

    public void dealTurn() {
        Deck deck = lobby.getDeck();

        board[3] = deck.deal();

        printBoard();
    }
    
    public void dealRiver() {
        Deck deck = lobby.getDeck();

        board[4] = deck.deal();

        printBoard();
    }

    public void printBoard() {
        for(int i = 0; i < board.length; i++) {
            System.out.println(board[i]);
        }
    }

    public void addtoPot(int amount) {
        pot += amount;
    }

    public void generateButton() {
        Random random = new Random();
        Player[] players = lobby.getPlayers();
        int count = players.length;
        setButton(random.nextInt((count - 0) + 1) + 0);
    }

    public int checkHands() {
        String[] playerResults = new String[lobby.players.length];
        Card[] boardTemp = new Card[7];
        for(int i = 0; i < board.length; i++) {
            boardTemp[i] = board[i];
        }

        for(int i = 0; i < lobby.players.length; i++) {
            boardTemp[5] = lobby.players[i].getFirstCard();
            boardTemp[6] = lobby.players[i].getSecondCard();
            System.out.println(besthand.evaluateBestHand(boardTemp));
        }

        return 0;
    }

    


    //getters and setters

    public Card[] getBoard() {
        return board;
    }

    public void setBoard(Card[] board) {
        this.board = board;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }

}
