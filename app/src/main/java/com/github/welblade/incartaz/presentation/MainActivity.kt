package com.github.welblade.incartaz.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.welblade.incartaz.core.MarginItemDecoration
import com.github.welblade.incartaz.core.extensions.createDialog
import com.github.welblade.incartaz.core.extensions.createProgressDialog
import com.github.welblade.incartaz.databinding.ActivityMainBinding
import com.github.welblade.incartaz.presentation.details.DetailsActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter = MovieListAdapter()
    private val progress by lazy { createProgressDialog()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setRecyclerView()
        setObservers()
    }
    private fun setRecyclerView(){
        binding.rvMovieList.layoutManager = GridLayoutManager(this, 3)
        binding.rvMovieList.adapter = adapter
        adapter.onClickListener = { movieId ->
            val intent = Intent(
                this@MainActivity,
                DetailsActivity::class.java
            )
            intent.putExtra("movieId", movieId)
            startActivity(intent)
        }
        binding.rvMovieList.addItemDecoration(
            MarginItemDecoration(8)
        )

    }
    private fun setObservers() {
        lifecycleScope.launch {
            viewModel.getMovies().collectLatest {
                    movies -> adapter.submitData(movies)
            }
        }

    }

}