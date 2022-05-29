package me.mircoporetti.coroutinesblog.api.post

import io.micronaut.http.HttpResponseFactory
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import kotlinx.coroutines.flow.Flow
import me.mircoporetti.coroutinesblog.domain.post.usecase.GetAllPosts
import me.mircoporetti.coroutinesblog.domain.post.Post

@Controller("/posts")
class PostController(
    private val getAllPosts: GetAllPosts
) {

    @Get
    fun findAll(): MutableHttpResponse<Flow<Post>>? {
        return HttpResponseFactory
            .INSTANCE
            .status<Post>(HttpStatus.OK)
            .body(getAllPosts.execute())
    }
}