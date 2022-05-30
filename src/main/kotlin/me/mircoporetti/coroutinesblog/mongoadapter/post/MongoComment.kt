package me.mircoporetti.coroutinesblog.mongoadapter.post

import io.micronaut.core.annotation.Introspected
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonProperty

@Introspected
data class MongoComment @BsonCreator constructor(
    @BsonProperty("author")
    var author: String,
    @BsonProperty("message")
    var message: String
)