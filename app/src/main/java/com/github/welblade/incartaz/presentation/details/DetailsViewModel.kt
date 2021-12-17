package com.github.welblade.incartaz.presentation.details

import androidx.lifecycle.*
import com.github.welblade.incartaz.data.model.Movie
import com.github.welblade.incartaz.domain.GetMovieDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel(), LifecycleObserver {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

     fun getMovieDeails(movieId:Long) {
        viewModelScope.launch {
            getMovieDetailsUseCase(movieId)
                .flowOn(Dispatchers.Main)
                .onStart {
                    _state.value = State.Loading
                }
                .catch {
                    _state.value = State.Error(it)
                }
                .collect {
                    _state.value = State.Success(it)
                }
        }
    }

    sealed class State {
        object Loading: State()
        data class Success(val movie: Movie): State()
        data class Error(val error: Throwable): State()
    }
}