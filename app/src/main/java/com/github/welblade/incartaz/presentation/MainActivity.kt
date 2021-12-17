package com.github.welblade.incartaz.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.welblade.incartaz.core.extensions.createDialog
import com.github.welblade.incartaz.core.extensions.createProgressDialog
import com.github.welblade.incartaz.databinding.ActivityMainBinding
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
        binding.rvMovieList.adapter = adapter
    }
    private fun setObservers() {
        viewModel.state.observe(this){
            when(it){
                MainViewModel.State.Loading -> {
                    progress.show()
                }
                is MainViewModel.State.Error -> {
                    progress.dismiss()
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is MainViewModel.State.Success -> {
                    progress.dismiss()
                    adapter.submitList(it.response.results)
                }
            }
        }
    }
}