import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BestHandTest {



    public String evaluateBestHand(Card[] cards) {
        if (cards.length != 7) {
            throw new IllegalArgumentException("You must provide exactly 7 cards.");
        }

        Arrays.sort(cards, Comparator.comparingInt(Card::getRank).reversed());

        if (isStraightFlush(cards)) return "Straight Flush";
        if (isFourOfAKind(cards)) return "Four of a Kind";
        if (isFullHouse(cards)) return "Full House";
        if (isFlush(cards)) return "Flush";
        if (isStraight(cards)) return "Straight";
        if (isThreeOfAKind(cards)) return "Three of a Kind";
        if (isTwoPair(cards)) return "Two Pair";
        if (isPair(cards)) return "Pair";

        return "High Card";
    }

    private static boolean isStraightFlush(Card[] cards) {
        return isFlush(cards) && isStraight(cards);
    }

    private static boolean isFourOfAKind(Card[] cards) {
        Map<Integer, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.values().contains(4);
    }

    private static boolean isFullHouse(Card[] cards) {
        Map<Integer, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.values().contains(3) && rankCounts.values().contains(2);
    }

    private static boolean isFlush(Card[] cards) {
        Map<Integer, Integer> suitCounts = new HashMap<>();
        for (Card card : cards) {
            suitCounts.put(card.getSuit(), suitCounts.getOrDefault(card.getSuit(), 0) + 1);
        }
        return suitCounts.values().stream().anyMatch(count -> count >= 5);
    }

    private static boolean isStraight(Card[] cards) {
        Set<Integer> uniqueRanks = new HashSet<>();
        for (Card card : cards) {
            uniqueRanks.add(card.getRank());
        }

        List<Integer> sortedRanks = new ArrayList<>(uniqueRanks);
        Collections.sort(sortedRanks);

        int consecutive = 0;
        for (int i = 0; i < sortedRanks.size(); i++) {
            if (i > 0 && sortedRanks.get(i) == sortedRanks.get(i - 1) + 1) {
                consecutive++;
                if (consecutive >= 4) return true;
            } else {
                consecutive = 0;
            }
        }

        // Check for a low Ace straight (A-2-3-4-5)
        return uniqueRanks.contains(14) && uniqueRanks.contains(2) &&
               uniqueRanks.contains(3) && uniqueRanks.contains(4) && uniqueRanks.contains(5);
    }

    private static boolean isThreeOfAKind(Card[] cards) {
        Map<Integer, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.values().contains(3);
    }

    private static boolean isTwoPair(Card[] cards) {
        Map<Integer, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.values().stream().filter(count -> count == 2).count() >= 2;
    }

    private static boolean isPair(Card[] cards) {
        Map<Integer, Integer> rankCounts = getRankCounts(cards);
        return rankCounts.values().contains(2);
    }

    private static Map<Integer, Integer> getRankCounts(Card[] cards) {
        Map<Integer, Integer> rankCounts = new HashMap<>();
        for (Card card : cards) {
            rankCounts.put(card.getRank(), rankCounts.getOrDefault(card.getRank(), 0) + 1);
        }
        return rankCounts;
    }

}
