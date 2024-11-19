import java.util.*;

public class main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        Lobby lobby = new Lobby();
        lobby.getNumberOfPlayers();
        lobby.makePlayers();
        int playing = 1;
        while(playing == 1) {
            Round round = new Round(lobby);
            PlayerAction action = new PlayerAction(round, lobby);
            lobby.clearPlayersCards();
            lobby.newDeck();
            lobby.shuffle();
            round.startRound(action);
            System.out.println("enter 1 to keep playing");
            playing = scanner.nextInt();
        }

    }
}
