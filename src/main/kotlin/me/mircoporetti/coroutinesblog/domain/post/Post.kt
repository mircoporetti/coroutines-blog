package me.mircoporetti.coroutinesblog.domain.post

class Post(
    var id: String?,
    var message: String,
    var comments: MutableList<Comment>?,
    var likes: Long,
    var dislikes: Long
    ) {

    fun addComment(comment: Comment) {
        comments?.add(comment)
    }

    fun rate(rating: Rating) {
        if (rating.equals(Rating.LIKE)) {
            ++likes
        } else {
            ++dislikes
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (message != other.message) return false
        if (comments != other.comments) return false
        if (likes != other.likes) return false
        if (dislikes != other.dislikes) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + message.hashCode()
        result = 31 * result + (comments?.hashCode() ?: 0)
        result = 31 * result + likes.hashCode()
        result = 31 * result + dislikes.hashCode()
        return result
    }

    override fun toString(): String {
        return "Post(id='$id', message='$message', comments=$comments, likes=$likes, dislikes=$dislikes)"
    }
}