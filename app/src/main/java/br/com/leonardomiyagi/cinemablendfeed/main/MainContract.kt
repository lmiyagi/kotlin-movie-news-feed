package br.com.leonardomiyagi.cinemablendfeed.main

import br.com.leonardomiyagi.cinemablendfeed.model.Article

/**
 * Created by lmiyagi on 11/5/17.
 */
interface MainContract {

    interface View {
        fun renderArticles(articles: List<Article>)
        fun showFetchError()
        fun goToArticle(articleTitle: String, articleUrl: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onArticleClicked(article: Article)
    }
}