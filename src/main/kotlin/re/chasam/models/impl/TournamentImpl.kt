package re.chasam.models.impl

import org.koin.core.component.KoinComponent
import re.chasam.connector.impl.Mongo
import re.chasam.models.Tournament

class TournamentImpl : Tournament, KoinComponent {
    var players = mutableListOf<Player>()
    private val mg = Mongo()
    init {
        players = mg.listAll().toMutableList()
        updateRank()
    }
    override fun getPlayer(name: String) : Player? {
        val player = Player(name)
        val index = players.indexOf(player)
        println("index: $index")
        if (index == -1)
            return null
        return players[index]
    }
    override fun addOrUpdate(player : Player) {
        val index = players.indexOf(player)
        println("index: $index")
        if (index == -1)
            players.add(player)
        else
            players[index] = player
        println(players)
        mg.insertOrUpdate(player.name, player.score)
        updateRank()
    }
    override fun addOrUpdate(name : String, score : Int){
        val player = Player(name, score)
        addOrUpdate(player)
    }
    override fun clean() {
        players.clear()
        mg.dropAll()
    }
    override fun updateRank() {
        players.sortDescending()
        var rank = 0
        var score = 0
        for (player in players) {
            if (player.score != score)
                rank ++
            player.rank = rank
            score = player.score
        }
    }
}