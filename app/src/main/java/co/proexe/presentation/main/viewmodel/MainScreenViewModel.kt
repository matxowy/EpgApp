package co.proexe.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.proexe.data.source.tvprogramme.model.data.TvProgramme
import co.proexe.domain.main.usecases.GetTvProgramsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTvProgramsUseCase: GetTvProgramsUseCase,
    @Named("IO") private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    var uiState = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                val tvProgramsList = getTvProgramsUseCase()
                _uiState.value = MainScreenUiState.Success(listOf(), tvProgramsList)
            } catch (e: Exception) {
                Log.e("RETROFIT", e.toString())
            }
        }
    }

    sealed class MainScreenUiState {
        data class Success(
            val timeStampsList: List<String>,
            val tvProgramsList: List<TvProgramme>
        ) : MainScreenUiState()
        object Loading : MainScreenUiState()
    }
}