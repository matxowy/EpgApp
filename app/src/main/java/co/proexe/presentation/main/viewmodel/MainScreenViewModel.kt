package co.proexe.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.proexe.data.source.tvprogramme.model.data.TvProgramme
import co.proexe.domain.time.usecases.GetDayTilesUseCase
import co.proexe.domain.tvprogramme.usecases.GetTvProgramsUseCase
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
    private val getDayTilesUseCase: GetDayTilesUseCase,
    @Named("IO") private val coroutineDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    var uiState = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                // TODO integrate it with TimeStamps, for now dummy implementation only with labels
                val dayLabelsResource = getDayTilesUseCase().map { it.dayLabel }
                val tvProgramsList = getTvProgramsUseCase()
                _uiState.value = MainScreenUiState.Success(dayLabelsResource, tvProgramsList)
            } catch (e: Exception) {
                _uiState.value = MainScreenUiState.Error
            }
        }
    }

    sealed class MainScreenUiState {
        data class Success(
            val dayLabelsList: List<Int>,
            val tvProgramsList: List<TvProgramme>
        ) : MainScreenUiState()
        object Loading : MainScreenUiState()
        object Error : MainScreenUiState()
    }
}