public class RoundTest {
    public static void main(String args[]) {
        Lobby lobby = new Lobby();
        Round round = new Round(lobby);
        PlayerAction action = new PlayerAction(round, lobby);

        lobby.makePlayers();
        round.startRound(action);

    }
}
