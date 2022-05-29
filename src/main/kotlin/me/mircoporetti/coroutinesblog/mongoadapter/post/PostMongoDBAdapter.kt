package me.mircoporetti.coroutinesblog.mongoadapter.post

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import me.mircoporetti.coroutinesblog.domain.post.Post
import me.mircoporetti.coroutinesblog.domain.post.PostPersistencePort

@Singleton
class PostMongoDbAdapter(private val mongoClient: MongoClient) : PostPersistencePort {

    override fun findAll(): Flow<Post> {
        return getCollection().find()
            .asFlow()
            .map {
                p -> Post(p.id, p.message, p.comments, p.likes, p.dislikes)
        }
    }

    private fun getCollection(): MongoCollection<MongoPost> {
        return mongoClient.getDatabase("coroutinesblog")
            .getCollection("posts", MongoPost::class.java)
    }
}