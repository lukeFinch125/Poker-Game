public class LobbyTest {
    public static void main (String args[]) {
        Lobby lobby = new Lobby();
        lobby.getNumberOfPlayers();
        lobby.makePlayers();
        lobby.printPlayers();
        Round round = new Round(lobby);
        PlayerAction action = new PlayerAction(round, lobby);
        round.startRound(action);
        
        
        for(int i = 0; i < lobby.getPlayers().length; i++) {
            lobby.players[i].showHand();
        }
    }

}
