package me.mircoporetti.coroutinesblog.api.post

import io.micronaut.http.HttpResponseFactory
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post as PostMapping
import kotlinx.coroutines.flow.Flow
import me.mircoporetti.coroutinesblog.domain.post.usecase.GetAllPosts
import me.mircoporetti.coroutinesblog.domain.post.Post
import me.mircoporetti.coroutinesblog.domain.post.usecase.CreateNewPost

@Controller("/posts")
class PostController(
    private val getAllPosts: GetAllPosts,
    private val createNewPost: CreateNewPost
) {

    @Get
    fun findPosts(): MutableHttpResponse<Flow<Post>>? {
        return HttpResponseFactory
            .INSTANCE
            .status<Post>(HttpStatus.OK)
            .body(getAllPosts.execute())
    }


    @PostMapping
    suspend fun createPost(@Body post: Post): MutableHttpResponse<Any> {
        createNewPost.executeFor(post)
        return HttpResponseFactory
            .INSTANCE
            .status(HttpStatus.CREATED)
    }
}