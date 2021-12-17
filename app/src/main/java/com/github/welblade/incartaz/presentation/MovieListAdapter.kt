package com.github.welblade.incartaz.presentation

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.welblade.incartaz.core.extensions.format
import com.github.welblade.incartaz.core.extensions.toDate
import com.github.welblade.incartaz.data.model.Result
import com.github.welblade.incartaz.databinding.ItemMovieBinding

class MovieListAdapter :
    PagingDataAdapter<Result, MovieListAdapter.ViewHolder>(DiffCallBack())
{
    var onClickListener: (Long)-> Unit = { }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) {
            binding.tvTitle.text = item.title
            // binding.tvCategory.text = item.genreIDS.first().toString()
            item.releaseDate.toDate()?.let {
                binding.tvDate.text = it.format()
            }

            if(!item.posterPath.isNullOrBlank()){
                val url = "https://www.themoviedb.org/t/p/w220_and_h330_face${item.posterPath}"
                Glide.with(binding.root.context)
                    .load(Uri.parse(url))
                    .into(binding.ivPoster)
            }
            binding.root.setOnClickListener {
                onClickListener(item.id)
            }

        }
    }
    class DiffCallBack : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

