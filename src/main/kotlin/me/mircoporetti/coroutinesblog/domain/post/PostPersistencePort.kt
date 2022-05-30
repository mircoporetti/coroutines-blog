package me.mircoporetti.coroutinesblog.domain.post

import kotlinx.coroutines.flow.Flow

interface PostPersistencePort {

    fun findAll(): Flow<Post>

    suspend fun save(post: Post)
}
