package co.proexe.presentation.main

import co.proexe.domain.time.usecases.GetDayTilesUseCase
import co.proexe.domain.tvprogramme.usecases.GetTvProgramsUseCase
import co.proexe.presentation.main.viewmodel.MainScreenViewModel
import co.proexe.util.DayTilesTestHelper.mockkListOfDayTiles
import co.proexe.util.TvProgrammeTestHelper.mockkFavoriteOnTopTvProgrammeList
import co.proexe.util.TvProgrammeTestHelper.mockkNoFavoriteTvProgrammeList
import co.proexe.util.TvProgrammeTestHelper.mockkTvProgrammeList
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainScreenViewModelTest {

    private val mockkGetTvProgramsUseCase = mockk<GetTvProgramsUseCase> {
        coEvery { this@mockk() } returns mockkTvProgrammeList
    }

    private val mockkGetDayTilesUseCase = mockk<GetDayTilesUseCase> {
        coEvery { this@mockk() } returns mockkListOfDayTiles
    }
    private val testCoroutineDispatcher = UnconfinedTestDispatcher()

    private var systemUnderTest = MainScreenViewModel(
        getTvProgramsUseCase = mockkGetTvProgramsUseCase,
        getDayTilesUseCase = mockkGetDayTilesUseCase,
        coroutineDispatcher = testCoroutineDispatcher
    )

    @Test
    fun `when loadData is triggered then mockkGetDayTilesUseCase should be called`() {
        systemUnderTest.loadData()

        coVerify(exactly = 1) { mockkGetDayTilesUseCase() }
    }

    @Test
    fun `when loadData is triggered then mockkGetTvProgramsUseCase should be called`() {
        systemUnderTest.loadData()

        coVerify(exactly = 1) { mockkGetTvProgramsUseCase() }
    }

    @Test
    fun `when loadData is triggered then uiState should be instance of Success`() {
        systemUnderTest.loadData()

        systemUnderTest.uiState.value should beInstanceOf<MainScreenViewModel.MainScreenUiState.Success>()
    }

    @Test
    fun `when loadData is triggered then uiState should be set to Success with correct lists`() {
        val listOfDayLabels = mockkListOfDayTiles.map { it.dayLabel }

        systemUnderTest.loadData()

        systemUnderTest.uiState.value shouldBe MainScreenViewModel.MainScreenUiState.Success(listOfDayLabels)
    }

    @Test
    fun `when loadData is triggered and getTvProgramsUseCase throws Exception then uiState should be instance of Error`() {
        coEvery { mockkGetTvProgramsUseCase() } throws Exception()

        systemUnderTest.loadData()

        systemUnderTest.uiState.value should beInstanceOf<MainScreenViewModel.MainScreenUiState.Error>()
    }

    @Test
    fun `when loadData is triggered and getDayTilesUseCase throws Exception then uiState should be instance of Error`() {
        coEvery { mockkGetDayTilesUseCase() } throws Exception()

        systemUnderTest.loadData()

        systemUnderTest.uiState.value should beInstanceOf<MainScreenViewModel.MainScreenUiState.Error>()
    }

    @Test
    fun `when onLongItemPress is triggered and program is not favorite then tvProgramsState value should be changed to proper list with favorite on top`() {
        coEvery { mockkGetTvProgramsUseCase() } returns mockkNoFavoriteTvProgrammeList
        systemUnderTest.loadData()

        systemUnderTest.onItemLongPress(1)

        systemUnderTest.tvProgramsListState.value shouldBe mockkFavoriteOnTopTvProgrammeList
    }

    @Test
    fun `when onLongItemPress is triggered and program is favorite then tvProgramsState value should be changed to proper list with program on proper position`() {
        coEvery { mockkGetTvProgramsUseCase() } returns mockkFavoriteOnTopTvProgrammeList
        systemUnderTest.loadData()

        systemUnderTest.onItemLongPress(1)

        systemUnderTest.tvProgramsListState.value shouldBe mockkNoFavoriteTvProgrammeList
    }
}