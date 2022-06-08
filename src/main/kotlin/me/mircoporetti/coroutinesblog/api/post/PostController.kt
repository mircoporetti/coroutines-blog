package me.mircoporetti.coroutinesblog.api.post

import io.micronaut.http.HttpResponseFactory
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post as PostMapping
import kotlinx.coroutines.flow.Flow
import me.mircoporetti.coroutinesblog.domain.post.Comment
import me.mircoporetti.coroutinesblog.domain.post.usecase.GetAllPosts
import me.mircoporetti.coroutinesblog.domain.post.Post
import me.mircoporetti.coroutinesblog.domain.post.usecase.AddComment
import me.mircoporetti.coroutinesblog.domain.post.usecase.CreateNewPost
import me.mircoporetti.coroutinesblog.domain.post.usecase.RatePost

@Controller("/posts")
class PostController(
    private val getAllPosts: GetAllPosts,
    private val createNewPost: CreateNewPost,
    private val addComment: AddComment,
    private val ratePost: RatePost
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

    @PostMapping("/{postId}/comments")
    suspend fun addComment(postId: String, @Body comment: Comment): MutableHttpResponse<Any> {
        addComment.executeFor(postId, comment)
        return HttpResponseFactory
            .INSTANCE
            .status(HttpStatus.OK)
    }

    @Patch("/{postId}/rate")
    suspend fun ratePost(postId: String, @Body rateRequest: RateRequest): MutableHttpResponse<Any> {
        ratePost.executeFor(postId, rateRequest.rating)
        return HttpResponseFactory
            .INSTANCE
            .status(HttpStatus.OK)
    }
}