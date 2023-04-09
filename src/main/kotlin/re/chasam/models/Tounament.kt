package re.chasam.models

import re.chasam.models.impl.Player

interface Tournament {
    fun getPlayer(name : String) : Player?
    fun addOrUpdate(player : Player)
    fun addOrUpdate(name : String, score : Int = 0)
    fun clean()
    fun updateRank()
}
