package br.com.leonardomiyagi.cinemablendfeed.api

import br.com.leonardomiyagi.cinemablendfeed.model.FeedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

/**
 * Created by lmiyagi on 11/5/17.
 */
class ApiClient {

    private var apiService: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://www.cinemablend.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    public fun getArticles(callback: Callback<FeedResponse>) {
        val call: Call<FeedResponse> = apiService.getArticles()
        call.enqueue(callback)
    }
}