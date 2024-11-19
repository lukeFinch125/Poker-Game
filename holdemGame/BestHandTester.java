public class BestHandTester {
    public static void main(String args[]) {
        BestHandTest test = new BestHandTest();

        Card[] hand = new Card[7];
        Card card1 = new Card(2,5);
        Card card2 = new Card(2,5);
        Card card3 = new Card(2,5);
        Card card4 = new Card(4, 2);
        Card card5 = new Card(2,2);
        Card card6 = new Card(2,7);
        Card card7 = new Card(2,8);

        hand[0] = card1;
        hand[1] = card2;
        hand[2] = card3;
        hand[3] = card4;
        hand[4] = card5;
        hand[5] = card6;
        hand[6] = card7;

        System.out.println(test.evaluateBestHand(hand));
    }
}
