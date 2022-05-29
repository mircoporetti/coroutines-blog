package me.mircoporetti.coroutinesblog.mongoadapter.post

import io.micronaut.core.annotation.Introspected
import me.mircoporetti.coroutinesblog.domain.post.Comment
import org.bson.types.ObjectId

@Introspected
class MongoPost(
    var id: ObjectId,
    var message: String,
    var comments: MutableList<Comment>,
    var likes: Long,
    var dislikes: Long
) {

    fun addComment(mongoComment: Comment): MongoPost {
        comments.add(mongoComment)
        return MongoPost(id, message, comments, likes, dislikes)
    }
}