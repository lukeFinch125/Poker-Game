public class Deck {
    private Card[] cards;
    private int top;

    public Deck() {
        cards = new Card[52];
        top = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 13; j++) {
                cards[i * 13 + j] = new Card(i, j);
            }
        }
    }

    public void shuffle() {
        for(int i = 0; i < 52; i++) {
            int j = (int)(Math.random() * 52);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card deal() {
        if(top == 52) {
            return null;
        }
        return cards[top++];
    }

    public int cardsLeft() {
        return 52 - top;
    }

    public void reset() {
        top = 0;
    }

    public void print() {
        for(int i = top; i < 52; i++) {
            System.out.println(cards[i]);
        }
    }

}
