package me.mircoporetti.coroutinesblog.api.post

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoCollection
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.RestAssured
import jakarta.inject.Inject
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import me.mircoporetti.coroutinesblog.domain.post.Post
import me.mircoporetti.coroutinesblog.mongoadapter.post.MongoPost
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
internal class PostControllerTest : MongoDBIntegrationTest() {

    @Inject
    private lateinit var server: EmbeddedServer

    @Inject
    private lateinit var mongoClient: MongoClient

    @BeforeEach
    fun setUp() {
        RestAssured.basePath = "/api"
        RestAssured.port = server.port
    }

    @Test
    internal fun `findPosts returns no posts`() {
        val posts: List<Post> = RestAssured
            .given()
            .`when`()
            .get("/posts")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .getList(".", Post::class.java)

        assertEquals(emptyList<Post>(), posts)
    }

    @Test
    internal fun `findPosts returns a post`() {
        val expectedPost = Post("6294bd60d371233a146e390b", "a message", null, 0L, 0L)

        runBlocking {
            getCollection().insertOne(MongoPost("6294bd60d371233a146e390b", "a message", mutableListOf(), 0L, 0L)).awaitSingle()
        }

        val posts: List<Post> = RestAssured
            .given()
            .`when`()
            .get("/posts")
            .then()
            .statusCode(HttpStatus.SC_OK)
            .extract()
            .body()
            .jsonPath()
            .getList(".", Post::class.java)

        assertEquals(listOf(expectedPost), posts)
    }

    @Test
    internal fun `createPost returns status CREATED`() {

        val postToBeSaved = Post(null, "a message", mutableListOf(), 0L, 0L)

        RestAssured
            .given()
            .contentType("application/json")
            .body(postToBeSaved)
            .`when`()
            .post("/posts")
            .then()
            .statusCode(HttpStatus.SC_CREATED)
    }

    private fun getCollection(): MongoCollection<MongoPost> {
        return mongoClient.getDatabase("coroutinesblog")
            .getCollection("posts", MongoPost::class.java)
    }
}