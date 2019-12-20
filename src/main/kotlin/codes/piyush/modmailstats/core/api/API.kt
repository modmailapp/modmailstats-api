package codes.piyush.modmailstats.core.api

import codes.piyush.modmailstats.core.database.Database
import express.Express

class ModmailStatsAPI(val databse: Database, val port: String) {
    fun init() {
        val app: Express = Express()
        app.bind(APIBindings(databse))
        println("OP")
        app.listen(port.toInt())
    }
}