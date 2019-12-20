package codes.piyush.modmailstats

import codes.piyush.modmailstats.core.api.ModmailStatsAPI
import codes.piyush.modmailstats.core.database.Database
import com.mongodb.MongoClientURI
import java.io.File

fun main() {
    val configFile = File("config.txt").readLines()
    println(configFile)
    val database = Database(MongoClientURI(configFile[0]), configFile[1])
    println(database.getStats().toString())
    val modmailAPI = ModmailStatsAPI(database, configFile[2])
    modmailAPI.init()
}