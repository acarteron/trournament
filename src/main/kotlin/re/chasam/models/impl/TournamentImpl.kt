package re.chasam.models.impl

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import re.chasam.connector.Connector
import re.chasam.connector.impl.Mongo
import re.chasam.models.Tournament

class TournamentImpl : Tournament, KoinComponent {
    private val connector by inject<Connector>()
    override var players: MutableList<Player> = connector.listAll().toMutableList()

    init {
        updateRank()
    }
    override fun addOrUpdate(player : Player) {
        super.addOrUpdate(player)
        connector.insertOrUpdate(player.name, player.score)
    }
    override fun clean() {
        super.clean()
        connector.dropAll()
    }
}