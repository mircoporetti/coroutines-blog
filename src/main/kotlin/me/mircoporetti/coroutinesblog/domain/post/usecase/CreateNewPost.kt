package me.mircoporetti.coroutinesblog.domain.post.usecase

import jakarta.inject.Singleton
import me.mircoporetti.coroutinesblog.domain.post.Post
import me.mircoporetti.coroutinesblog.domain.post.PostPersistencePort

@Singleton
class CreateNewPost(private val postPersistencePort: PostPersistencePort) {

     suspend fun executeFor(post: Post) {
        postPersistencePort.save(post)
    }
}