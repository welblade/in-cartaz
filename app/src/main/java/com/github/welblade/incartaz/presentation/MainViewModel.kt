package com.github.welblade.incartaz.presentation

import androidx.lifecycle.*
import com.github.welblade.incartaz.data.model.NowPlayingResult
import com.github.welblade.incartaz.domain.GetNowPlayingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val nowPlayingUseCase: GetNowPlayingUseCase
): ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    fun getNowPlaying(page: Int = 1){
        viewModelScope.launch {
            nowPlayingUseCase(page)
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

    sealed class State(){
        object Loading: State()
        data class Success(val response: NowPlayingResult): State()
        data class Error(val error: Throwable): State()
    }
}