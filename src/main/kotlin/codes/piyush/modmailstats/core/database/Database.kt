package codes.piyush.modmailstats.core.database

import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

data class ModmailStats(val month: String, val instances: Int)

class Database {
    private lateinit var client: MongoClient
    private lateinit var database: MongoDatabase

    constructor(uri: MongoClientURI, dbName: String) {
        this.client = KMongo.createClient(uri)
        this.database = this.client.getDatabase(dbName)
    }

    fun insertStats(month: String, instances: Int) {
        val coll = database.getCollection<ModmailStats>("stats")

        coll.insertOne(ModmailStats(month, instances))
    }

    fun getStats(): HashMap<String, Int>? {
        try {
            val coll = database.getCollection<ModmailStats>("stats")

            val documents = coll.find()
            val data = HashMap<String, Int>()

            for (document in documents) {
                data.put(document.month, document.instances)
            }

            return data
        } catch (e: Exception) {
            println(e)
            return null
        }
    }
}