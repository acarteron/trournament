package re.chasam.models.impl

import org.junit.Test
import kotlin.test.assertEquals

class TournamentImplTest {
    private var tournament = TournamentImpl()
    @Test
    fun getPlayer() {
        tournament.players = mutableListOf(
            Player("Player 1"),
            Player("Player 2", 1, 3),
            Player("Player 3"),
            Player("Player 4"))

        val player1 = Player("Player 1", 1)
        val player2 = Player("Player 2",2)
        val player3 = Player("Player 3",3)
        val player4 = Player("Player 4", 4)

        assertEquals(player1, tournament.getPlayer("Player 1"))
        assertEquals(player2, tournament.getPlayer("Player 2"))
        assertEquals(player3, tournament.getPlayer("Player 3"))
        assertEquals(player4, tournament.getPlayer("Player 4"))

        tournament.getPlayer("Player 2")?.let { assertEquals(1, it.score) }
        tournament.getPlayer("Player 2")?.let { assertEquals(3, it.rank) }
    }
    @Test
    fun updateRank() {
        tournament.players = mutableListOf(
            Player("Player 1", 30),
            Player("Player 2", 10),
            Player("Player 3",20),
            Player("Player 4",40),
            Player("Player 5", 30))

        tournament.updateRank()
        println(tournament.players)
        tournament.getPlayer("Player 1")?.let { assertEquals(2, it.rank) }
        tournament.getPlayer("Player 2")?.let { assertEquals(4, it.rank) }
        tournament.getPlayer("Player 3")?.let { assertEquals(3, it.rank) }
        tournament.getPlayer("Player 4")?.let { assertEquals(1, it.rank) }
        tournament.getPlayer("Player 5")?.let { assertEquals(2, it.rank) }
    }
}