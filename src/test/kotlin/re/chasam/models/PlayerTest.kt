package re.chasam.models

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class PlayerTest {

    @Test
    fun compareTo() {
        val expected1 = -1
        val expected2 = 1
        val expected3 = 0

        val player1 = Player("Player1", 1)
        val player2 = Player("Player2",2)
        val player3 = Player("Player3",1)
        val player4 = Player("Player1", 2)

        assertEquals(expected1, player1.compareTo(player2))
        assertEquals(expected2, player2.compareTo(player1))
        assertEquals(expected3, player3.compareTo(player1))
        assertEquals(expected2, player4.compareTo(player1))
    }

    @Test
    fun testEquals() {

        val player1 = Player("Player1", 1)
        val player2 = Player("Player2",2)
        val player3 = Player("Player3",1)
        val player4 = Player("Player1", 2)

        assertFalse(player1.equals(player2))
        assertTrue(player1.equals(player4))
        assertFalse(player3.equals(player1))

        assertNotEquals(player1, player2)
        assertEquals(player1, player4)
        assertNotEquals(player3, player1)
    }
}