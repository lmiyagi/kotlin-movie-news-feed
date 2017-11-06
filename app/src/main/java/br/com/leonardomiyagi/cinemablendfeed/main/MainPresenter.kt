package br.com.leonardomiyagi.cinemablendfeed.main

import br.com.leonardomiyagi.cinemablendfeed.api.ApiClient
import br.com.leonardomiyagi.cinemablendfeed.model.Article
import br.com.leonardomiyagi.cinemablendfeed.model.FeedResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * Created by lmiyagi on 11/5/17.
 */
class MainPresenter(private val apiClient: ApiClient) : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun attachView(view: MainContract.View) {
        this.view = view
        fetchArticles()
    }

    override fun detachView() {
        view = null
    }

    override fun onArticleClicked(article: Article) {
        view?.goToArticle(article.title!!, article.link!!)
    }

    private fun fetchArticles() {
        apiClient.getArticles(callback = object : Callback, retrofit2.Callback<FeedResponse> {

            override fun onResponse(call: Call<FeedResponse>?, response: Response<FeedResponse>?) {
                view?.renderArticles(response?.body()?.channel?.articles!!)
            }

            override fun onFailure(call: Call<FeedResponse>?, t: Throwable?) {
                t?.printStackTrace()
                view?.showFetchError()
            }
        })
    }
}