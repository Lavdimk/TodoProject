package com.ict.projecttodo.ui.fragments.api

import com.ict.projecttodo.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface ServiceAPI {

    @GET("todos")
    fun getAllPosts() : Call<List<Post>>
}