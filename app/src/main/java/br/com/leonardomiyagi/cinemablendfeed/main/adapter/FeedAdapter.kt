package br.com.leonardomiyagi.cinemablendfeed.main.adapter

import android.databinding.DataBindingUtil
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.leonardomiyagi.cinemablendfeed.R
import br.com.leonardomiyagi.cinemablendfeed.databinding.ListItemFeedBinding
import br.com.leonardomiyagi.cinemablendfeed.model.Article
import br.com.leonardomiyagi.cinemablendfeed.utils.DateUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by lmiyagi on 11/5/17.
 */
class FeedAdapter(val listener: OnArticleClickedListener) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private var articles: MutableList<Article> = ArrayList()

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedViewHolder {
        return FeedViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.list_item_feed, parent, false))
    }

    override fun onBindViewHolder(holder: FeedViewHolder?, position: Int) {
        holder?.format(articles[position])
    }

    public fun setArticles(articles: List<Article>) {
        this.articles.clear()
        this.articles.addAll(articles)
        notifyDataSetChanged()
    }

    inner class FeedViewHolder(private var binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun format(article: Article) {
            binding.dateTextView.text = DateUtils.formatApiDate(binding.root.context, article.pubDate!!)
            binding.articleContainer.setOnClickListener({ listener.onClick(article) })
            binding.titleTextView.text = article.title
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.descriptionTextView.text = Html.fromHtml(article.description, Html.FROM_HTML_MODE_LEGACY)
            } else {
                binding.descriptionTextView.text = Html.fromHtml(article.description)
            }
            Glide.with(binding.root.context)
                    .load(article.enclosure?.url)
                    .apply(RequestOptions().centerCrop().placeholder(R.mipmap.ic_launcher))
                    .into(binding.coverImageView)
        }
    }

    interface OnArticleClickedListener {
        fun onClick(article: Article)
    }
}