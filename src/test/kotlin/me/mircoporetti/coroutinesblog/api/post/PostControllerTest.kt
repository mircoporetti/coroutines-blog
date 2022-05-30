package me.mircoporetti.coroutinesblog.api.post

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.RestAssured
import jakarta.inject.Inject
import me.mircoporetti.coroutinesblog.domain.post.Post
import org.apache.http.HttpStatus
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

@MicronautTest
internal class PostControllerTest : MongoDBIntegrationTest() {

    @Inject
    private lateinit var server: EmbeddedServer

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
}