package com.github.welblade.incartaz.presentation.details

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.github.welblade.incartaz.core.extensions.createDialog
import com.github.welblade.incartaz.core.extensions.createProgressDialog
import com.github.welblade.incartaz.core.extensions.format
import com.github.welblade.incartaz.core.extensions.toDate
import com.github.welblade.incartaz.data.model.Movie
import com.github.welblade.incartaz.databinding.ActivityDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<DetailsViewModel>()
    private val progress by lazy { createProgressDialog() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled (true)
        setObserver()
        getMovieDetails()
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun getMovieDetails() {
        val movieId:Long = intent.getLongExtra("movieId", 0)
        if(movieId > 0){
            viewModel.getMovieDeails(movieId)
        }
    }

    private fun setDetails(movie: Movie) {
        binding.toolbar.title = movie.title
        movie.releaseDate.toDate()?.let {
            binding.tvDate.text = it.format("dd 'de' MMMM 'de' yyyy")
        }
        binding.tvOriginalLanguage.text = movie.originalLanguage
        if(!movie.posterPath.isNullOrBlank()){
            val url = "https://www.themoviedb.org/t/p/w220_and_h330_face${movie.posterPath}"
            Glide.with(binding.root.context)
                .load(Uri.parse(url))
                .into(binding.ivPoster)
        }
        binding.tvCategory.text = movie.genres.joinToString { it.name }
        binding.tvOverview.text = movie.overview
    }

    private fun setObserver() {
        viewModel.state.observe(this){
            when(it){
                DetailsViewModel.State.Loading -> progress.show()
                is DetailsViewModel.State.Error -> {
                    progress.dismiss()
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is DetailsViewModel.State.Success -> {
                    progress.dismiss()
                    setDetails(it.movie)
                }
            }
        }
    }
}