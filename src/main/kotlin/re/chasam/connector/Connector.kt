package re.chasam.connector

import re.chasam.models.impl.Player

data class Database (
    val scheme : String,
    val host : String,
    val port : Int,
    val name : String,
    val collection : String,
)

interface Connector {
    val defaultDatabase: Database
    fun insertOrUpdate(name: String, score: Int = 0, rank : Int = 0)
    fun dropAll()
    fun listAll(): List<Player>
}