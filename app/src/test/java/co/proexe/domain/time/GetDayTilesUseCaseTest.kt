package co.proexe.domain.time

import co.proexe.data.tvprogramme.repository.TvProgrammeRepository
import co.proexe.domain.tvprogramme.usecases.GetTvProgramsUseCase
import co.proexe.util.TvProgrammeTestHelper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GetDayTilesUseCaseTest {
    private val mockkTvProgrammeRepository = mockk<TvProgrammeRepository> {
        coEvery { getTvPrograms() } returns TvProgrammeTestHelper.mockkTvProgrammeList
    }

    private val systemUnderTest = GetTvProgramsUseCase(mockkTvProgrammeRepository)

    @Test
    fun `when usecase is triggered then getTvPrograms is called`() = runTest {
        systemUnderTest()

        coVerify(exactly = 1) { mockkTvProgrammeRepository.getTvPrograms() }
    }
}