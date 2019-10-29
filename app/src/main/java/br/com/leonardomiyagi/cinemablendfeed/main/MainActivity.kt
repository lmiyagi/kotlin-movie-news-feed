package br.com.leonardomiyagi.cinemablendfeed.main

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.leonardomiyagi.cinemablendfeed.R
import br.com.leonardomiyagi.cinemablendfeed.api.ApiClient
import br.com.leonardomiyagi.cinemablendfeed.article.ArticleActivity
import br.com.leonardomiyagi.cinemablendfeed.databinding.ActivityMainBinding
import br.com.leonardomiyagi.cinemablendfeed.main.adapter.FeedAdapter
import br.com.leonardomiyagi.cinemablendfeed.model.Article
import br.com.leonardomiyagi.cinemablendfeed.utils.PlaceholderData

class MainActivity : AppCompatActivity(), MainContract.View, FeedAdapter.OnArticleClickedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MainContract.Presenter
    private lateinit var adapter: FeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        presenter = MainPresenter(ApiClient())
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        presenter.detachView()
        super.onStop()
    }

    override fun renderArticles(articles: List<Article>) {
        adapter.setArticles(articles)
    }

    override fun showLoading() {
        binding.placeholders?.data = PlaceholderData.loading(this)
    }

    override fun showFetchError(tryAgainAction: Runnable) {
        binding.placeholders?.data = PlaceholderData.error(this, R.string.activity_main_fetch_error, tryAgainAction)
    }

    override fun hideAllPlaceholders() {
        binding.placeholders?.data = PlaceholderData.hide()
    }

    override fun onClick(article: Article) {
        presenter.onArticleClicked(article)
    }

    override fun goToArticle(articleTitle: String, articleUrl: String) {
        val intent = Intent(this, ArticleActivity::class.java)
        intent.putExtra(ArticleActivity.ARTICLE_TITLE_EXTRA, articleTitle)
        intent.putExtra(ArticleActivity.ARTICLE_URL_EXTRA, articleUrl)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        adapter = FeedAdapter(this)
        binding.feedRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.feedRecyclerView.adapter = adapter
    }
}
