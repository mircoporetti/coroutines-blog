package me.mircoporetti.coroutinesblog.domain.post

import org.bson.types.ObjectId

class Post(
    private var id: ObjectId,
    private var message: String,
    private var comments: List<Comment>,
    private var likes: Long,
    private var dislikes: Long
    ) {

    fun rate(rating: Rating) {
        if (rating.equals(Rating.LIKE)) {
            ++likes
        } else {
            ++dislikes
        }
    }
}