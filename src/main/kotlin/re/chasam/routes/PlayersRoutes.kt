package re.chasam.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import re.chasam.models.impl.TournamentImpl
import re.chasam.models.impl.Player

fun Route.playersRouting() {

    val tournamentImpl by inject<TournamentImpl>()
    route("/players") {
        get {
            if (tournamentImpl.players.isNotEmpty()) {
                call.respond(tournamentImpl.players)
            } else {
                call.respondText("No players found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            println("status $id")
            val player = tournamentImpl.getPlayer(id)
            if (player != null) {
                call.respond(player)
            } else {
                call.respondText("No player found", status = HttpStatusCode.NotFound)
            }
        }
        post {
            val player = call.receive<Player>()
            println("post $player")
            tournamentImpl.addOrUpdate(Player(player.name))
            call.respondText("player stored correctly", status = HttpStatusCode.Created)
        }
        patch("{id?}") {
            val id = call.parameters["id"] ?: return@patch call.respondText("Bad Request", status = HttpStatusCode.BadRequest)
            tournamentImpl.getPlayer(id) ?: return@patch call.respondText("No player found", status = HttpStatusCode.NotFound)
            val player = call.receive<Player>()
            println("post $id $player")
            tournamentImpl.addOrUpdate(id, player.score)
            call.respondText("score updated correctly", status = HttpStatusCode.OK)
        }
        delete("") {
            tournamentImpl.clean()
            call.respondText("players removed correctly", status = HttpStatusCode.OK)
        }
    }
}