package me.mircoporetti.coroutinesblog.mongoadapter.post

import com.mongodb.client.model.Filters.eq
import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import me.mircoporetti.coroutinesblog.domain.post.Comment
import me.mircoporetti.coroutinesblog.domain.post.Post
import me.mircoporetti.coroutinesblog.domain.post.PostPersistencePort
import org.bson.types.ObjectId

@Singleton
class PostMongoDbAdapter(private val mongoClient: MongoClient) : PostPersistencePort {

    override fun findAll(): Flow<Post> {
        return getCollection().find()
            .asFlow()
            .map { p ->
                Post(
                    p.id, p.message,
                    p.comments?.map { c -> Comment(c.author, c.message) }?.toMutableList(), p.likes, p.dislikes
                )
            }
    }

    override suspend fun find(postId: String): Post {
        val post = getCollection().find(eq("_id", ObjectId(postId)))
            .awaitSingle()

        return Post(
            post.id, post.message,
            post.comments?.map { c -> Comment(c.author, c.message) }?.toMutableList(), post.likes, post.dislikes
        )
    }

    override suspend fun save(post: Post) {
        getCollection().insertOne(
            (if(post.id !== null) post.id else ObjectId().toHexString())?.let {
                MongoPost(
                    it,
                    post.message,
                    post.comments?.map { p -> MongoComment(p.author, p.message) }
                        ?.toMutableList(),
                    post.likes,
                    post.dislikes)
            }).awaitFirst()
    }

    override suspend fun update(post: Post) {
            getCollection().deleteOne(eq("_id", ObjectId(post.id))).awaitSingle()
            save(post)
    }

    private fun getCollection(): MongoCollection<MongoPost> {
        return mongoClient.getDatabase("coroutinesblog")
            .getCollection("posts", MongoPost::class.java)
    }
}