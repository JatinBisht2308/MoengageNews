package com.example.newsapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.RowItemNewsItemBinding
import com.example.newsapp.models.NewsArticle

class NewsListAdapter(private val newsList : ArrayList<NewsArticle>, private val listener : NewsItemListener) : RecyclerView.Adapter<NewsListAdapter.NewsItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        val binding = RowItemNewsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = NewsItemViewHolder(binding)
        binding.root.setOnClickListener {
            listener.onItemClicked(newsList[viewHolder.adapterPosition].url)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val item = newsList[position]
        holder.binding.tvTitle.text = item.headline
        holder.binding.tvAuthor.text = item.src
        Glide.with(holder.itemView.context)
            .load(item.imgUrl)
            .into(holder.binding.ivImage)
    }

    fun submitList(list : List<NewsArticle>) {
        newsList.clear()
        newsList.addAll(list)

        notifyDataSetChanged()
    }

    inner class NewsItemViewHolder(val binding: RowItemNewsItemBinding) : RecyclerView.ViewHolder(binding.root)
}

interface NewsItemListener {
    fun onItemClicked(urlToNewsItem : String)
}