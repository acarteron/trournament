package re.chasam.connector.impl

import com.mongodb.MongoException
import com.mongodb.client.*
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import org.bson.Document
import re.chasam.connector.Connector
import re.chasam.connector.Database
import re.chasam.models.impl.Player


class Mongo : Connector {
    override val defaultDatabase = Database(
        System.getenv("SCHEME") ?: "mongodb",
        System.getenv("HOST") ?: "127.0.0.0",
        (System.getenv("PORT") ?: "27017").toInt(),
        System.getenv("NAME") ?: "tournament",
        System.getenv("COLLECTION") ?: "users")

    private var uri = "${defaultDatabase.scheme}://${defaultDatabase.host}:${defaultDatabase.port}"
    private val mongoClient: MongoClient = MongoClients.create(uri)
    private val database: MongoDatabase = mongoClient.getDatabase(defaultDatabase.name)
    private val mCollection: MongoCollection<Document> = database.getCollection(defaultDatabase.collection)
    init {
        println("init tournament")
    }
    override fun insertOrUpdate(name: String, score: Int, rank : Int) {
        val query = Document().append("name", name)
        val updates = Updates.combine(
            Updates.set("score", score),
            Updates.currentTimestamp("lastUpdated")
        )
        val options = UpdateOptions().upsert(true)
        try {
            val result = mCollection.updateOne(query, updates, options)
            println("Modified document count: " + result.modifiedCount)
            println("Upserted id: " + result.upsertedId) // only contains a value when an upsert is performed
        } catch (me: MongoException) {
            System.err.println("Unable to update due to an error: $me")
        }
    }
    override fun dropAll() {
        try {
            val result = mCollection.deleteMany(Document())
            println("Dropped collection : $result")
        } catch (me: MongoException) {
            System.err.println("Unable to drop due to an error: $me")
        }
    }
    override fun listAll(): List<Player> {
        try {

            val projectionFields = Projections.fields(
                Projections.include("name", "score", "rank"),
                Projections.excludeId()
            )
            val doc = mCollection.find()
                .projection(projectionFields)
                .sort(Sorts.descending("score"))
            val players = mutableListOf<Player>()
            doc.forEach { it: Document ->
                players.add(Player(it.getString("name"), it.getInteger("score"), it.getInteger("rank")))
            }
            return players
        } catch (me: MongoException) {
            System.err.println("Unable to drop due to an error: $me")
            return emptyList()
        }
    }
}