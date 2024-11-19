import java.util.*;

public class Player {
    private Round round;
    private String name;
    private Card[] hand;
    private int money;
    private boolean inHand;
    private int numberOfCards;
    private int currentBet = 0;
    private boolean hasPlayed = false;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.hand = new Card[2];
        this.numberOfCards = 0;
        this.inHand = true;
    }

    public Player(String name) {
        this.name = name;
        this.money = 20;
        this.hand = new Card[2];
        this.numberOfCards = 0;
        this.inHand = true;
    }

    public Player(String name, int money, int sizeOfHand) {
        this.name = name;
        this.money = money;
        this.hand = new Card[sizeOfHand];
        this.numberOfCards = 0;
        this.inHand = true;
    }

    public void check() {
        setHasPlayed(false);
        System.out.println("player checks");
    }

    public void bet(int amount, Round round) {
        setHasPlayed(false);
        subMoney(amount);
        round.addtoPot(amount);
        System.out.println("Current Money: " + getMoney());
    }

    public void call(int currentBet, Round round) {
        setHasPlayed(false);
        subMoney(currentBet);
        round.addtoPot(currentBet);
        System.out.println("Current money: " + getMoney());
    }

    public void setFirstCard(Card card) {
        card = hand[0];
    }

    public void setSecondCard(Card card) {
        card = hand[1];
    }

    public Card getFirstCard() {
        return hand[0];
    }

    public Card getSecondCard() {
        return hand[1];
    }

    public void fold() {
        setinHand(false);
        setHasPlayed(false);
    }

    public void raise(int currentBet, int raise, Round round) {
        setHasPlayed(false);
        setCurrentBet(raise);
        subMoney(raise);
        round.addtoPot(raise);
        System.out.println("Current money: " + getMoney());
    }


    public void subMoney(int amount) {
        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void addCard(Card card) {
        if (numberOfCards < hand.length) {
            hand[numberOfCards] = card;
            numberOfCards++;
        } else {
            throw new IllegalStateException("Cannot add more cards to the player's hand.");
        }
    }
    

    public void showHand() {
        for (Card card : hand) {
            System.out.println(card);
        }
    }

    @Override
    public String toString() {
        String toReturn = getName() + " " + getMoney() + " " + getinHand();
        return toReturn;
    }

    //getters and setters

    public void setHasPlayed(boolean hasPlayed) {
        this.hasPlayed = hasPlayed;
    }

    public boolean getHasPlayed() {
        return hasPlayed;
    }


    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public boolean getinHand() {
        return inHand;
    }

    public void setinHand(boolean inHand) {
        this.inHand = inHand;
    }

    public int getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }
}
