package br.com.leonardomiyagi.cinemablendfeed.api

import br.com.leonardomiyagi.cinemablendfeed.model.FeedResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by lmiyagi on 11/5/17.
 */
interface ApiService {

    @GET("rss_news_movies.xml")
    public fun getArticles(): Call<FeedResponse>
}