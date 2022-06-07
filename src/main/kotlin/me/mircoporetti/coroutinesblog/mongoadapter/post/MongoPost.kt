package me.mircoporetti.coroutinesblog.mongoadapter.post

import io.micronaut.core.annotation.Introspected
import org.bson.BsonType
import org.bson.codecs.pojo.annotations.BsonCreator
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.codecs.pojo.annotations.BsonProperty
import org.bson.codecs.pojo.annotations.BsonRepresentation

@Introspected
data class MongoPost @BsonCreator constructor(
    @BsonId
    @BsonRepresentation(BsonType.OBJECT_ID)
    var id: String,
    @BsonProperty("message")
    var message: String,
    @BsonProperty("comments")
    var comments: MutableList<MongoComment>?,
    @BsonProperty("likes")
    var likes: Long,
    @BsonProperty("dislikes")
    var dislikes: Long
)