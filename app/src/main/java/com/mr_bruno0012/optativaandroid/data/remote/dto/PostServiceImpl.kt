package com.mr_bruno0012.optativaandroid.data.remote.dto

import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostServiceImpl(private val client: HttpClient) : PostsService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (e: RedirectResponseException) {
            println("Error redirect ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            println("Error client: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("Error server:  ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (e: RedirectResponseException) {
            println("Error redirect ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            println("Error client: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            println("Error server:  ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error ${e.message}")
            null
        }

    }
}

