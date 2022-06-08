package me.mircoporetti.coroutinesblog.domain.post.usecase

import jakarta.inject.Singleton
import me.mircoporetti.coroutinesblog.domain.post.PostPersistencePort
import me.mircoporetti.coroutinesblog.domain.post.Rating

@Singleton
class RatePost(private val postPersistencePort: PostPersistencePort) {

    suspend fun executeFor(postId: String, rating: Rating) {
        val post = postPersistencePort.find(postId)
        post.rate(rating)
        postPersistencePort.update(post)
    }
}