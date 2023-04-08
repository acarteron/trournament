package re.chasam.connector

import com.mongodb.MongoException
import com.mongodb.client.*
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Sorts
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates
import org.bson.Document
import re.chasam.models.Player


class Mongo {
    data class Database (
        val scheme : String,
        val host : String,
        val port : Int = 27017,
        val name : String,
        val collection : String,
    )
    val defaultDatabase = Database("mongodb","127.0.0.1", 27017, "tournament", "users")

    private var uri = "${defaultDatabase.scheme}://${defaultDatabase.host}:${defaultDatabase.port}"

    private val mongoClient: MongoClient = MongoClients.create(uri)
    private val database: MongoDatabase = mongoClient.getDatabase(defaultDatabase.name)
    private val mCollection: MongoCollection<Document> = database.getCollection(defaultDatabase.collection)
    init {
        println("init tournament")
    }

//    fun insert(name: String, score: Int = 0) {
//        try {
//            val result = collection.insertOne(
//                Document()
//                    .append("name", name)
//                    .append("score", score)
//            )
//            println("Success! Inserted document id: " + result.insertedId)
//        } catch (me: MongoException) {
//            System.err.println("Unable to insert due to an error: $me")
//        }
//    }
    fun insertOrUpdate(name: String, score: Int = 0) {
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
    fun dropAll() {
        try {
            val result = mCollection.deleteMany(Document())
            println("deleted document count: " + result.deletedCount)
        } catch (me: MongoException) {
            System.err.println("Unable to drop due to an error: $me")
        }
    }
    fun listAll(): List<Player> {
        try {

            val projectionFields = Projections.fields(
                Projections.include("name", "score"),
                Projections.excludeId()
            )
            val doc = mCollection.find()
                .projection(projectionFields)
                .sort(Sorts.descending("score"))

            println("listed document count: ${doc.toList()}")
            val players = mutableListOf<Player>()
            doc.forEach { it: Document ->
                players.add(Player(it.getString("name"), it.getInteger("score")))
            }
            println("listed document count: $players")
            return players
        } catch (me: MongoException) {
            System.err.println("Unable to drop due to an error: $me")
            return emptyList()
        }
    }
}