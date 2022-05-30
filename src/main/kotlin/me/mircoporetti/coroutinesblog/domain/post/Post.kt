package me.mircoporetti.coroutinesblog.domain.post

import org.bson.types.ObjectId

class Post(
    var id: ObjectId,
    var message: String,
    var comments: MutableList<Comment>,
    var likes: Long,
    var dislikes: Long
    ) {

    fun rate(rating: Rating) {
        if (rating.equals(Rating.LIKE)) {
            ++likes
        } else {
            ++dislikes
        }
    }
}