package me.mircoporetti.coroutinesblog.domain.post.usecase

import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import me.mircoporetti.coroutinesblog.domain.post.Post
import me.mircoporetti.coroutinesblog.domain.post.PostPersistencePort

@Singleton
class GetAllPosts(private val postPersistencePort: PostPersistencePort) {

     fun execute(): Flow<Post> {
        return postPersistencePort.findAll()
    }
}