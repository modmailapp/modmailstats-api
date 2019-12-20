package codes.piyush.modmailstats.core.api

import codes.piyush.modmailstats.core.database.Database
import express.DynExpress
import express.http.RequestMethod
import express.http.request.Request
import express.http.response.Response
import express.utils.MediaType
import express.utils.Status

class APIBindings(val database: Database) {
    @DynExpress(context = "/stats", method = RequestMethod.GET)
    fun sendStats(req: Request, res: Response) {
        println(database.getStats().toString())
        val stats: HashMap<String, Int>? = database.getStats()

        if(stats == null) {
            res.sendStatus(Status._501)
            return
        }

        var _stats = "{"

        for((month, instances) in stats) {
            _stats += "\"$month\": \"$instances\","
        }
        _stats = _stats.trimEnd(',')
        _stats += "}"

        res.setContentType(MediaType._json)
        res.send("{\"success\": true, \"data\": ${_stats}}")
        return
    }
}