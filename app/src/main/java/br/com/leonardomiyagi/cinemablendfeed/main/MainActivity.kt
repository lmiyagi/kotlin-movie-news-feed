package br.com.leonardomiyagi.cinemablendfeed.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.leonardomiyagi.cinemablendfeed.*
import br.com.leonardomiyagi.cinemablendfeed.api.ApiClient
import br.com.leonardomiyagi.cinemablendfeed.article.ArticleActivity
import br.com.leonardomiyagi.cinemablendfeed.databinding.ActivityMainBinding
import br.com.leonardomiyagi.cinemablendfeed.main.adapter.FeedAdapter
import br.com.leonardomiyagi.cinemablendfeed.model.Article

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

    override fun showFetchError() {
        Toast.makeText(this, R.string.activity_main_fetch_error, Toast.LENGTH_SHORT).show()
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
        binding.feedRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.feedRecyclerView.adapter = adapter
    }
}
