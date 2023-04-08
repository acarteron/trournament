package re.chasam.models

import org.koin.core.component.KoinComponent
import re.chasam.connector.Mongo

interface Tournament {
    fun getPlayer(name : String) : Player?
    fun addOrUpdate(player : Player)
    fun addOrUpdate(name : String, score : Int = 0)
    fun clean()
}

class TournamentImpl : Tournament, KoinComponent {
    var players = mutableListOf<Player>()
    private val mg = Mongo()
    init {
        players = mg.listAll().toMutableList()
    }
    override fun getPlayer(name: String) : Player? {
        val player = Player(name)
        val index = players.indexOf(player)
        println("index: $index")
        if (index == -1)
            return null
        players[index].rank = index + 1
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
    }
    override fun addOrUpdate(name : String, score : Int){
        val player = Player(name, score)
        addOrUpdate(player)
    }
    override fun clean() {
        players.clear()
        mg.dropAll()
    }
}