package co.proexe.presentation.main.viewmodel

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

    private val _tvProgramsListState = MutableStateFlow<List<TvProgramme>>(listOf())
    var tvProgramsListState = _tvProgramsListState.asStateFlow()

    fun loadData() {
        viewModelScope.launch(coroutineDispatcher) {
            try {
                // TODO integrate it with TimeStamps, for now dummy implementation only with labels
                val dayLabelsResource = getDayTilesUseCase().map { it.dayLabel }
                _tvProgramsListState.value = getTvProgramsUseCase()
                _uiState.value = MainScreenUiState.Success(dayLabelsResource)
            } catch (e: Exception) {
                _uiState.value = MainScreenUiState.Error
            }
        }
    }

    fun onItemLongPress(id: Int) {
        val currentList = _tvProgramsListState.value
        val program = currentList.find { it.id == id }!!
        if (program.isFavorite) {
            removeFromFavorites(program, currentList)
        } else {
            addToFavorites(program, currentList)
        }
    }

    private fun addToFavorites(program: TvProgramme, listOfPrograms: List<TvProgramme>) {
        val newList = listOfPrograms.toMutableList().apply {
            remove(program)
            add(0, program.copy(isFavorite = true))
        }
        _tvProgramsListState.value = newList
    }

    private fun removeFromFavorites(program: TvProgramme, listOfPrograms: List<TvProgramme>) {
        val nonFavoritePrograms = listOfPrograms.filter { !it.isFavorite }.toMutableList().apply {
            add(program.copy(isFavorite = false))
            sortBy { programme -> programme.id }
        }
        val newList = listOfPrograms.filter { it.isFavorite }.toMutableList().apply {
            remove(program)
            addAll(nonFavoritePrograms)
        }
        _tvProgramsListState.value = newList
    }

    sealed class MainScreenUiState {
        data class Success(val dayLabelsList: List<Int>) : MainScreenUiState()

        object Loading : MainScreenUiState()
        object Error : MainScreenUiState()
    }
}