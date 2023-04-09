package re.chasam.models

import re.chasam.models.impl.Player

interface Tournament {
    var players: MutableList<Player>
    fun getPlayer(name : String) : Player? {
        val player = Player(name)
        val index = players.indexOf(player)
        if (index == -1)
            return null
        return players[index]
    }
    fun addOrUpdate(player : Player) {
        val index = players.indexOf(player)
        println("index: $index")
        if (index == -1)
            players.add(player)
        else
            players[index] = player
        println(players)

        updateRank()
    }
    fun addOrUpdate(name : String, score : Int = 0) {
        val player = Player(name, score)
        addOrUpdate(player)
    }
    fun clean() {
        players.clear()
    }
    fun updateRank() {
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
