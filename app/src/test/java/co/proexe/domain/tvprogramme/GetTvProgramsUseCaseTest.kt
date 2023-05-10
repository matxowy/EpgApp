package co.proexe.domain.tvprogramme

import co.proexe.data.time.repository.TimeRepository
import co.proexe.domain.time.usecases.GetDayTilesUseCase
import co.proexe.util.DayTilesTestHelper.mockkListOfDayTiles
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetTvProgramsUseCaseTest {
    private val mockkTimeRepository = mockk<TimeRepository> {
        coEvery { getDayTiles() } returns mockkListOfDayTiles
    }

    private val systemUnderTest = GetDayTilesUseCase(mockkTimeRepository)

    @Test
    fun `when usecase is triggered then getDayTiles is called`() = runTest {
        systemUnderTest()

        coVerify(exactly = 1) { mockkTimeRepository.getDayTiles() }
    }
}