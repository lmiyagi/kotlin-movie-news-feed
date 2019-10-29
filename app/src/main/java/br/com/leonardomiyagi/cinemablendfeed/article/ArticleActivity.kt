package br.com.leonardomiyagi.cinemablendfeed.article

import android.annotation.SuppressLint
import androidx.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import br.com.leonardomiyagi.cinemablendfeed.R
import br.com.leonardomiyagi.cinemablendfeed.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    companion object {
        const val ARTICLE_TITLE_EXTRA = "ARTICLE_TITLE_EXTRA"
        const val ARTICLE_URL_EXTRA = "ARTICLE_URL_EXTRA"
    }

    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupWebView()
        title = intent.getStringExtra(ARTICLE_TITLE_EXTRA)
        binding.webview.loadUrl(intent.getStringExtra(ARTICLE_URL_EXTRA))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        val settings: WebSettings = binding.webview.settings
        settings.javaScriptEnabled = true
        binding.webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE

            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}
