package me.mircoporetti.coroutinesblog.domain.post.usecase

import jakarta.inject.Singleton
import me.mircoporetti.coroutinesblog.domain.post.Comment
import me.mircoporetti.coroutinesblog.domain.post.PostPersistencePort

@Singleton
class AddComment(private val postPersistencePort: PostPersistencePort) {

    suspend fun executeFor(postId: String, comment: Comment) {
        val post = postPersistencePort.find(postId)
        post.addComment(comment)
        postPersistencePort.update(post)
    }
}